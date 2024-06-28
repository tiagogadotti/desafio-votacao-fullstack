package com.desafio.votacao.voto;

import com.desafio.votacao.associado.AssociadoService;
import com.desafio.votacao.sessao.SessaoService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class VotoMapper {

    private final AssociadoService associadoService;
    private final SessaoService sessaoService;

    public VotoMapper(AssociadoService associadoService, SessaoService sessaoService) {
        this.associadoService = associadoService;
        this.sessaoService = sessaoService;
    }

    public VotoDTO fromObject (Voto voto){
        VotoDTO votoDTO = new VotoDTO();
        votoDTO.setOpcao(String.valueOf(voto.getOpcao()));
        votoDTO.setAssociadoId(voto.getAssociado().getId());
        votoDTO.setSessaoId(voto.getSessao().getId());
        return votoDTO;
    }

    public Voto fromDTO (VotoDTO votoDTO){
        Voto voto = new Voto();
        voto.setOpcao(VotoOpcao.valueOf(votoDTO.getOpcao()));
        try{
            voto.setAssociado(associadoService.findById(votoDTO.getAssociadoId()));
        }catch (NoSuchElementException nsee){
            throw new VotoException("Associado não encontrato", nsee);
        }
        try{
            voto.setSessao(sessaoService.findById(votoDTO.getSessaoId()));
        }catch (NoSuchElementException nsee){
            throw new VotoException("Sessao não encontrada", nsee);
        }
        return voto;
    }
}
