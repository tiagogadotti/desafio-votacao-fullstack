package com.desafio.votacao.associado;

import com.desafio.votacao.cpf.CpfService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssociadoServiceTest {

    @Mock
    private AssociadoRepository associadoRepository;

    @Mock
    private CpfService cpfService;

    @InjectMocks
    private AssociadoService associadoService;


    @Test
    public void testFindById() {
        Associado associado = new Associado();
        associado.setId(1L);
        when(associadoRepository.findById(1L)).thenReturn(Optional.of(associado));
        Associado result = associadoService.findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindById_NotFound() {
        when(associadoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> associadoService.findById(1L));
    }

    @Test
    public void salvarAssociadoValido() {
        Associado associado = new Associado();
        associado.setCpf("12345678909");
        when(cpfService.ableToVote()).thenReturn(Map.of("status", "ABLE_TO_VOTE"));
        when(cpfService.validar("12345678909")).thenReturn(true);
        when(associadoRepository.save(any(Associado.class))).thenReturn(associado);

        Associado result = associadoService.save(associado);
        assertTrue(result.isAptoParaVotar());
    }

    @Test
    public void quandoAssociadoInvalidoLancarExcecao() {
        Associado associado = new Associado();
        associado.setCpf("12345678909");
        when(cpfService.ableToVote()).thenReturn(Map.of("status", "ABLE_TO_VOTE"));
        when(cpfService.validar("12345678909")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> associadoService.save(associado));
    }

    @Test
    public void findAll() {
        List<Associado> associados = List.of(new Associado(), new Associado());
        when(associadoRepository.findAll()).thenReturn(associados);

        List<Associado> result = associadoService.findAll();
        assertEquals(2, result.size());
    }

}
