package com.desafio.votacao.voto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoService {

    private final VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public Voto save(Voto voto) {
        return votoRepository.save(voto);
    }

    public List<Voto> findAllBySessaoId(Long id){
        return votoRepository.findAllBySessaoId(id);
    }

    public List<Voto> findAllByAssociadoId(Long id){
        return votoRepository.findAllByAssociadoId(id);
    }
}
