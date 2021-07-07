package br.com.zupacademy.victor.mercadolivre.api.validation.validators;

import java.util.List;
import br.com.zupacademy.victor.mercadolivre.api.validation.annotations.ValorUnico;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

	private String atributoDoDominio;
	private Class<?> klass;
	private EntityManager manager;

	public ValorUnicoValidator(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void initialize(ValorUnico valorParametro) {
		atributoDoDominio = valorParametro.nomeDoCampo();
		klass = valorParametro.classeDeDominio();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager
				.createQuery("select 1 from " + klass.getName() + " where " + atributoDoDominio + "=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();

		Assert.state(list.size() <= 1,
				"Foi encontrado mais de um " + klass + " com o atributo " + atributoDoDominio + " = " + value);
		return list.isEmpty();
	}

}