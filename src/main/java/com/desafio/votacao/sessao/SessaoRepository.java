package com.desafio.votacao.sessao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

}
