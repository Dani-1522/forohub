package com.aluracurso.forohub.repository;

import com.aluracurso.forohub.model.Topico;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(@NotBlank String titulo, @NotBlank String mensaje);
}
