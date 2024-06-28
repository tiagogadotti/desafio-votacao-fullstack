package com.desafio.votacao.pauta;

import com.desafio.votacao.sessao.Sessao;
import com.desafio.votacao.sessao.SessaoService;
import com.desafio.votacao.voto.Voto;
import com.desafio.votacao.voto.VotoOpcao;
import com.desafio.votacao.voto.VotoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoService sessaoService;

    @Mock
    private VotoService votoService;

    @InjectMocks
    private PautaService pautaService;

    @Test
    void deveEncontrarPautaPorId() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));

        Pauta result = pautaService.findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void deveLancarExcecaoQuandoPautaNaoForEncontradaPorId() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> pautaService.findById(1L));
    }

    @Test
    void deveSalvarPauta() {
        Pauta pauta = new Pauta();
        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        Pauta result = pautaService.save(pauta);
        assertNotNull(result);
    }

    @Test
    void deveEncontrarTodasAsPautas() {
        List<Pauta> pautas = List.of(new Pauta(), new Pauta());
        when(pautaRepository.findAll()).thenReturn(pautas);

        List<Pauta> result = pautaService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void deveEncontrarTodasAsInfosDasPautas() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTitulo("Título");
        pauta.setDescricao("Descrição");

        List<Pauta> pautas = List.of(pauta);
        when(pautaRepository.findAll()).thenReturn(pautas);

        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setMinutosDuracao(60);
        sessao.setInicio(null);
        when(sessaoService.findByPautaId(1L)).thenReturn(sessao);

        List<Voto> votos = new ArrayList<>();
        Voto votoSim = new Voto();
        votoSim.setOpcao(VotoOpcao.SIM);
        Voto votoNao = new Voto();
        votoNao.setOpcao(VotoOpcao.NAO);
        votos.add(votoSim);
        votos.add(votoNao);
        when(votoService.findAllBySessaoId(1L)).thenReturn(votos);

        List<PautaInfoDTO> result = pautaService.findAllInfo();
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getPautaId());
        assertEquals("Título", result.get(0).getPautaTitulo());
        assertEquals("Descrição", result.get(0).getPautaDescricao());
        assertEquals(1L, result.get(0).getSessaoId());
        assertEquals(60, result.get(0).getSessaoDuracao());
        assertEquals(1, result.get(0).getSessaoTotalVotosSim());
        assertEquals(1, result.get(0).getSessaoTotalVotosNao());
    }
}
