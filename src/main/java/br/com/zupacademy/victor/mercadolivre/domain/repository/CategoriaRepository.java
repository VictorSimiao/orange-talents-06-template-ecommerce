package br.com.zupacademy.victor.mercadolivre.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.victor.mercadolivre.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
