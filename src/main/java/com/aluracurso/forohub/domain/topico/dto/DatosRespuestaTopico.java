package com.aluracurso.forohub.domain.topico.dto;

import com.aluracurso.forohub.domain.topico.Topico;


import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String estado,
        String autor,
        String curso) {

    public DatosRespuestaTopico (Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado(),
                topico.getAutor(), topico.getCurso());
    }
}
