package com.desafio.votacao.associado;

import com.desafio.votacao.api.ApiError;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class AssociadoController {
    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @GetMapping(value = "/associado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Associado> getAssociado(@RequestParam Long id) {
        return ResponseEntity.ok(associadoService.getAssociado(id));
    }

    @PostMapping("/associado")
    public ResponseEntity<Associado> save(@RequestBody Associado associado) {
        return ResponseEntity.ok(associadoService.save(associado));
    }
}

