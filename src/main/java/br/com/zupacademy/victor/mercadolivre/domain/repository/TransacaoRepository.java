package br.com.zupacademy.victor.mercadolivre.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.victor.mercadolivre.domain.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

}
