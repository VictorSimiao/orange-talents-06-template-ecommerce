package br.com.zupacademy.victor.mercadolivre.api.dto.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.victor.mercadolivre.api.validation.annotations.ExisteId;
import br.com.zupacademy.victor.mercadolivre.domain.model.Compra;
import br.com.zupacademy.victor.mercadolivre.domain.model.StatusPagamento;
import br.com.zupacademy.victor.mercadolivre.domain.model.Transacao;
import br.com.zupacademy.victor.mercadolivre.domain.repository.CompraRepository;

public class PagamentoPaypalRequest implements PagamentoGatwayRequest {

	@ExisteId(nomeDoCampo = "id", classeDeDominio = Compra.class)
	private Integer idCompra;

	@NotNull
	@Range(min = 0, max = 1)
	private Integer status;

	public PagamentoPaypalRequest(Integer idCompra,
			@NotNull @Range(min = 0, max = 1) Integer status) {
		this.idCompra = idCompra;
		this.status = status;
	}

	@Override
	public Transacao toModel(Integer idTransacao,CompraRepository compraRepository) {
		Compra compra = compraRepository.findById(idCompra)
				.orElseThrow(() -> new IllegalStateException("Compra não encontrada"));
		StatusPagamento statusPagamento = this.status == 1 ? StatusPagamento.SUCESSO : StatusPagamento.ERRO;
		return new Transacao(idTransacao, compra, statusPagamento);
	}

}
