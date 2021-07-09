package br.com.zupacademy.victor.mercadolivre.api.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	private MessageSource messageSource;

	public ApiExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<?> handle(MethodArgumentNotValidException exception) {
		List<Problema> dtos = new ArrayList<Problema>();

		List<FieldError> errosDeCampos = exception.getBindingResult().getFieldErrors();
		if (!errosDeCampos.isEmpty()) {

			List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
			fieldErrors.forEach(erro -> {
				String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
				Problema problema = new Problema(erro.getField(), mensagem);
				dtos.add(problema);
			});
			return dtos;
		}
		return exception.getBindingResult().getGlobalErrors();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handle(IllegalArgumentException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Problema(exception.getMessage()));
	}

}
