package br.com.zupacademy.victor.mercadolivre.api.dto.request;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.victor.mercadolivre.api.validation.annotations.ExisteId;
import br.com.zupacademy.victor.mercadolivre.domain.model.Categoria;
import br.com.zupacademy.victor.mercadolivre.domain.model.Produto;
import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;
import br.com.zupacademy.victor.mercadolivre.domain.repository.CategoriaRepository;

public class ProdutoRequest {

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@PositiveOrZero
	private Integer quantidade;

	@NotBlank
	@Size(max = 100)
	private String descricao;

	@NotNull
	@ExisteId(nomeDoCampo = "id", classeDeDominio = Categoria.class)
	private Integer idCategoria;
	
	@Size
	@Valid
	Set<CaracteristicaRequest> caracteristicas = new HashSet<CaracteristicaRequest>();
	
	
	public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidade, @NotBlank @Size(max = 100) String descricao,
			@NotNull Integer idCategoria, @Size @Valid Set<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas = caracteristicas;
	}


	public Produto toModel(Usuario usuarioDono, CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.findById(idCategoria)
				.orElseThrow(()-> new IllegalStateException("id da Categoria é inválido"));
		return new Produto(nome, valor, quantidade, descricao, caracteristicas, usuarioDono, categoria);
	}


	
	
	

	
}
