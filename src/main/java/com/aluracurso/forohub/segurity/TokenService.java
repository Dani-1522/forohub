package com.aluracurso.forohub.segurity;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private String secret;
    public String getSubject(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            throw new RuntimeException("Token inválido", e);
        }
    }


}