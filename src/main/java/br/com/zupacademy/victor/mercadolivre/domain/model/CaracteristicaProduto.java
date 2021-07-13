package br.com.zupacademy.victor.mercadolivre.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String nome;

	@NotBlank
	private String descricao;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;
	
	@Deprecated
	public CaracteristicaProduto() {

	}

	public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao, @NotNull Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
