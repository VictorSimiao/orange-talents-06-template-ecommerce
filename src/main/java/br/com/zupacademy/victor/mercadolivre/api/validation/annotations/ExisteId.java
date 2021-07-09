package br.com.zupacademy.victor.mercadolivre.api.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.victor.mercadolivre.api.validation.validators.ExisteIdValidator;

@Documented
@Constraint(validatedBy = {ExisteIdValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteId {
	String message() default "{br.com.zupacademy.victor.mercadolivre.api.validation.annotations.existeid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	String nomeDoCampo();
	
	Class<?> classeDeDominio();
}
