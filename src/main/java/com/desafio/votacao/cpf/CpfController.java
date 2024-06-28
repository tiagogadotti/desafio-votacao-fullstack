package com.desafio.votacao.cpf;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CpfController {

    private final CpfService cpfService;

    public CpfController(CpfService cpfService) {
        this.cpfService = cpfService;
    }

    @Operation(summary = "Valida um CPF e verifica se é apto para votar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CPF válido e status de aptidão para votar retornado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)) }),
            @ApiResponse(responseCode = "404", description = "CPF inválido", content = @Content)
    })
    @PostMapping(value = "/cpf/validar", consumes = {"text/plain"})
    public ResponseEntity<Map<String, String>> validar(@RequestBody String cpf){
        if (cpfService.validar(cpf)){
            return ResponseEntity.ok(cpfService.ableToVote());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
