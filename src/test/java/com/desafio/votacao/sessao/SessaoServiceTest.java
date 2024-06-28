package com.desafio.votacao.sessao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @InjectMocks
    private SessaoService sessaoService;

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.openMocks(this); // Não é necessário com @ExtendWith(MockitoExtension.class)
    }

    @Test
    void deveEncontrarSessaoPorId() {
        Sessao sessao = new Sessao();
        sessao.setId(1L);
        when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));

        Sessao result = sessaoService.findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void deveLancarExcecaoQuandoSessaoNaoForEncontradaPorId() {
        when(sessaoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> sessaoService.findById(1L));
    }

    @Test
    void deveEncontrarTodasAsSessoes() {
        List<Sessao> sessoes = List.of(new Sessao(), new Sessao());
        when(sessaoRepository.findAll()).thenReturn(sessoes);

        List<Sessao> result = sessaoService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void deveEncontrarSessaoPorPautaId() {
        Sessao sessao = new Sessao();
        sessao.setId(1L);
        when(sessaoRepository.findByPautaId(1L)).thenReturn(Optional.of(sessao));

        Sessao result = sessaoService.findByPautaId(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void deveLancarExcecaoQuandoSessaoNaoForEncontradaPorPautaId() {
        when(sessaoRepository.findByPautaId(1L)).thenReturn(Optional.empty());

        assertThrows(SessaoException.class, () -> sessaoService.findByPautaId(1L));
    }

    @Test
    void deveSalvarSessaoComValoresPadrao() {
        Sessao sessao = new Sessao();
        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);

        Sessao result = sessaoService.save(sessao);
        assertNotNull(result);
        assertNotNull(result.getInicio());
        assertEquals(1, result.getMinutosDuracao());
    }

    @Test
    void deveSalvarSessaoComValoresFornecidos() {
        Sessao sessao = new Sessao();
        sessao.setMinutosDuracao(10);
        sessao.setInicio(LocalDateTime.of(2022, 1, 1, 0, 0));
        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);

        Sessao result = sessaoService.save(sessao);
        assertNotNull(result);
        assertEquals(10, result.getMinutosDuracao());
        assertEquals(LocalDateTime.of(2022, 1, 1, 0, 0), result.getInicio());
    }
}
