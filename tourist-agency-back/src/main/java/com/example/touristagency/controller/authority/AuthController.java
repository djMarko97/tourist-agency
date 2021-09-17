package com.example.touristagency.controller.authority;

import com.example.touristagency.controller.RestController;
import com.example.touristagency.dto.ReservationDto;
import com.example.touristagency.dto.UserDto;
import com.example.touristagency.entity.authority.UserEntity;
import com.example.touristagency.mapper.authority.UserViewMapper;
import com.example.touristagency.requests.authority.AuthUserRequest;
import com.example.touristagency.requests.authority.CreateUserRequest;
import com.example.touristagency.security.JwtTokenUtil;
import com.example.touristagency.security.authorization.IsUser;
import com.example.touristagency.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserViewMapper userViewMapper;

    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserViewMapper userViewMapper, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userViewMapper = userViewMapper;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid AuthUserRequest authUserRequest){
        try{
            Authentication authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authUserRequest.getUsername(),authUserRequest.getPassword()));
            UserEntity userEntity = (UserEntity) authentication.getPrincipal();
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, this.jwtTokenUtil.generateAccessToken(userEntity))
                    .body(this.userViewMapper.toDto(userEntity));
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public UserDto register(@RequestBody @Valid CreateUserRequest createUserRequest){
        return this.userService.createUser(createUserRequest);
    }

    @IsUser
    @GetMapping(path="reservations/{id}")
    public ResponseEntity<Set<ReservationDto>> getReservationsByUserId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getReservationsByUserId(id));
    }

    @IsUser
    @PostMapping("reservation/{idRes}/user/{idUser}")
    public Set<ReservationDto> addReservationToUser(@PathVariable Long idRes, @PathVariable Long idUser){
        return userService.addReservationToUser(idUser, idRes);
    }

    @IsUser
    @DeleteMapping("reservation/{idRes}/user/{idUser}")
    public Set<ReservationDto> removeUserFromReservation(@PathVariable Long idRes, @PathVariable Long idUser){
        return userService.removeReservationForUser(idUser, idRes);
    }
}
