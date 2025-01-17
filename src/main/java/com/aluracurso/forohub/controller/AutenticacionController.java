package com.aluracurso.forohub.controller;

import com.aluracurso.forohub.domain.usuario.Usuario;
import com.aluracurso.forohub.domain.usuario.dto.DatosAuntenticacionUsuario;
import com.aluracurso.forohub.infra.segurity.DatosJWTToken;
import com.aluracurso.forohub.infra.segurity.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private  AuthenticationManager authenticationManager;

    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario (@RequestBody @Valid DatosAuntenticacionUsuario datosAuntenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAuntenticacionUsuario.nombre(),
                datosAuntenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var usuario = usuarioAutenticado.getPrincipal();
        var JWTtoken = tokenService.generarToken((Usuario) usuario);
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
