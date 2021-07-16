package br.com.zupacademy.victor.mercadolivre.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Positive
	@NotNull
	private Integer quantidade;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	@NotNull
	@Enumerated(EnumType.STRING)
	private GatewayPagamento gatewayPagamento;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusCompra status = StatusCompra.INICIADA;

	public Compra(@Positive @NotNull Integer quantidade, @NotNull Produto produto, @NotNull Usuario usuario,
			@NotNull GatewayPagamento gatewayPagamento) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.usuario = usuario;
		this.gatewayPagamento = gatewayPagamento;
	}

	public boolean abateEstoqueDoProduto() {
		return produto.abaterEstoque(quantidade);
	}

	public String urlPagamento() {
		return gatewayPagamento.geraUrl(id);
	}
    
	public String getEmailComprador() {
		return usuario.getEmail();
	}
	
	public String getEmailVendedor() {
		return produto.getEmailDono();
	}
	
	public String getNomeProduto() {
		return produto.getNome();
	}
	
	public StatusCompra getStatus() {
		return status;
	}
}
