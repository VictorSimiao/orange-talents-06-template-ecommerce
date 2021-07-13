package br.com.zupacademy.victor.mercadolivre.api.dto.response;

import br.com.zupacademy.victor.mercadolivre.domain.model.CaracteristicaProduto;

public class ProdutoCaracteristicaResponse {

	private String nome;
	private String descricao;

	public ProdutoCaracteristicaResponse(CaracteristicaProduto caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
