package br.com.zupacademy.victor.mercadolivre.api.validation.annotations;

import br.com.zupacademy.victor.mercadolivre.api.validation.validators.ValorUnicoValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { ValorUnicoValidator.class })
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {
	String message() default "{br.com.zupacademy.victor.mercadolivre.api.validation.annotations.valorunico}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String nomeDoCampo();

	Class<?> classeDeDominio();

}
