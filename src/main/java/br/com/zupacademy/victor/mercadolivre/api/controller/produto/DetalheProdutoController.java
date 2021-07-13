package br.com.zupacademy.victor.mercadolivre.api.controller.produto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.victor.mercadolivre.api.dto.response.DetalheProdutoResponse;
import br.com.zupacademy.victor.mercadolivre.domain.model.Produto;
import br.com.zupacademy.victor.mercadolivre.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class DetalheProdutoController {
	
	ProdutoRepository produtoRepository;
	
	public DetalheProdutoController(ProdutoRepository produtoRepository) {
	
		this.produtoRepository = produtoRepository;
	}

	@GetMapping("/{id}")
	public DetalheProdutoResponse detalharProduto(@PathVariable Integer id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
		
		return new DetalheProdutoResponse(produto);
	}

}
