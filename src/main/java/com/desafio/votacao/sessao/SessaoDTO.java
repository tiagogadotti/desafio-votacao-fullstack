package com.desafio.votacao.sessao;

import java.time.LocalDateTime;

public class SessaoDTO {

    private Long id;
    private Long pautaId;
    private LocalDateTime inicio;
    private Integer duracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "SessaoDTO{" +
                "id=" + id +
                ", pautaId=" + pautaId +
                ", inicio=" + inicio +
                ", duracao=" + duracao +
                '}';
    }
}
