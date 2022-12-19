package com.berknbilgc.registerlogin.business.services.impl;

import com.berknbilgc.registerlogin.business.dto.UserDto;
import com.berknbilgc.registerlogin.business.services.IAuthenticationService;
import com.berknbilgc.registerlogin.security.UserPrincipal;
import com.berknbilgc.registerlogin.security.jwt.IJwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


public class AuthenticationServiceImpl implements IAuthenticationService {

    //injection
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private IJwtProvider iJwtProvider;

    @Override
    public String loginReturnJwt(UserDto userDto){
        //Authentication ==> UserDto username ve password vermek
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword()));

        // UserPrincipal
        UserPrincipal userPrincipal= (UserPrincipal) authentication.getPrincipal();

        //IJwtProvider
        return iJwtProvider.generateToken(userPrincipal);
    }
}