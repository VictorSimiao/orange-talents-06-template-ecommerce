package br.com.zupacademy.victor.mercadolivre.api.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploaderFake implements Uploader {

	@Override
	public Set<String> enviar(List<MultipartFile> imagens) {
		return imagens.stream().map(imagem ->"http://img.com/"+imagem.getOriginalFilename()).collect(Collectors.toSet());
	}
	
}


