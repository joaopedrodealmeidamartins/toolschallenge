package com.toolschallenge.repository;

import com.toolschallenge.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
