package br.com.zupacademy.victor.mercadolivre.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String titulo;

	@CreationTimestamp
	@PastOrPresent
	private LocalDateTime registradaEm;

	@NotNull
	@Valid
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	@NotNull
	@Valid
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;

	@Deprecated
	public Pergunta() {

	}

	public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;

	}

	public String getEmailUsuario() {
		return usuario.getEmail();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getEmailDonoProduto() {
		return produto.getEmailDono();
	}

}
