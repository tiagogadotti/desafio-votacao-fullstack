package com.desafio.votacao.pauta;

import org.springframework.stereotype.Service;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta findById(Long id) {
        return pautaRepository.findById(id).get();
    }

    public Pauta save(Pauta pauta) {
        return pautaRepository.save(pauta);
    }
}
