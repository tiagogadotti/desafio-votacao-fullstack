package com.desafio.votacao.sessao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SessaoController {

    private final SessaoService sessaoService;
    private final SessaoMapper sessaoMapper;

    public SessaoController(SessaoService sessaoService, SessaoMapper sessaoMapper) {
        this.sessaoService = sessaoService;
        this.sessaoMapper = sessaoMapper;
    }

    @Operation(summary = "Encontra uma sessão por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessaoDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Sessão não encontrada", content = @Content)
    })
    @GetMapping("/sessao")
    public ResponseEntity<SessaoDTO> findById(@RequestParam Long id) {
        return ResponseEntity.ok(sessaoMapper.fromObject(sessaoService.findById(id)));
    }

    @Operation(summary = "Encontra todas as sessões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sessões encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessaoDTO.class)) })
    })
    @GetMapping("/sessoes")
    public ResponseEntity<List<SessaoDTO>> findAll() {
        List<SessaoDTO> sessoes = sessaoService.findAll().stream().map(sessaoMapper::fromObject).toList();
        return ResponseEntity.ok(sessoes);
    }

    @Operation(summary = "Encontra uma sessão por ID da pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessaoDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Sessão não encontrada", content = @Content)
    })
    @GetMapping("/sessao/pauta/{id}")
    public ResponseEntity<SessaoDTO> findByPautaId(@PathVariable Long id) {
        return ResponseEntity.ok(sessaoMapper.fromObject(sessaoService.findByPautaId(id)));
    }

    @Operation(summary = "Salva uma nova sessão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão salva",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessaoDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping("/sessao")
    public ResponseEntity<SessaoDTO> save(@RequestBody SessaoDTO sessaoDTO) {
        Sessao sessao = sessaoMapper.fromDTO(sessaoDTO);
        return ResponseEntity.ok(sessaoMapper.fromObject(sessaoService.save(sessao)));
    }
}
