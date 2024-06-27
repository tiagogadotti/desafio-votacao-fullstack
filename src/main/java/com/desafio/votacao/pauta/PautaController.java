package com.desafio.votacao.pauta;

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
    @PostMapping("/pauta")
    public ResponseEntity<Pauta> save(@RequestBody Pauta pauta) {
        return ResponseEntity.ok(pautaService.save(pauta));
    }
}
