package br.com.zupacademy.victor.mercadolivre.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.victor.mercadolivre.domain.security.GeradorDeSenha;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Email
	private String login;
	
	@NotBlank
	@Length(min = 6)
	private String senha;
	
	@CreationTimestamp
	@PastOrPresent
	private LocalDateTime momentoCadastro;

	@Deprecated
	public Usuario() {
	}

	public Usuario(@NotBlank @Email String login, GeradorDeSenha senhaPura) {
		this.login = login;
		this.senha = senhaPura.criptografar();
	}

}
