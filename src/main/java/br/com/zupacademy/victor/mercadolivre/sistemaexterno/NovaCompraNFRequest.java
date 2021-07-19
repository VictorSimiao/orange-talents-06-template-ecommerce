package br.com.zupacademy.victor.mercadolivre.sistemaexterno;

import javax.validation.constraints.NotNull;

public class NovaCompraNFRequest {
	@NotNull
	private Integer idCompra;
	@NotNull
	private Integer idComprador;
	
	public NovaCompraNFRequest(@NotNull Integer idCompra, @NotNull Integer idComprador) {
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}

	@Override
	public String toString() {
		String mensagem = String.format(new StringBuilder()
				.append("=========Sistema de Nota Fiscal============\n")
				.append("Informações da Nota Fiscal:\n")
				.append("Identificado da compra: %s\n")
				.append("Identificado do cliente: %s \n")
				.append("============================================\n")
				.toString(), this.idCompra,this.idComprador);
		return mensagem;
	}
	
	

}
