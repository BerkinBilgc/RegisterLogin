package com.berknbilgc.registerlogin.business.services;

import com.berknbilgc.registerlogin.business.dto.UserDto;

public interface IAuthenticationService {

    String loginReturnJwt(UserDto userDto);
}
