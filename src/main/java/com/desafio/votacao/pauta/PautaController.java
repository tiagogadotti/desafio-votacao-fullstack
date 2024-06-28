package com.desafio.votacao.pauta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PautaController {

    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @Operation(summary = "Encontra uma pauta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pauta.class)) }),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada", content = @Content)
    })
    @GetMapping("/pauta")
    public ResponseEntity<Pauta> findById(@RequestParam Long id) {
        return ResponseEntity.ok(pautaService.findById(id));
    }

    @Operation(summary = "Encontra todas as pautas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pautas encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pauta.class)) })
    })
    @GetMapping("/pautas")
    public ResponseEntity<List<Pauta>> findAll() {
        return ResponseEntity.ok(pautaService.findAll());
    }

    @Operation(summary = "Encontra todas as informações das pautas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações das pautas encontradas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PautaInfoDTO.class)) })
    })
    @GetMapping("/pautas/info")
    public ResponseEntity<List<PautaInfoDTO>> findAllInfo() {
        List<PautaInfoDTO> pautaInfoDTOS = pautaService.findAllInfo()
                .stream()
                .sorted(Comparator
                        .comparingInt((PautaInfoDTO pauta) -> getStatus(pauta, LocalDateTime.now()))
                        .thenComparing(PautaInfoDTO::getPautaTitulo))
                .toList();

        return ResponseEntity.ok(pautaInfoDTOS);
    }

    private int getStatus(PautaInfoDTO pauta, LocalDateTime now) {
        if (pauta.getSessaoInicio() == null) {
            return 2; // Pendente
        } else if (pauta.getSessaoInicio().plusMinutes(pauta.getSessaoDuracao()).isBefore(now)) {
            return 3; // Encerrada
        } else {
            return 1; // Em andamento
        }
    }

    @Operation(summary = "Salva uma nova pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta salva",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pauta.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping("/pauta")
    public ResponseEntity<Pauta> save(@RequestBody Pauta pauta) {
        return ResponseEntity.ok(pautaService.save(pauta));
    }
}
