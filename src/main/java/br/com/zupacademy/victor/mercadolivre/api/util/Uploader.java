package br.com.zupacademy.victor.mercadolivre.api.util;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {
 /**
  * 
  * @param imagens
  * @return Links para imagens que fizeram Upload
  */
	Set<String> enviar(List<MultipartFile> imagens);
}
