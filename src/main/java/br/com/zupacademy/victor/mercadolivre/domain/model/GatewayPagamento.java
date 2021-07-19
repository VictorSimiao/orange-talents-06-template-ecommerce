package br.com.zupacademy.victor.mercadolivre.domain.model;

public enum GatewayPagamento {
	PAGSEGURO("https://pagseguro.com?returnId=%s&redirectUrl=http://localhost:8080/api/retorno-pagseguro/%s"),
	PAYPAL("https://paypal.com?buyerId=%s&redirectUrl=http://localhost:8080/api/retorno-paypal/%s");
	
	String url;
	
	private GatewayPagamento(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public String geraUrl(Integer idCompra) {
		return String.format(getUrl(), idCompra.toString(),idCompra.toString());
	}

}
