package br.com.zupacademy.victor.mercadolivre.domain.model;

public enum StatusPagamento {

	SUCESSO(StatusCompra.CONCLUIDA), ERRO(StatusCompra.ERRO_DE_PAGAMENTO);

	StatusCompra statusCompra;

	private StatusPagamento(StatusCompra statusCompra) {
		this.statusCompra = statusCompra;
	}

	public StatusCompra getStatusCompra() {
		return statusCompra;
	}
}
