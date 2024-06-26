package com.desafio.votacao.sessao;

import com.desafio.votacao.pauta.PautaService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SessaoMapper {

    private final PautaService pautaService;

    public SessaoMapper(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    public SessaoDTO fromObject (Sessao sessao){
        SessaoDTO sessaoDTO = new SessaoDTO();
        sessaoDTO.setId(sessao.getId());
        sessaoDTO.setInicio(sessao.getInicio());
        sessaoDTO.setDuracao(sessao.getMinutosDuracao());
        sessaoDTO.setPautaId(sessao.getPauta().getId());
        return sessaoDTO;
    }

    public Sessao fromDTO (SessaoDTO sessaoDTO){
        Sessao sessao = new Sessao();
        sessao.setId(sessaoDTO.getId());
        sessao.setInicio(sessaoDTO.getInicio());
        sessao.setMinutosDuracao(sessaoDTO.getDuracao());
        try{
            sessao.setPauta(pautaService.findById(sessaoDTO.getPautaId()));
        }catch (NoSuchElementException nsee){
            throw new NoSuchElementException("Pauta não encontrada", nsee);
        }
        return sessao;
    }
}
