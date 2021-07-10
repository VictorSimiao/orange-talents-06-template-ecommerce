package br.com.zupacademy.victor.mercadolivre.api.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.victor.mercadolivre.domain.model.Opiniao;
import br.com.zupacademy.victor.mercadolivre.domain.model.Produto;
import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;

public class OpiniaoRequest {
	
	@NotNull
	@Range(min= 1, max= 5)
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String descricao;

	public OpiniaoRequest(@Range(min = 1, max = 5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Opiniao toModel(@NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
		return new Opiniao(nota,titulo,descricao,usuario, produto);
	}

}
