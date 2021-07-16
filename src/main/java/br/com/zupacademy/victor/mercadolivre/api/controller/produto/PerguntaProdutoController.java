package br.com.zupacademy.victor.mercadolivre.api.controller.produto;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.victor.mercadolivre.api.dto.request.PerguntaRequest;
import br.com.zupacademy.victor.mercadolivre.api.util.Email;
import br.com.zupacademy.victor.mercadolivre.domain.model.Pergunta;
import br.com.zupacademy.victor.mercadolivre.domain.repository.PerguntaRepository;
import br.com.zupacademy.victor.mercadolivre.domain.repository.ProdutoRepository;
import br.com.zupacademy.victor.mercadolivre.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/produtos")
public class PerguntaProdutoController {

	private ProdutoRepository produtoRepository;
	private UsuarioRepository usuarioRepository;
	private PerguntaRepository perguntaRepository;

	public PerguntaProdutoController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository,
			PerguntaRepository perguntaRepository) {
		this.produtoRepository = produtoRepository;
		this.usuarioRepository = usuarioRepository;
		this.perguntaRepository = perguntaRepository;
	}

	@PostMapping("/perguntas")
	public ResponseEntity<?> adicionaPergunta(@RequestBody @Valid PerguntaRequest request) {
		Pergunta novaPegunta = request.toModel(produtoRepository, usuarioRepository);
		perguntaRepository.save(novaPegunta);
		enviarEmail(novaPegunta);
		return ResponseEntity.ok().build();
	}

	private String enviarEmail(Pergunta pergunta) {
		Email email = new Email(pergunta);
		return email.notificar();
	}
}
