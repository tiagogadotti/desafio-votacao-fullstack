package com.desafio.votacao.voto;

import com.desafio.votacao.associado.Associado;
import com.desafio.votacao.sessao.Sessao;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VotoOpcao opcao;

    @ManyToOne
    private Associado associado;

    @ManyToOne
    private Sessao sessao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VotoOpcao getOpcao() {
        return opcao;
    }

    public void setOpcao(VotoOpcao opcao) {
        this.opcao = opcao;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return Objects.equals(id, voto.id) && opcao == voto.opcao && Objects.equals(associado, voto.associado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, opcao, associado);
    }

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", opcao=" + opcao +
                ", associado=" + associado +
                '}';
    }
}
