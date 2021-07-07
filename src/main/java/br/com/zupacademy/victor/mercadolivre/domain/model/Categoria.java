package br.com.zupacademy.victor.mercadolivre.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoriaAncestral;

	@Deprecated
	public Categoria() {

	}

	public Categoria(@NotBlank String nome) {
		this.nome = nome;
	}

	public void adicionarCategoriaAncestral(Categoria categoria) {
		this.categoriaAncestral = categoria;

	}

}
