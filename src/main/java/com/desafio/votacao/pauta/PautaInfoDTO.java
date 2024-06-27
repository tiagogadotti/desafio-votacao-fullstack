package com.desafio.votacao.pauta;

import java.time.LocalDateTime;

public class PautaInfoDTO {
    private Long pautaId;
    private String pautaTitulo;
    private String pautaDescricao;
    private Long sessaoId;
    private LocalDateTime sessaoInicio;
    private Integer sessaoDuracao;
    private Long sessaoTotalVotosSim;
    private Long sessaoTotalVotosNao;

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public String getPautaTitulo() {
        return pautaTitulo;
    }

    public void setPautaTitulo(String pautaTitulo) {
        this.pautaTitulo = pautaTitulo;
    }

    public String getPautaDescricao() {
        return pautaDescricao;
    }

    public void setPautaDescricao(String pautaDescricao) {
        this.pautaDescricao = pautaDescricao;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public LocalDateTime getSessaoInicio() {
        return sessaoInicio;
    }

    public void setSessaoInicio(LocalDateTime sessaoInicio) {
        this.sessaoInicio = sessaoInicio;
    }

    public Integer getSessaoDuracao() {
        return sessaoDuracao;
    }

    public void setSessaoDuracao(Integer sessaoDuracao) {
        this.sessaoDuracao = sessaoDuracao;
    }

    public Long getSessaoTotalVotosSim() {
        return sessaoTotalVotosSim;
    }

    public void setSessaoTotalVotosSim(Long sessaoTotalVotosSim) {
        this.sessaoTotalVotosSim = sessaoTotalVotosSim;
    }

    public Long getSessaoTotalVotosNao() {
        return sessaoTotalVotosNao;
    }

    public void setSessaoTotalVotosNao(Long sessaoTotalVotosNao) {
        this.sessaoTotalVotosNao = sessaoTotalVotosNao;
    }

    @Override
    public String toString() {
        return "PautaInfoDTO{" +
                "pautaId=" + pautaId +
                ", pautaTitulo='" + pautaTitulo + '\'' +
                ", pautaDescricao='" + pautaDescricao + '\'' +
                ", sessaoId=" + sessaoId +
                ", sessaoInicio=" + sessaoInicio +
                ", sessaoDuracao=" + sessaoDuracao +
                ", sessaoTotalVotosSim=" + sessaoTotalVotosSim +
                ", sessaoTotalVotosNao=" + sessaoTotalVotosNao +
                '}';
    }
}
