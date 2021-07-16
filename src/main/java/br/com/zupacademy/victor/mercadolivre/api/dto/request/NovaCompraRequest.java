package br.com.zupacademy.victor.mercadolivre.api.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.victor.mercadolivre.api.validation.annotations.ExisteId;
import br.com.zupacademy.victor.mercadolivre.domain.model.Compra;
import br.com.zupacademy.victor.mercadolivre.domain.model.GatewayPagamento;
import br.com.zupacademy.victor.mercadolivre.domain.model.Produto;
import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;
import br.com.zupacademy.victor.mercadolivre.domain.repository.ProdutoRepository;

public class NovaCompraRequest {

	@Positive
	@NotNull
	private Integer quantidade;
	@NotNull
	@ExisteId(nomeDoCampo = "id", classeDeDominio = Produto.class)
	private Integer idProduto;
	@NotNull
	private GatewayPagamento gatewayPagamento;

	public NovaCompraRequest(@Positive @NotNull Integer quantidade, @NotNull Integer idProduto,
			@NotNull GatewayPagamento gatewayPagamento) {
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gatewayPagamento = gatewayPagamento;
	}

	public Compra toModel(ProdutoRepository produtoRepository, Usuario usuario) {
		Produto produto = produtoRepository.findById(idProduto)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
		return new Compra(quantidade, produto, usuario, gatewayPagamento);
	}

}
