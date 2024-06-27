package com.desafio.votacao.voto;

import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VotoController {

    private final VotoService votoService;
    private final VotoMapper votoMapper;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    public VotoController(VotoService votoService, VotoMapper votoMapper, DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration) {
        this.votoService = votoService;
        this.votoMapper = votoMapper;
        this.dataSourceTransactionManagerAutoConfiguration = dataSourceTransactionManagerAutoConfiguration;
    }

    @PostMapping("/voto")
    public ResponseEntity<VotoDTO> save(@RequestBody  VotoDTO votoDTO) {
        Voto voto = votoService.save(votoMapper.fromDTO(votoDTO));
        return ResponseEntity.ok(votoMapper.fromObject(voto));
    }

    @GetMapping("/voto/sessao")
    public ResponseEntity<List<VotoDTO>> findAllBySessaoId(@RequestParam  Long id) {
        List<VotoDTO> votosDTO = votoService.findAllBySessaoId(id).stream().map(votoMapper::fromObject).toList();
        return ResponseEntity.ok(votosDTO);
    }

    @GetMapping("/voto/associado")
    public ResponseEntity<List<VotoDTO>> findAllByAssociadoId(@RequestParam  Long id) {
        List<VotoDTO> votosDTO = votoService.findAllByAssociadoId(id).stream().map(votoMapper::fromObject).toList();
        return ResponseEntity.ok(votosDTO);
    }


}
