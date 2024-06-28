package com.desafio.votacao.pauta;

import com.desafio.votacao.sessao.Sessao;
import com.desafio.votacao.sessao.SessaoService;
import com.desafio.votacao.voto.Voto;
import com.desafio.votacao.voto.VotoOpcao;
import com.desafio.votacao.voto.VotoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;
    private final SessaoService sessaoService;
    private final VotoService votoService;

    public PautaService(PautaRepository pautaRepository, SessaoService sessaoService, VotoService votoService) {
        this.pautaRepository = pautaRepository;
        this.sessaoService = sessaoService;
        this.votoService = votoService;
    }

    public Pauta findById(Long id) {
        return pautaRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Pauta save(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    public List<PautaInfoDTO> findAllInfo(){
        List<Pauta> pautas = pautaRepository.findAll();
        List<PautaInfoDTO> pautaInfoDTOS = new ArrayList<>();
        pautas.forEach(pauta -> {
            PautaInfoDTO pautaInfoDTO = new PautaInfoDTO();
            pautaInfoDTO.setPautaId(pauta.getId());
            pautaInfoDTO.setPautaTitulo(pauta.getTitulo());
            pautaInfoDTO.setPautaDescricao(pauta.getDescricao());
            try {
                Sessao sessao = sessaoService.findByPautaId(pauta.getId());
                pautaInfoDTO.setSessaoId(sessao.getId());
                pautaInfoDTO.setSessaoDuracao(sessao.getMinutosDuracao());
                pautaInfoDTO.setSessaoInicio(sessao.getInicio());
                List<Voto> votos = votoService.findAllBySessaoId(sessao.getId());
                pautaInfoDTO.setSessaoTotalVotosSim(votos.stream().filter(e -> e.getOpcao() == VotoOpcao.SIM).count());
                pautaInfoDTO.setSessaoTotalVotosNao(votos.stream().filter(e -> e.getOpcao() == VotoOpcao.NAO).count());
            }catch (Exception ignored) {}
            pautaInfoDTOS.add(pautaInfoDTO);
        });
        return pautaInfoDTOS;
    }
}
