package com.aluracurso.forohub.domain.topico.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico (
    @NotNull
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fecha,
    String estado,
    String autor,
    String curso
){

}
