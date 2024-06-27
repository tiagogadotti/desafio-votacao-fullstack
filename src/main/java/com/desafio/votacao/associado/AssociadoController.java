package com.desafio.votacao.associado;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AssociadoController {
    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @GetMapping(value = "/associado")
    public ResponseEntity<Associado> getAssociado(@RequestParam Long id) {
        return ResponseEntity.ok(associadoService.findById(id));
    }

    @PostMapping("/associado")
    public ResponseEntity<Associado> save(@RequestBody Associado associado) {
        return ResponseEntity.ok(associadoService.save(associado));
    }

    @GetMapping("/associados")
    public ResponseEntity<List<Associado>> findAll() {
        return ResponseEntity.ok(associadoService.findAll());
    }
}

