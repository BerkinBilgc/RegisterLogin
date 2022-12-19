package com.berknbilgc.registerlogin.security.jwt;

import com.berknbilgc.registerlogin.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;

public interface IJwtProvider {
    //RSA kullanıdğımızı Spring Security'e bildirmek için
    //key factory method: Java security RSA kullanıdğımızı belirtmemiz için kullanıyoruz
    KeyFactory getKeyFactory();

    //1.YÖNTEM (Token create)
    // (JWT token oluşturmak) öncelikle sistemde kullanıcı kimliği doğrulanmışssa buradan devam edilir.
    //userPrincipal: username,password,roles
    String generateToken(UserPrincipal userPrincipal);

    //2.YÖNTEM (jwt ayrıştırılması Bearer
    //HEADER: bearer => 7
    String resolveToken(HttpServletRequest httpServletRequest);
    Authentication getAuthentication(HttpServletRequest httpServletRequest);

    //3.YÖNTEM (token süresini kontrol etmek )
    boolean isValidateToken(HttpServletRequest httpServletRequest);
}