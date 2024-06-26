package com.desafio.votacao.voto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findAllByAssociadoId(Long associadoId);
    List<Voto> findAllBySessaoId(Long sessaoId);
}
