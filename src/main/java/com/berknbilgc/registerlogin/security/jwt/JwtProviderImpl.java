package com.berknbilgc.registerlogin.security.jwt;

import com.berknbilgc.registerlogin.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


//spring tarafında instance oluşturmak için
@Component
public class JwtProviderImpl implements IJwtProvider {

    //Token header String nesneleri oluştur
    private static final String JWT_TOKEN_PREFIX = "Bearer";
    private static final String JWT_HEADER_STRING = "Authorization";

    //token yaşama süresi
    @Value("${authentication.jwt.expiration-ms}")
    private Long JWT_EXPIRATION_MS;

    // Genel anahtar
    private PublicKey jwtPublicKey;

    // Gizli anahtar
    private PrivateKey jwtPrivateKey;

    //parametresiz constructor
    public JwtProviderImpl(
            @Value("${authentication.jwt.public-key}") String jwtPublicKeyStr,
            @Value("${authentication.jwt.private-key}") String jwtPrivateKeyStr) throws InvalidKeySpecException {
        try {
            //Java security RSA kullanıdğımızı belirtmemiz için kullanıyoruz
            KeyFactory keyFactory = getKeyFactory();

            //gizli ve ozel anahtarlar Base64 anahtarlama olduğunda açmamız gerekiyor.
            Base64.Decoder decoder = Base64.getDecoder();

            //Spring security için anahtar kodlayıcı genel ve ozel anahtar için
            //private key kodlamak
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decoder.decode(jwtPrivateKeyStr.getBytes()));

            //public key kodlamak
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decoder.decode(jwtPublicKeyStr.getBytes()));

            jwtPrivateKey = keyFactory.generatePrivate(privateKeySpec);
            jwtPublicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            throw new RuntimeException("Invalid key specification ", e);
        }
    }


    // 1.YÖNTEM
    //key factory method: Java security RSA kullanıdğımızı belirtmemiz için kullanıyoruz
    @Override
    public KeyFactory getKeyFactory() {
        try {
            return KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unknow key generation algorithm ", e);
        }
    }

    //1.YÖNTEM generateToken
    // (JWT token oluşturmak) öncelikle sistemde kullanıcı kimliği doğrulanmışssa buradan devam edilir.
    // USerPrincipal: kullanıcı email,şifre,roller vardır.
    @Override
    public String generateToken(UserPrincipal authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        return Jwts.builder().setSubject(authentication.getUsername())
                //kullanıcı ID almak
                .claim("userId", authentication.getId())
                //kullanıc rol
                .claim("roles", authorities)
                //token başlama ve bitiş süresi
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                //jwt imzalamak için signWith farklı olanları kullanabiliriz en iyisi  SignatureAlgorithm.RS512
                .signWith(jwtPrivateKey, SignatureAlgorithm.RS512)
                .compact();
    }

    // 2.YÖNTEM  resolveToken
    // HEADER: bearer ile başlar ==> bearer (boşlukla beraber 7 karakterdir)
    // jwt ayrıştırmak ve authentication nesnesi oluşturmayı sağlamak
    //token unutma Bearer(boşluk)=7harf anahtar kelimesinden sonra
    @Override
    public String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(JWT_HEADER_STRING);
        //token null ise return null döndürelim
        if (bearerToken != null && bearerToken.startsWith(JWT_TOKEN_PREFIX))
            return bearerToken.substring(7);
        return null;
    }

    // 2.YÖNTEM  getAuthentication
    @Override
    public Authentication getAuthentication(HttpServletRequest httpServletRequest) {
        String token = resolveToken(httpServletRequest);
        //eğer token null ise return null olsun
        if (token == null)
            return null;
        //Claims JWT tüm bilgilerine erişim sağlıyor.
        //Token verisinie ayrıştırmak
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtPublicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        //Claims üzeridnen username erişmek
        //Claims üzeridnen userId erişmek
        //Claims üzeridnen roles erişmek
        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);
        //dikakt kimlik doğrulama roles ile başlamalıdır. eğer rolde yoksa başına roles eklemeliyiz
        List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //UserDetails kimlik doğrulama oluşturmak
        UserDetails userDetails = new UserPrincipal(userId, username, null);
        Authentication kimlikDogrulama = username != null ? new UsernamePasswordAuthenticationToken(userDetails, null, authorities) : null;
        return kimlikDogrulama;
    }

    //3.YÖNTEM
    // validate: token süresini kontrol etmek için
    @Override
    public boolean isValidateToken(HttpServletRequest httpServletRequest) {
        String token = resolveToken(httpServletRequest);
        if (token == null)
            return false;

        //Claims JWT tüm bilgilerine erişim sağlıyor.
        //Token verisinie ayrıştırmak
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtPublicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();


        //eğer token süresi bitmişse false döndürelim yoksa true döndürelim
        if (claims.getExpiration().before(new Date()))
            return false;
        return true;
    }
}
