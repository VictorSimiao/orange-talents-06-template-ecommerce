package br.com.zupacademy.victor.mercadolivre.api.dto.response;

import br.com.zupacademy.victor.mercadolivre.domain.model.ImagemProduto;

public class ProdutoImagemResponse {

	private String link;

	public ProdutoImagemResponse(ImagemProduto imagemProduto) {
		this.link = imagemProduto.getLink();
	}

	public String getLink() {
		return link;
	}
}
