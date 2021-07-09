package br.com.zupacademy.victor.mercadolivre.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import br.com.zupacademy.victor.mercadolivre.api.dto.request.CaracteristicaRequest;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@PositiveOrZero
	private Integer quantidade;

	@NotBlank
	@Size(max = 100)
	private String descricao;

	@Valid
	@Size(min = 3)
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<CaracteristicaProduto>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario dono;

	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;

	@CreationTimestamp
	@PastOrPresent
	private LocalDateTime cadastradoEm;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<ImagemProduto>();

	@Deprecated
	public Produto() {

	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidade, @NotBlank @Size(max = 100) String descricao,
			@Valid @Size(min = 3) Set<CaracteristicaRequest> caracteristicas, Usuario donoProduto,
			Categoria categoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
		this.dono = donoProduto;
		this.categoria = categoria;

		Assert.isTrue(this.caracteristicas.size() >= 3, "O produto precisa ter no mínimo três características");
		Assert.isTrue(this.quantidade >= 0, "A quantide precisa ser maoir ou igual a 0");

	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}
	
	
	public Usuario getDono() {
		return dono;
	}

	public boolean pertenceAoDono(Usuario possivelDono) {
		return this.dono.equals(possivelDono);
	}

}
