package com.desafio.votacao.cpf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CpfServiceTest {
    private CpfService cpfService;

    @BeforeEach
    public void setUp() {
        cpfService = new CpfService();
    }

    @Test
    public void retornarAbleToVoteOuUnableToVote() {
        Map<String, String> result = cpfService.ableToVote();
        assertTrue(result.containsKey("status"));
        assertTrue(result.get("status").equals("ABLE_TO_VOTE") || result.get("status").equals("UNABLE_TO_VOTE"));
    }

    @Test
    public void validarCpfValido() {
        assertTrue(cpfService.validar("873.666.360-37"));
        assertTrue(cpfService.validar("79416178039"));
    }

    @Test
    public void validarCpfInvalido() {
        assertFalse(cpfService.validar("123.456.789-00"));
        assertFalse(cpfService.validar("12345678910"));
    }


    @Test
    public void validarCpfInvalidoMaiorMenorOuNaoNumerico() {
        assertFalse(cpfService.validar("123.456.78"));
        assertFalse(cpfService.validar("asdbc"));
        assertFalse(cpfService.validar("794161780390"));
    }

    @Test
    public void validarCpfNulo() {
        assertFalse(cpfService.validar(null));
    }
}