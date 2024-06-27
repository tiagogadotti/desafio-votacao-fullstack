package com.desafio.votacao.pauta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/pautas")
    public ResponseEntity<List<Pauta>> findAll(){
        return ResponseEntity.ok(pautaService.findAll());
    }

    @GetMapping("/pautas/info")
    public ResponseEntity<List<PautaInfoDTO>> findAllInfo(){
        return ResponseEntity.ok(pautaService.findAllInfo());
    }
    @PostMapping("/pauta")
    public ResponseEntity<Pauta> save(@RequestBody Pauta pauta) {
        System.out.println(pauta);
        return ResponseEntity.ok(pautaService.save(pauta));
    }
}
