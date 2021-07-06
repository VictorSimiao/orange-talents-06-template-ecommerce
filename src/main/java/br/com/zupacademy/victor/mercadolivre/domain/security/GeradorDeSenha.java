package br.com.zupacademy.victor.mercadolivre.domain.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class GeradorDeSenha {
	
	private String senhaPura;
	
	public GeradorDeSenha(String senhaPura) {
		this.senhaPura = senhaPura;
	}

	public String criptografar() {
		Assert.hasLength(senhaPura, "senha não pode está em branco");
		Assert.isTrue(senhaPura.length()>=6, "Senha precisa ter mais de 5 caracteres");
		return new BCryptPasswordEncoder().encode(senhaPura);
	}

}
