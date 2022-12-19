package com.berknbilgc.registerlogin.security;

import com.berknbilgc.registerlogin.business.services.IUserServices;
import com.berknbilgc.registerlogin.data.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//lombok
@RequiredArgsConstructor

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    //inject
    private final IUserServices services;

    //kullanıcı bulmak için kullanıyoruz.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // UserDetailsService -->  UserDetails (username,password,isAccountNonExpired,isAccountNonLocked,isCredentialsNonExpired,isEnabled)
        UserEntity userEntity=services.findUsername(username).orElseThrow(()->new UsernameNotFoundException(username+" adlı kullanıcı bulunamadı") );
        return new UserPrincipal(userEntity.getId(),userEntity.getUsername(),userEntity.getPassword());
    }
}
