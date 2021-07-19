package br.com.zupacademy.victor.mercadolivre.sistemaexterno;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.victor.mercadolivre.api.util.EventoCompraSucesso;
import br.com.zupacademy.victor.mercadolivre.domain.model.Compra;

@Service
public class Ranking implements EventoCompraSucesso {
	@Override
	public void processa(Compra compra) {
		Assert.isTrue(compra.isConcluida(),"Essa compra não foi concluída com sucesso");
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getIdVendedor());
		restTemplate.postForEntity("http://localhost:8080/rankings", request, String.class);
	}
}
