package br.com.zupacademy.victor.mercadolivre.api.dto.request;

import br.com.zupacademy.victor.mercadolivre.domain.model.Transacao;
import br.com.zupacademy.victor.mercadolivre.domain.repository.CompraRepository;

public interface PagamentoGatwayRequest {
	 Transacao toModel(Integer idTransacao, CompraRepository compraRepository);
}
