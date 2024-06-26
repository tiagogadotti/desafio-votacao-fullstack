package com.desafio.votacao.sessao;

import com.desafio.votacao.pauta.Pauta;
import com.desafio.votacao.voto.Voto;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pauta pauta;

    private LocalDateTime inicio;

    private Integer minutosDuracao;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "sessao")
    private List<Voto> votos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public Integer getMinutosDuracao() {
        return minutosDuracao;
    }

    public void setMinutosDuracao(Integer minutosDuracao) {
        this.minutosDuracao = minutosDuracao;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sessao sessao = (Sessao) o;
        return Objects.equals(id, sessao.id) && Objects.equals(pauta, sessao.pauta) && Objects.equals(inicio, sessao.inicio) && Objects.equals(minutosDuracao, sessao.minutosDuracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pauta, inicio, minutosDuracao);
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "id=" + id +
                ", pauta=" + pauta +
                ", inicio=" + inicio +
                ", minutosDuracao=" + minutosDuracao +
                '}';
    }
}
