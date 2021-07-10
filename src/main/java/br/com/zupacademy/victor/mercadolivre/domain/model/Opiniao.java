package br.com.zupacademy.victor.mercadolivre.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Range(min = 1, max = 5)
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public Opiniao() {
		
	}

	public Opiniao(@NotNull @Range(min = 1, max = 5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull @Valid Usuario usuario,
			@NotNull @Valid Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

}
