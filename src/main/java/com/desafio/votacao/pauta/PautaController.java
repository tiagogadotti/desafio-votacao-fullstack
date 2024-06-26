package com.desafio.votacao.pauta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PautaController {

    private final PautaService pautaService;


    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping("/pauta")
    public ResponseEntity<Pauta> findById(Long id) {
        return ResponseEntity.ok(pautaService.findById(id));
    }

    @PostMapping("/pauta")
    public ResponseEntity<Pauta> save(@RequestBody Pauta pauta) {
        return ResponseEntity.ok(pautaService.save(pauta));
    }
}
