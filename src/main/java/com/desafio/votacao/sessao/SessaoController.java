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
    private final SessaoMapper sessaoMapper;

    public SessaoController(SessaoService sessaoService, VotoService votoService, SessaoMapper sessaoMapper) {
        this.sessaoService = sessaoService;
        this.votoService = votoService;
        this.sessaoMapper = sessaoMapper;
    }

    @GetMapping("/sessao")
    public ResponseEntity<SessaoDTO> findById(@RequestParam  Long id) {
        return ResponseEntity.ok(sessaoMapper.fromObject(sessaoService.findById(id)));
    }

    @PostMapping("/sessao")
    public ResponseEntity<SessaoDTO> save(@RequestBody SessaoDTO sessaoDTO) {
        System.out.println(sessaoDTO);
        Sessao sessao = sessaoMapper.fromDTO(sessaoDTO);
        return ResponseEntity.ok(sessaoMapper.fromObject(sessaoService.save(sessao)));
    }

}
