package br.com.zupacademy.victor.mercadolivre.domain.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

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
	
	@OneToMany(mappedBy = "compra",cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<Transacao>();
	
	@Deprecated
	public Compra() {
		
	}

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
	
	public Integer getIdVendedor() {
		return getProduto().getIdDono();
		}
	
	public String getNomeProduto() {
		return produto.getNome();
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public StatusCompra getStatus() {
		return status;
	}
	
	public void addTransacao(Transacao transacao) {
		 Optional<Transacao> transacaoAndamento = transacoes.stream().filter(transacao::equals).findAny();
		 if(transacaoAndamento.isPresent() && transacaoAndamento.get().isTransacaoConcluida()) {
			 throw new IllegalArgumentException("Essa compra já foi concluída");
		 }
		
		this.transacoes.add(transacao);
	}
	
	public boolean isConcluida() {
		return status == StatusCompra.CONCLUIDA;
	}
	
	public void setStatus(StatusCompra status) {
		Assert.isTrue(!isConcluida(),"Não é permitido mudar o status de uma compra concluída");
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
}
