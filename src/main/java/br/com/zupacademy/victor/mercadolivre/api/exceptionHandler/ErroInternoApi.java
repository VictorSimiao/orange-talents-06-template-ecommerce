package br.com.zupacademy.victor.mercadolivre.api.exceptionHandler;

public class ErroInternoApi {

	private String mensagemDeErro;

	public ErroInternoApi(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}
	
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}
	
}
