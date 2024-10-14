package jp.co.itmeister.userservice.userservice.Security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

import jakarta.servlet.http.Cookie;
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

    public ResponseCookie generateJwtCookie (String username) {
        String jwt = generateTokenFromUserName(username);
        ResponseCookie cookie = ResponseCookie.from(jwtCookieName, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).secure(true).sameSite("Strict").build();
        return cookie;
    }

    public boolean validateJwtToken (String authToken) {
        try {
            Jwts.parser().verifyWith(key()).build().parseSignedClaims(authToken);

            return true;
          
        } catch (JwtException e) {
            return false;
        }
    }

    private String generateTokenFromUserName(String username) {
    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + jwtExpirationSeconds))
        .signWith(key())
        .compact();
    }

    public String getUserNameFromToken(String token) {
        try {
            return Jwts.parser().verifyWith(key()).build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    public String getTokenFromCookie (HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookieName);

        if(cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }
}