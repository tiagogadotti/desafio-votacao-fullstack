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
        if (sessao.getMinutosDuracao() == null){
            sessao.setMinutosDuracao(1);
        }
        if(sessao.getInicio() == null){
            sessao.setInicio(LocalDateTime.now());
        }
        return sessaoRepository.save(sessao);
    }
}
