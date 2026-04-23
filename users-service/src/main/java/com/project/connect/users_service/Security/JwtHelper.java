package com.project.connect.users_service.Security;

import com.project.connect.users_service.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtHelper {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public SecretKey getSecret()
    {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String getAccessToken(User user)
    {
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("email",user.getEmail())
                .claim("userName",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*5))
                .signWith(getSecret())
                .compact();

    }
    public  Long getUserId(String accessToken)
    {
        Claims claims = Jwts.parser()
                .verifyWith(getSecret())
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }
    public String refreshToken(User user)
    {
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("email",user.getEmail())
                .claim("userName",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getSecret())
                .compact();
    }

}
