package br.com.zupacademy.victor.mercadolivre.api.dto.request;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zupacademy.victor.mercadolivre.api.validation.annotations.ValorUnico;
import br.com.zupacademy.victor.mercadolivre.domain.model.Categoria;
import br.com.zupacademy.victor.mercadolivre.domain.repository.CategoriaRepository;

public class CategoriaRequest {

	@NotBlank
	@ValorUnico(nomeDoCampo = "nome", classeDeDominio = Categoria.class)
	private String nome;
	@Positive
	private Integer idCategoriaAncestral;


	public CategoriaRequest(@NotBlank String nome, @Positive Integer idCategoriaAncestral) {
		this.nome = nome;
		this.idCategoriaAncestral = idCategoriaAncestral;
	}

	public Categoria toModel(CategoriaRepository repository) {
		Categoria categoria = new Categoria(nome);
		if (idCategoriaAncestral != null) {
			Optional<Categoria> possivelCategoria = repository.findById(idCategoriaAncestral);
			Assert.state(possivelCategoria.isPresent(), "Informe um id de categoria válido");
			categoria.adicionarCategoriaAncestral(possivelCategoria.get());
		}
		return categoria;
	}

}
