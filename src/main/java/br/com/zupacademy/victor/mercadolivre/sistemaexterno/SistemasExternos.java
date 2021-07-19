package br.com.zupacademy.victor.mercadolivre.sistemaexterno;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SistemasExternos {
	
	@PostMapping("/notas-fiscais")
	public void criarNotas(@Valid @RequestBody NovaCompraNFRequest request) throws InterruptedException {
		System.out.println(request);
		Thread.sleep(150);
	}
	@PostMapping("/rankings")
	public void ranking(@Valid @RequestBody RankingRequest request) throws InterruptedException {
		System.out.println(request);
		Thread.sleep(150);
	}
	

}
