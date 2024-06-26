package com.desafio.votacao.sessao;

import com.desafio.votacao.voto.Voto;
import com.desafio.votacao.voto.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SessaoController {

    private final SessaoService sessaoService;
    private final VotoService votoService;

    public SessaoController(SessaoService sessaoService, VotoService votoService) {
        this.sessaoService = sessaoService;
        this.votoService = votoService;
    }

    @GetMapping("/sessao")
    public ResponseEntity<Sessao> findById(Long id) {
        return ResponseEntity.ok(sessaoService.findById(id));
    }

    @PostMapping("/sessao")
    public ResponseEntity<Sessao> save(Sessao sessao) {
        return ResponseEntity.ok(sessaoService.save(sessao));
    }

    @PostMapping("/sessao/start")
    public ResponseEntity<Sessao> startById(@RequestParam Long id, @RequestParam Integer duracao){
        return ResponseEntity.ok(sessaoService.startById(id, duracao));
    }

}
