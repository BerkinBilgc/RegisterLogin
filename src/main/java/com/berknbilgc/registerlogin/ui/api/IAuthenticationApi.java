package com.berknbilgc.registerlogin.ui.api;

import com.berknbilgc.registerlogin.business.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationApi {

    ResponseEntity<?> login(@RequestBody UserDto userDto);
    ResponseEntity<?> register(@RequestBody UserDto userDto);
}