package com.aluracurso.forohub.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardar
        usuario.setClave(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
}