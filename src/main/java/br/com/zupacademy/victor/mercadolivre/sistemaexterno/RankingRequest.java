package br.com.zupacademy.victor.mercadolivre.sistemaexterno;

import javax.validation.constraints.NotNull;

public class RankingRequest {
	@NotNull
	private Integer idCompra;
	@NotNull
	private Integer idVendedor;
	
	public RankingRequest(@NotNull Integer idCompra, @NotNull Integer idVendedor) {
		this.idCompra = idCompra;
		this.idVendedor = idVendedor;
	}

	@Override
	public String toString() {
		String mensagem = String.format(new StringBuilder()
				.append("=========Sistema de Ranking============\n")
				.append("Informações do Ranking:\n")
				.append("Identificado da compra: %s\n")
				.append("Identificado do Vendedor: %s \n")
				.append("============================================\n")
				.toString(), this.idCompra,this.idVendedor);
		return mensagem;
	}
	
	

}
