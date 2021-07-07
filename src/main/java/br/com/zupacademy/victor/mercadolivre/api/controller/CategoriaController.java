package br.com.zupacademy.victor.mercadolivre.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.victor.mercadolivre.api.dto.request.CategoriaRequest;
import br.com.zupacademy.victor.mercadolivre.domain.model.Categoria;
import br.com.zupacademy.victor.mercadolivre.domain.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	private CategoriaRepository repository;

	public CategoriaController(CategoriaRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaRequest request) {
		Categoria novaCategoria = request.toModel(repository);
		repository.save(novaCategoria);
		return ResponseEntity.ok().build();
	}

}
