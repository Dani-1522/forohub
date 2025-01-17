package com.aluracurso.forohub.infra.segurity;


import com.aluracurso.forohub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apisecret;

    public String generarToken (Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apisecret);
            return JWT.create()
                    .withIssuer("foro hub")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verify = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apisecret);

            verify = JWT.require(algorithm)
                    .withIssuer("foro hub")
                    .build()
                    .verify(token);
            verify.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verify.getSubject() == null) {
            throw new RuntimeException("Token inválido");
        }
        return verify.getSubject();
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
    }

}

