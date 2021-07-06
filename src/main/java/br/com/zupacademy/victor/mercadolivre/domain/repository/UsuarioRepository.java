package br.com.zupacademy.victor.mercadolivre.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
