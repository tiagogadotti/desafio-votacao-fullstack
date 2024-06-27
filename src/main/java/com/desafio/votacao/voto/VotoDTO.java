package com.desafio.votacao.voto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VotoDTO {
    private Long associadoId;
    private Long sessaoId;
    private String opcao;

    public Long getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(Long associadoId) {
        this.associadoId = associadoId;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    @Override
    public String toString() {
        return "VotoDTO{" +
                "associadoId=" + associadoId +
                ", sessaoId=" + sessaoId +
                ", opcao=" + opcao +
                '}';
    }
}
