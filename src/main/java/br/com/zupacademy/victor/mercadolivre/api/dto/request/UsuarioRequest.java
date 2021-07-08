package br.com.zupacademy.victor.mercadolivre.api.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.victor.mercadolivre.api.validation.annotations.ValorUnico;
import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;
import br.com.zupacademy.victor.mercadolivre.domain.security.GeradorDeSenha;

public class UsuarioRequest {

	@NotBlank
	@Email
	@ValorUnico(nomeDoCampo = "email", classeDeDominio = Usuario.class)
	private String email;

	@NotBlank
	@Length(min = 6)
	private String senha;

	public UsuarioRequest(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
		this.email = email;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(email, new GeradorDeSenha(senha));
	}

}
