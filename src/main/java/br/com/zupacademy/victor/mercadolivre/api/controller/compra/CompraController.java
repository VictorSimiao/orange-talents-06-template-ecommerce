package br.com.zupacademy.victor.mercadolivre.api.controller.compra;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.victor.mercadolivre.api.dto.request.NovaCompraRequest;
import br.com.zupacademy.victor.mercadolivre.api.util.Email;
import br.com.zupacademy.victor.mercadolivre.domain.model.Compra;
import br.com.zupacademy.victor.mercadolivre.domain.model.Usuario;
import br.com.zupacademy.victor.mercadolivre.domain.repository.CompraRepository;
import br.com.zupacademy.victor.mercadolivre.domain.repository.ProdutoRepository;
import br.com.zupacademy.victor.mercadolivre.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

	private ProdutoRepository produtoRepository;
	private UsuarioRepository usuarioRepository;
	private CompraRepository compraRepository;

	public CompraController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository,
			CompraRepository compraRepository) {
		this.produtoRepository = produtoRepository;
		this.usuarioRepository = usuarioRepository;
		this.compraRepository = compraRepository;
	}

	@PostMapping
	public ResponseEntity<?> realizaNovaCompra(@RequestBody @Valid NovaCompraRequest request) {
		Usuario usuarioLogado = usuarioRepository.findByEmail("victor@gmail.com").get();
		Compra novaCompra = request.toModel(produtoRepository, usuarioLogado);
		if (novaCompra.abateEstoqueDoProduto()) {
			compraRepository.save(novaCompra);
			enviarEmail(novaCompra, "Esse usuário está interessado em comprar esse produto");
			return ResponseEntity.status(HttpStatus.FOUND).body(novaCompra.urlPagamento());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não possui estoque suficiente");
	}

	private String enviarEmail(Compra compra, String informacao) {
		Email email = new Email(compra, informacao);
		return email.notificar();
	}
}
