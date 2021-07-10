package br.com.zupacademy.victor.mercadolivre.api.controller.produto;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.victor.mercadolivre.api.dto.request.OpiniaoRequest;
import br.com.zupacademy.victor.mercadolivre.domain.model.Opiniao;
import br.com.zupacademy.victor.mercadolivre.domain.model.Produto;
import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;
import br.com.zupacademy.victor.mercadolivre.domain.repository.ProdutoRepository;
import br.com.zupacademy.victor.mercadolivre.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/produtos")
public class OpiniaoProdutoController {

	private ProdutoRepository produtoRepository;
	private UsuarioRepository usuarioRepository;

	public OpiniaoProdutoController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
		this.produtoRepository = produtoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping("/{id}/opinioes")
	public ResponseEntity<?> adicionaOpiniao(@PathVariable("id") Integer id, @RequestBody @Valid OpiniaoRequest request) {

		Usuario usuario = usuarioRepository.findByEmail("victor@gmail.com")
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));

		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

		Opiniao novaOpiniao = request.toModel(usuario, produto);

		produto.adicionaOpiniao(novaOpiniao);

		produtoRepository.save(produto);
		
		return ResponseEntity.ok().build();
	}

}
