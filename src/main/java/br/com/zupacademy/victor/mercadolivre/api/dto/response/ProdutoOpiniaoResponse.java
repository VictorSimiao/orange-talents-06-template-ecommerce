package br.com.zupacademy.victor.mercadolivre.api.dto.response;

import br.com.zupacademy.victor.mercadolivre.domain.model.Opiniao;

public class ProdutoOpiniaoResponse {

	private Integer nota;
	private String titulo;
	private String descricao;

	public ProdutoOpiniaoResponse(Opiniao opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
}
