package com.berknbilgc.registerlogin.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Filter
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    //Constructor Injection öncelikle bunu SecurityConfig jwtAuthorizationFilterBeanMethod() adında bean ekledim
    @Autowired
    private  IJwtProvider iJwtProvider;

    //filter daha önce çalışmamışsa
    //requet ve responselara erişir bu filter
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //gelen istediği
        Authentication authentication=iJwtProvider.getAuthentication(request);

        //kimlik doğrulama
        if(authentication!=null && iJwtProvider.isValidateToken(request)){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //filtereleme sonrasında geri dönüş sağladık
        filterChain.doFilter(request,response);
    }
}