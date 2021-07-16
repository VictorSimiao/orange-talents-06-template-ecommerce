package br.com.zupacademy.victor.mercadolivre.api.util;

import br.com.zupacademy.victor.mercadolivre.domain.model.Compra;
import br.com.zupacademy.victor.mercadolivre.domain.model.Pergunta;

public class Email {

	private String emailUsuario;
	private String mensagem;
	private String emailDonoProduto;
	private String nomeProduto;

	public Email(Pergunta pergunta) {
		this.emailUsuario = pergunta.getEmailUsuario();
		this.mensagem = pergunta.getTitulo();
		this.emailDonoProduto = pergunta.getEmailDonoProduto();
		this.nomeProduto = pergunta.getNomeProduto();
	}

	public Email(Compra compra, String informacao) {
		this.emailUsuario = compra.getEmailComprador();
		this.mensagem = informacao;
		this.nomeProduto = compra.getNomeProduto();
		this.emailDonoProduto = compra.getEmailVendedor();
	}

	public  String notificar() {
		String mensagem = "Enviado por: " + this.emailUsuario + "\n" + "Sobre o Produto: " + this.nomeProduto + "\n"
				+ "Mensagem: " + this.mensagem + "\n" + "Destinatário: " + this.emailDonoProduto;
		return mensagem;
	}

}
