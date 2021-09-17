package com.example.touristagency.security;


import com.example.touristagency.entity.authority.UserEntity;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.lang.String.format;

/**
 * Utility class for managing JWTa
 * @author djMarko97
 */
@Component
public class JwtTokenUtil {

    /** Secret **/
    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
    /** Issuer **/
    private final String jwtIssuer = "tourist-agency.com";

    /**
     * Generates token for user
     * @param user user entity
     * @return token
     */

    public String generateAccessToken(UserEntity user){
        return Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer(this.jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 *24 *60*60*1000))//1 week
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();
    }

    /**
     * Gets user id from token
     * @param token jwt token
     * @return user id
     */

    public String getUserId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    /**
     * Gets username from token
     * @param token jwt token
     * @return username
     */

    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    /**
     * Gets the expiration date of token
     * @param token jwt token
     * @return expiration date
     */

    public Date getExpirationDate(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    /**
     * Checks if the token is valid
     * @param token
     * @return true if valid, otherwise false
     */

    public boolean validate(String token){
        try{
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){

        }catch (MalformedJwtException ex){

        }catch (ExpiredJwtException ex){

        }catch (UnsupportedJwtException ex){

        }catch (IllegalArgumentException ex){

        }
        return  false;
    }

}
