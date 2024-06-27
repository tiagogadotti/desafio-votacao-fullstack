package com.desafio.votacao.cpf;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class CpfService {

    private static int TAMANHO_CPF = 11;

    public Map<String, String> ableToVote(String cpf) {
        Map<String, String> statusCPF = new HashMap<>();
        if (Math.random() > 0.3) {
            statusCPF.put("status", "ABLE_TO_VOTE");
        } else {
            statusCPF.put("status", "UNABLE_TO_VOTE");
        }
        return statusCPF;
    }

    public boolean validar(String numero) {
        numero = numero.replaceAll("[^0-9]", "");

        System.out.println(numero);
        if (numero == null || numero.length() != TAMANHO_CPF) {
            return false;
        }
        if (!numerosDiferentes(numero)) {
            return false;
        }
        int dv1 = calcularDigitoVerificador(numero, 9, 10);
        int dv2 = calcularDigitoVerificador(numero, 10, 11);

        if (dv1 != numero.charAt(9) - '0' || dv2 != numero.charAt(10) - '0') {
            return false;
        }
        return true;
    }

    private int calcularDigitoVerificador(String numero, int tamanho, int peso) {
        int soma = 0;
        for (int i = 0; i < tamanho; i++) {
            soma += (numero.charAt(i) - '0') * (peso - i);
        }
        int resultado = 11 - soma % 11;
        return resultado > 9 ? 0 : resultado;
    }

    private boolean numerosDiferentes(String numero) {
        char primeiroDigito = numero.charAt(0);
        for (int i = 1; i < numero.length(); i++) {
            if (numero.charAt(i) != primeiroDigito) {
                return true;
            }
        }
        return false;
    }

}
