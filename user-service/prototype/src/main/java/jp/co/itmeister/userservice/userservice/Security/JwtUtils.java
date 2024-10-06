package jp.co.itmeister.userservice.userservice.Security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import jakarta.servlet.http.HttpServletRequest;


@Component
public class JwtUtils {

    @Value("${prototype.app.jwtExpirationSeconds}")
    private Integer jwtExpirationSeconds;

    @Value("${prototype.app.jwtCookieName}")
    private String jwtCookieName;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    public ResponseCookie generateJwtCookie (String email) {
        String jwt = generateTokenFromEmail(email);
        ResponseCookie cookie = ResponseCookie.from(jwtCookieName, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).secure(true).sameSite("Strict").build();
        return cookie;
    }

    // public boolean validateJwtToken (HttpServletRequest request) {

    // }

private String generateTokenFromEmail(String email) {
    return Jwts.builder()
        .subject(email)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + jwtExpirationSeconds))
        .signWith(key())
        .compact();
}

    // private String getTokenFromCookie (HttpServletRequest request) {

    // }
}