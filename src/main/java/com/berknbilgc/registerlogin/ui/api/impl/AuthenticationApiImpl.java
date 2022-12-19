package com.berknbilgc.registerlogin.ui.api.impl;

import com.berknbilgc.registerlogin.business.dto.UserDto;
import com.berknbilgc.registerlogin.business.services.IAuthenticationService;
import com.berknbilgc.registerlogin.business.services.IUserServices;
import com.berknbilgc.registerlogin.security.jwt.JwtProviderImpl;
import com.berknbilgc.registerlogin.ui.api.IAuthenticationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//lombok
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationApiImpl implements IAuthenticationApi {

    //Injection
    private final IAuthenticationService authenticationService;
    private final IUserServices userServices;


    private final  AuthenticationManager authenticationManager;
    private final JwtProviderImpl jwtUtils;


    //REGISTER
    // http://localhost:1111/api/authentication/register
    @Override
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        // kullanıcı adımız unique olmalıdır.
        if (userServices.findUsername(userDto.getUsername()).isPresent()) {
            //aynı kullanıcı varsa conflict oluşturalım
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userServices.createUser(userDto), HttpStatus.CREATED);
    }

    //LOGIN
    // http://localhost:1111/api/authentication/login
    @Override
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        //UserPrincipal userLoginDetails= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println(userLoginDetails);
        return new ResponseEntity<>(authenticationService.loginReturnJwt(userDto), HttpStatus.OK);
    }
}
