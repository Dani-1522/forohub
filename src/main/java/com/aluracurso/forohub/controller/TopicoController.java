package com.aluracurso.forohub.controller;

import com.aluracurso.forohub.model.Topico;
import com.aluracurso.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    public ResponseEntity<?> resgitroTopico(@RequestBody @Valid Topico topico) {
        if (topicoRepository.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El topico ya existe");
        }
        Topico nuevoTopico = topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTopico);
    }

    @GetMapping
    public ResponseEntity<?> listarTopicos() {
        return ResponseEntity.ok(topicoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDetallesTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(topico.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topico no encontrado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody @Valid Topico topicoActualizado) {
        Optional<Topico> topicoExistete = topicoRepository.findById(id);
        if (topicoExistete.isPresent()) {
            Topico topico = topicoExistete.get();
            topico.setTitulo(topicoActualizado.getTitulo());
            topico.setMensaje(topicoActualizado.getMensaje());
            topico.setAutor(topicoActualizado.getAutor());
            topico.setCurso(topicoActualizado.getCurso());
            topicoRepository.save(topico);
            return ResponseEntity.ok(topico);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topico no encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topico no encontrado");
    }
}

