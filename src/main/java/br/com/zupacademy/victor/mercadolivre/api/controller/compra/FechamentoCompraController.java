package br.com.zupacademy.victor.mercadolivre.api.controller.compra;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.victor.mercadolivre.api.dto.request.PagamentoPagseguroRequest;
import br.com.zupacademy.victor.mercadolivre.api.dto.request.PagamentoPaypalRequest;
import br.com.zupacademy.victor.mercadolivre.api.util.EventosNovaCompra;
import br.com.zupacademy.victor.mercadolivre.domain.model.Transacao;
import br.com.zupacademy.victor.mercadolivre.domain.repository.CompraRepository;
import br.com.zupacademy.victor.mercadolivre.domain.repository.TransacaoRepository;

@RestController
@RequestMapping("/api")
public class FechamentoCompraController {

	private CompraRepository compraRepository;
	private TransacaoRepository transacaoRepository;
	private EventosNovaCompra eventosNovaCompra;

	public FechamentoCompraController(CompraRepository compraRepository, TransacaoRepository transacaoRepository,
			EventosNovaCompra eventosNovaCompra) {
		this.compraRepository = compraRepository;
		this.transacaoRepository = transacaoRepository;
		this.eventosNovaCompra = eventosNovaCompra;
	}

	@PostMapping("retorno-paypal/{id}")
	public ResponseEntity<?> efetuaPagamentoPaypal(@PathVariable Integer id,
			@RequestBody @Valid PagamentoPaypalRequest request) {
		Transacao transacao = request.toModel(id, compraRepository);
		processarPagamentoTransacao(transacao);
		return ResponseEntity.ok(transacao.getStatus());
	}

	@PostMapping("retorno-pagseguro/{id}")
	public ResponseEntity<?> efetuaPagamentoPagseguro(@PathVariable Integer id,
			@RequestBody @Valid PagamentoPagseguroRequest request) {
		Transacao transacao = request.toModel(id, compraRepository);
		processarPagamentoTransacao(transacao);
		return ResponseEntity.ok(transacao.getStatus());
	}

	private void processarPagamentoTransacao(Transacao transacao) {
		transacao.processarTransacao();
		transacaoRepository.save(transacao);
		eventosNovaCompra.processa(transacao.getCompra());
	}

}
