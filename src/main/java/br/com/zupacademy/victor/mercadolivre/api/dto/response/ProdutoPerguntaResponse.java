package br.com.zupacademy.victor.mercadolivre.api.dto.response;

import br.com.zupacademy.victor.mercadolivre.domain.model.Pergunta;

public class ProdutoPerguntaResponse {

	private String titulo;

	public ProdutoPerguntaResponse(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
	}

	public String getTitulo() {
		return titulo;
	}
}
