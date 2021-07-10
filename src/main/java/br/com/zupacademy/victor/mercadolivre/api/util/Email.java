package br.com.zupacademy.victor.mercadolivre.api.util;

import br.com.zupacademy.victor.mercadolivre.domain.model.Pergunta;

public class Email {
	
	private String emailUsuarioRemetente;
	private String titulo;
	private String emailUsuarioDestinatario;
	
	public Email(Pergunta pergunta) {
		this.emailUsuarioRemetente = pergunta.getEmailUsuario();
		this.titulo = pergunta.getTitulo();
		this.emailUsuarioDestinatario = pergunta.getEmailDonoProduto();
	}
	
	public void notificarDono() {
		System.out.println("Enviando e-mail....");
		System.out.println("Enviado por: "+this.emailUsuarioRemetente);
		System.out.println("Assunto: "+this.titulo);
		System.out.println("Destinatário: "+this.emailUsuarioDestinatario);
	}
	
}
