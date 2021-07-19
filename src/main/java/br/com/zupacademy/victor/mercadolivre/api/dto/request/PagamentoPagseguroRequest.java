package br.com.zupacademy.victor.mercadolivre.api.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.victor.mercadolivre.api.validation.annotations.ExisteId;
import br.com.zupacademy.victor.mercadolivre.domain.model.Compra;
import br.com.zupacademy.victor.mercadolivre.domain.model.StatusPagamento;
import br.com.zupacademy.victor.mercadolivre.domain.model.Transacao;
import br.com.zupacademy.victor.mercadolivre.domain.repository.CompraRepository;

public class PagamentoPagseguroRequest implements PagamentoGatwayRequest {

	@ExisteId(nomeDoCampo = "id", classeDeDominio = Compra.class)
	private Integer idCompra;

	@Enumerated(EnumType.STRING)
	private StatusPagamento status;

	public PagamentoPagseguroRequest(Integer idCompra, @NotBlank StatusPagamento status) {
		this.idCompra = idCompra;
		this.status = status;
	}

	@Override
	public Transacao toModel(Integer idTransacao, CompraRepository compraRepository) {
		Compra compra = compraRepository.findById(idCompra)
				.orElseThrow(() -> new IllegalStateException("Compra não encontrada"));
		return new Transacao(idTransacao, compra, status);
	}

}
