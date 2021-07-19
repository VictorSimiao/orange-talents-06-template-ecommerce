package br.com.zupacademy.victor.mercadolivre.api.util;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zupacademy.victor.mercadolivre.domain.model.Compra;

@Service
public class EventosNovaCompra {
	@Autowired
	private Set<EventoCompraSucesso> eventosCompraSucesso;
	Email email;
	public void processa(Compra compra) {
		if (compra.isConcluida()) {
			eventosCompraSucesso.forEach(evento -> evento.processa(compra));
			 email = new Email(compra, "Compra Realizada com Sucesso");
			System.out.println(email.notificacaoCompraSucesso());
		} else {
		email = new Email(compra, "Compra não foi concluída");
		System.out.println(email.notificacaoFalhaCompra());
		}

	}

}
