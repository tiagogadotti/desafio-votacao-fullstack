package com.desafio.votacao.sessao;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    public SessaoService(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    public Sessao findById(Long id) {
        return sessaoRepository.findById(id).get();
    }

    public Sessao save(Sessao sessao) {
        return sessaoRepository.save(sessao);
    }

    public Sessao startById(Long id, Integer duracao){
        Sessao sessao = sessaoRepository.findById(id).get();
        sessao.setInicio(LocalDateTime.now());
        sessao.setMinutosDuracao(duracao);
        return sessao;
    }

}
