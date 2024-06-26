package com.desafio.votacao.associado;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CPF {

    public CPF(){}

    public CPF(String numero) {
        this.numero = numero;
    }

    @Column(name = "cpf")
    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
