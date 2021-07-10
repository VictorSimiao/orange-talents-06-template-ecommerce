package br.com.zupacademy.victor.mercadolivre.api.dto.request;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.victor.mercadolivre.api.validation.annotations.ExisteId;
import br.com.zupacademy.victor.mercadolivre.domain.model.Pergunta;
import br.com.zupacademy.victor.mercadolivre.domain.model.Produto;
import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;
import br.com.zupacademy.victor.mercadolivre.domain.repository.ProdutoRepository;
import br.com.zupacademy.victor.mercadolivre.domain.repository.UsuarioRepository;

public class PerguntaRequest {
	
	@NotBlank
	private String titulo;
	
	@ExisteId(nomeDoCampo = "id", classeDeDominio = Produto.class, message = "Produto não encontrado")
	private Integer idProduto;
	
	@ExisteId(nomeDoCampo = "id", classeDeDominio = Usuario.class, message = "Usuário não encontrado")
	private Integer idUsuario;

	public PerguntaRequest(@NotBlank String titulo, Integer idProduto, Integer idUsuario) {
		this.titulo = titulo;
		this.idProduto = idProduto;
		this.idUsuario = idUsuario;
	}


	public Pergunta toModel(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
		Produto produto = produtoRepository.findById(idProduto)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Precisa ser um usuário"));
		return new Pergunta(titulo,usuario,produto);
	}
	
	
	
	
	
	

}
