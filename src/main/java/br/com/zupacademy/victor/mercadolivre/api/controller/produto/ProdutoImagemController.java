package br.com.zupacademy.victor.mercadolivre.api.controller.produto;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.victor.mercadolivre.api.dto.request.ImagensRequest;
import br.com.zupacademy.victor.mercadolivre.api.util.UploaderFake;
import br.com.zupacademy.victor.mercadolivre.domain.model.Produto;
import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;
import br.com.zupacademy.victor.mercadolivre.domain.repository.ProdutoRepository;
import br.com.zupacademy.victor.mercadolivre.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoImagemController {

	private UploaderFake uploaderFake;
	private ProdutoRepository produtoRepository;
	private UsuarioRepository usuarioRepository;

	public ProdutoImagemController(UploaderFake uploaderFake, ProdutoRepository produtoRepository,
			UsuarioRepository usuarioRepository) {
		this.uploaderFake = uploaderFake;
		this.produtoRepository = produtoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping("/{id}/imagens")
	public void adicionaImagens(@PathVariable("id") Integer id, @Valid ImagensRequest request) {

		Usuario possivelDono = usuarioRepository.findByEmail("victor@gmail.com")
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));

		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

		if (!produto.pertenceAoDono(possivelDono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Produto não pertence o Usuário");
		}

		Set<String> links = uploaderFake.enviar(request.getImagens());
		produto.associaImagens(links);
		produtoRepository.save(produto);
	}

}
