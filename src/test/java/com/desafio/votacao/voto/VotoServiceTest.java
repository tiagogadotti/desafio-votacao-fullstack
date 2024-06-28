package com.desafio.votacao.voto;

import com.desafio.votacao.associado.Associado;
import com.desafio.votacao.sessao.Sessao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private VotoService votoService;


    @Test
    void deveSalvarVoto() {
        Voto voto = new Voto();
        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setInicio(LocalDateTime.now().minusMinutes(1));
        sessao.setMinutosDuracao(10);
        voto.setSessao(sessao);

        Associado associado = new Associado();
        associado.setId(1L);
        associado.setAptoParaVotar(true);
        voto.setAssociado(associado);

        when(votoRepository.save(any(Voto.class))).thenReturn(voto);
        when(votoRepository.existsVotoByAssociadoIdAndSessaoId(associado.getId(), sessao.getId())).thenReturn(false);

        Voto result = votoService.save(voto);
        assertNotNull(result);
    }

    @Test
    void deveLancarExcecaoQuandoSessaoNaoFornecida() {
        Voto voto = new Voto();
        voto.setSessao(null);

        Associado associado = new Associado();
        associado.setAptoParaVotar(true);
        voto.setAssociado(associado);

        VotoException thrown = assertThrows(VotoException.class, () -> votoService.save(voto));

        assertEquals("Sessão não encontrada", thrown.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoSessaoEncerrada() {
        Voto voto = new Voto();
        Sessao sessao = new Sessao();
        sessao.setInicio(LocalDateTime.now().minusMinutes(15));
        sessao.setMinutosDuracao(10);
        voto.setSessao(sessao);

        Associado associado = new Associado();
        associado.setAptoParaVotar(true);
        voto.setAssociado(associado);

        VotoException thrown = assertThrows(VotoException.class, () -> votoService.save(voto));

        assertEquals("Sessão encerrada para votação", thrown.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoAssociadoJaVotou() {
        Voto voto = new Voto();
        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setInicio(LocalDateTime.now().minusMinutes(1));
        sessao.setMinutosDuracao(10);
        voto.setSessao(sessao);

        Associado associado = new Associado();
        associado.setId(1L);
        associado.setAptoParaVotar(true);
        voto.setAssociado(associado);

        when(votoRepository.existsVotoByAssociadoIdAndSessaoId(associado.getId(), sessao.getId())).thenReturn(true);

        VotoException thrown = assertThrows(VotoException.class, () -> votoService.save(voto));

        assertEquals("Associado ja votou para esta sessão", thrown.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoAssociadoNaoAptoParaVotar() {
        Voto voto = new Voto();
        Sessao sessao = new Sessao();
        sessao.setInicio(LocalDateTime.now().minusMinutes(1));
        sessao.setMinutosDuracao(10);
        voto.setSessao(sessao);

        Associado associado = new Associado();
        associado.setAptoParaVotar(false);
        voto.setAssociado(associado);

        VotoException thrown = assertThrows(VotoException.class, () -> votoService.save(voto));

        assertEquals("Associado não é apto para votar", thrown.getMessage());
    }

    @Test
    void deveEncontrarTodosVotosPorSessaoId() {
        List<Voto> votos = List.of(new Voto(), new Voto());
        when(votoRepository.findAllBySessaoId(1L)).thenReturn(votos);

        List<Voto> result = votoService.findAllBySessaoId(1L);
        assertEquals(2, result.size());
    }

    @Test
    void deveEncontrarTodosVotosPorAssociadoId() {
        List<Voto> votos = List.of(new Voto(), new Voto());
        when(votoRepository.findAllByAssociadoId(1L)).thenReturn(votos);

        List<Voto> result = votoService.findAllByAssociadoId(1L);
        assertEquals(2, result.size());
    }
}
