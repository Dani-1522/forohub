package com.aluracurso.forohub.domain.topico;

import com.aluracurso.forohub.domain.topico.dto.DatosActualizarTopico;
import com.aluracurso.forohub.domain.topico.dto.DatosRegistroTopico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name ="Topico")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensaje;
    @NotBlank
    private String autor;
    @NotBlank
    private String curso;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private String estado;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public Topico(DatosRegistroTopico datosRegistroTopico){
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = datosRegistroTopico.estado();
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.curso();
    }
    public void actualizarDatos (DatosActualizarTopico datosActualizarTopico){
        if (datosActualizarTopico.titulo() != null){
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }

        if (datosActualizarTopico.estado() != null){
            this.estado = datosActualizarTopico.estado();
        }
        if (datosActualizarTopico.autor() != null){
            this.autor = datosActualizarTopico.autor();
        }
        if (datosActualizarTopico.curso() != null){
            this.curso = datosActualizarTopico.curso();
        }
    }
}