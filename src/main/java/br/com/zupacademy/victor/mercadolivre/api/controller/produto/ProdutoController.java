package br.com.zupacademy.victor.mercadolivre.api.controller.produto;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.victor.mercadolivre.api.dto.request.ProdutoRequest;
import br.com.zupacademy.victor.mercadolivre.domain.model.Produto;
import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;
import br.com.zupacademy.victor.mercadolivre.domain.repository.CategoriaRepository;
import br.com.zupacademy.victor.mercadolivre.domain.repository.ProdutoRepository;
import br.com.zupacademy.victor.mercadolivre.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private ProdutoRepository produtoRepository;
	private CategoriaRepository categoriaRepository;
	private UsuarioRepository usuarioRepository;

	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
			UsuarioRepository usuarioRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	public ResponseEntity<?> cadastar(@RequestBody @Valid ProdutoRequest request) {
		Usuario usuarioDono = usuarioRepository.findByEmail("victor@gmail.com")
				.orElseThrow(() -> new IllegalStateException("e-mail é inválido"));
		Produto novoProduto = request.toModel(usuarioDono, categoriaRepository);
		produtoRepository.save(novoProduto);
		return ResponseEntity.ok().build();
	}

}
