package br.com.zupacademy.victor.mercadolivre.api.util;

import br.com.zupacademy.victor.mercadolivre.domain.model.Compra;

public interface EventoCompraSucesso {
	void processa(Compra compra);
}
