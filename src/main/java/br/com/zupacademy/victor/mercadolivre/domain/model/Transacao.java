package br.com.zupacademy.victor.mercadolivre.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private Integer idTransacaoGateway;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Compra compra;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusPagamento status;

	@NotNull
	private LocalDateTime momentoTransacao = LocalDateTime.now();

	@Deprecated
	public Transacao() {
	}

	public Transacao(@NotNull Integer idTransacaoGateway, @NotNull Compra compra, @NotNull StatusPagamento status) {
		this.idTransacaoGateway = idTransacaoGateway;
		this.compra = compra;
		this.status = status;
	}

	public void processarTransacao() {
		compra.addTransacao(this);
		compra.setStatus(this.status.getStatusCompra());
	}

	public Integer getId() {
		return id;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public Compra getCompra() {
		return compra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransacaoGateway == null) ? 0 : idTransacaoGateway.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (idTransacaoGateway == null) {
			if (other.idTransacaoGateway != null)
				return false;
		} else if (!idTransacaoGateway.equals(other.idTransacaoGateway))
			return false;
		return true;
	}

	public boolean isTransacaoConcluida() {
		return this.compra.isConcluida();
	}

}
