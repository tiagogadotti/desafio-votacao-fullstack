package com.desafio.votacao.voto;

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
public class VotoController {

    private final VotoService votoService;
    private final VotoMapper votoMapper;

    public VotoController(VotoService votoService, VotoMapper votoMapper) {
        this.votoService = votoService;
        this.votoMapper = votoMapper;
    }

    @Operation(summary = "Salva um novo voto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voto salvo",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VotoDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping("/voto")
    public ResponseEntity<VotoDTO> save(@RequestBody VotoDTO votoDTO) {
        Voto voto = votoService.save(votoMapper.fromDTO(votoDTO));
        return ResponseEntity.ok(votoMapper.fromObject(voto));
    }

    @Operation(summary = "Encontra todos os votos por ID da sessão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de votos encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VotoDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Sessão não encontrada", content = @Content)
    })
    @GetMapping("/voto/sessao")
    public ResponseEntity<List<VotoDTO>> findAllBySessaoId(@RequestParam Long id) {
        List<VotoDTO> votosDTO = votoService.findAllBySessaoId(id).stream().map(votoMapper::fromObject).toList();
        return ResponseEntity.ok(votosDTO);
    }

    @Operation(summary = "Encontra todos os votos por ID do associado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de votos encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VotoDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Associado não encontrado", content = @Content)
    })
    @GetMapping("/voto/associado")
    public ResponseEntity<List<VotoDTO>> findAllByAssociadoId(@RequestParam Long id) {
        List<VotoDTO> votosDTO = votoService.findAllByAssociadoId(id).stream().map(votoMapper::fromObject).toList();
        return ResponseEntity.ok(votosDTO);
    }
}
