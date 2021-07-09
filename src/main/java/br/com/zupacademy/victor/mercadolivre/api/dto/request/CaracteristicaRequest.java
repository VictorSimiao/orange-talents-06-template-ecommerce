package br.com.zupacademy.victor.mercadolivre.api.dto.request;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.victor.mercadolivre.domain.model.CaracteristicaProduto;
import br.com.zupacademy.victor.mercadolivre.domain.model.Produto;

public class CaracteristicaRequest {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public CaracteristicaProduto toModel(Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaracteristicaRequest other = (CaracteristicaRequest) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


}
