package com.desafio.votacao.cpf;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CpfController {

    private final CpfService cpfService;
    Map<String, String> statusCPF = new HashMap<>();

    public CpfController(CpfService cpfService) {
        this.cpfService = cpfService;
    }

    @PostMapping(value = "/cpf/validar", consumes = {"text/plain"})
    public ResponseEntity<Map<String, String>> validar(@RequestBody String cpf){

        if (cpfService.validar(cpf)){
            return ResponseEntity.ok(cpfService.ableToVote(cpf));
        }
        return ResponseEntity.notFound().build();
    }
}