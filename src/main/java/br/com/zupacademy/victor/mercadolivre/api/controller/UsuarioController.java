package br.com.zupacademy.victor.mercadolivre.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.victor.mercadolivre.api.dto.request.UsuarioRequest;
import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;
import br.com.zupacademy.victor.mercadolivre.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

	private UsuarioRepository repository;

	public UsuarioController(UsuarioRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest request) {
		Usuario novoUsuario = request.toModel();
		repository.save(novoUsuario);
		return ResponseEntity.ok().build();
	}

}
