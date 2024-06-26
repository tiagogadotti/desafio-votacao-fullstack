package com.desafio.votacao.voto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping("/voto")
    public ResponseEntity<Voto> save(Voto voto){
        return ResponseEntity.ok(votoService.save(voto));
    }

    @GetMapping("/voto/sessao")
    public ResponseEntity<List<Voto>> findAllBySessaoId(Long id){
        return ResponseEntity.ok(votoService.findAllBySessaoId(id));
    }

    @GetMapping("/voto/associado")
    public ResponseEntity<List<Voto>> findAllByAssociadoId(Long id){
        return ResponseEntity.ok(votoService.findAllByAssociadoId(id));
    }


}
