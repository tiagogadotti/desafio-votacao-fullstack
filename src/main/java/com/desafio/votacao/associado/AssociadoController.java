package com.desafio.votacao.associado;

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
public class AssociadoController {
    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @Operation(summary = "Obter associado por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Associado encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Associado.class)) }),
            @ApiResponse(responseCode = "404", description = "Associado não encontrado", content = @Content)
    })
    @GetMapping(value = "/associado")
    public ResponseEntity<Associado> getAssociado(@RequestParam Long id) {
        return ResponseEntity.ok(associadoService.findById(id));
    }

    @Operation(summary = "Salvar novo associado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Associado salvo",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Associado.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping("/associado")
    public ResponseEntity<Associado> save(@RequestBody Associado associado) {
        return ResponseEntity.ok(associadoService.save(associado));
    }

    @Operation(summary = "Obter todos os associados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de associados encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Associado.class)) })
    })
    @GetMapping("/associados")
    public ResponseEntity<List<Associado>> findAll() {
        return ResponseEntity.ok(associadoService.findAll());
    }
}
