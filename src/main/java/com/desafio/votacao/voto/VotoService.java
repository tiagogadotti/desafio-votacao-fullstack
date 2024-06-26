package com.desafio.votacao.voto;

import com.desafio.votacao.associado.Associado;
import com.desafio.votacao.associado.AssociadoRepository;
import com.desafio.votacao.sessao.Sessao;
import com.desafio.votacao.sessao.SessaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotoService {

    private final VotoRepository votoRepository;
    public VotoService(VotoRepository votoRepository, SessaoRepository sessaoRepository, AssociadoRepository associadoRepository) {
        this.votoRepository = votoRepository;
    }

    public Voto save(Voto voto) {
        checkSessaoAberta(voto.getSessao());
        checkAssociadoJaVotou(voto.getAssociado(), voto.getSessao());
        return votoRepository.save(voto);
    }

    public List<Voto> findAllBySessaoId(Long id) {
        return votoRepository.findAllBySessaoId(id);
    }

    public List<Voto> findAllByAssociadoId(Long id) {
        return votoRepository.findAllByAssociadoId(id);
    }

    private void checkSessaoAberta(Sessao sessao){
        if (sessao == null){
            throw new VotoException("Sessão não encontrada");
        }
        if (sessao.getInicio() == null){
            throw new VotoException("Início da sessão não definido");
        }
        if(sessao.getMinutosDuracao() == null || sessao.getMinutosDuracao() <= 0){
           throw new VotoException("Duração da sessão não definida");
        }
        if (sessao.getInicio().plusMinutes(sessao.getMinutosDuracao()).isBefore(LocalDateTime.now())){
            throw new VotoException("Sessão encerrada para votação");
        }
    }

    private void checkAssociadoJaVotou(Associado associado, Sessao sessao) {
        if (associado == null){
            throw new VotoException("Associado não encontrado");
        }
        if (votoRepository.existsVotoByAssociadoIdAndSessaoId(associado.getId(), sessao.getId())){
            throw new VotoException("Associado ja votou para esta sessão");
        }
    }
}
