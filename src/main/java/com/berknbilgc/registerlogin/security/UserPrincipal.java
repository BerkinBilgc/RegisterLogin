package com.berknbilgc.registerlogin.security;

import com.berknbilgc.registerlogin.roles.ERoles;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// authentication:kimlik doğrulama işelmini yapacağız.
// authorization:Yetkilendirme

//lombok
@Data
@NoArgsConstructor
@Log4j2
public class UserPrincipal implements UserDetails {
    //field
    private Long id;
    private String username;
    transient private String password;

    //parametreli constructor
    public UserPrincipal(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //rol tabanlı olmadığı için
        return Collections.singletonList(new SimpleGrantedAuthority(ERoles.USER.toString()));
    }

    @Override
    public String getPassword() {
        return
                password;
    }

    @Override
    public String getUsername() {
        return
                username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
