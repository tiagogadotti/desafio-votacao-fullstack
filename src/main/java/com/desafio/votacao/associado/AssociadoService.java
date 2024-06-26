package com.desafio.votacao.associado;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }


    public Associado findById(Long id) throws NoSuchElementException {
        return associadoRepository.findById(id).get();
    }

    public Associado save(Associado associado) {
        return associadoRepository.save(associado);
    }
}
