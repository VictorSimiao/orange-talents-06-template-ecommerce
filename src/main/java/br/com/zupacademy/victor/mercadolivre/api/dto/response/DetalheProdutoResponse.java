package br.com.zupacademy.victor.mercadolivre.api.dto.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zupacademy.victor.mercadolivre.domain.model.Produto;

public class DetalheProdutoResponse {

	private Set<ProdutoImagemResponse> imagens = new HashSet<ProdutoImagemResponse>();
	private String nome;
	private BigDecimal preco;
	private Set<ProdutoCaracteristicaResponse> caracteristicas = new HashSet<ProdutoCaracteristicaResponse>();
	private String descricao;
	private Double media;
	private Long totalNotas;
	private List<ProdutoOpiniaoResponse> opinioes = new ArrayList<ProdutoOpiniaoResponse>();
	private Set<ProdutoPerguntaResponse> peguntas = new HashSet<ProdutoPerguntaResponse>();

	public DetalheProdutoResponse(Produto produto) {
		this.imagens = produto.getImagens().stream().map(ProdutoImagemResponse::new).collect(Collectors.toSet());
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.caracteristicas = produto.getCaracteristicas().stream().map(ProdutoCaracteristicaResponse::new)
				.collect(Collectors.toSet());
		this.descricao = produto.getDescricao();
		this.media = produto.getMedia();
		this.totalNotas = produto.getTotalNotas();
		this.opinioes = produto.getOpinioes().stream().map(ProdutoOpiniaoResponse::new).collect(Collectors.toList());
		this.peguntas = produto.getPeguntas().stream().map(ProdutoPerguntaResponse::new).collect(Collectors.toSet());
	}

	public Set<ProdutoImagemResponse> getImagens() {
		return imagens;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Set<ProdutoCaracteristicaResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getMedia() {
		return media;
	}

	public Long getTotalNotas() {
		return totalNotas;
	}

	public List<ProdutoOpiniaoResponse> getOpinioes() {
		return opinioes;
	}

	public Set<ProdutoPerguntaResponse> getPeguntas() {
		return peguntas;
	}

}
