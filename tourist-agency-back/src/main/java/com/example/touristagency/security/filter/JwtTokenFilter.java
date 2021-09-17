package com.example.touristagency.security.filter;

import com.example.touristagency.repository.authority.UserRepository;
import com.example.touristagency.security.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    /** JWT Token Util **/
    private final JwtTokenUtil jwtTokenUtil;

    /** user Repository **/
    private final UserRepository userRepository;

    private final String HEADER_BEARER = "Bearer";

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // Get authorization header and validate
        final String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(!StringUtils.startsWith(header, HEADER_BEARER)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if(!this.jwtTokenUtil.validate(token)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // Get user identity and set it on the spring security context
        String username = this.jwtTokenUtil.getUsername(token);
        UserDetails userDetails = this.userRepository
                .findByUsername(this.jwtTokenUtil.getUsername(token))
                .orElse(null);

        UsernamePasswordAuthenticationToken authentication = null;
        if(userDetails != null){
            authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null,
                    userDetails.getAuthorities()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
