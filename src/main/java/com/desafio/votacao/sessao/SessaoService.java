package com.desafio.votacao.sessao;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    public SessaoService(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    public Sessao findById(Long id) {
        return sessaoRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Sessao> findAll() {
        return sessaoRepository.findAll();
    }

    public Sessao findByPautaId(Long id) {
        try {
            return sessaoRepository.findByPautaId(id).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            throw new SessaoException("Pauta não encontrada", e);
        }
    }

    public Sessao save(Sessao sessao) {
        if (sessao.getMinutosDuracao() == null) {
            sessao.setMinutosDuracao(1);
        }
        if (sessao.getInicio() == null) {
            sessao.setInicio(LocalDateTime.now());
        }
        return sessaoRepository.save(sessao);
    }
}
