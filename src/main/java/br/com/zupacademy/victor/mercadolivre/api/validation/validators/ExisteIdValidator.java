package br.com.zupacademy.victor.mercadolivre.api.validation.validators;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

import br.com.zupacademy.victor.mercadolivre.api.validation.annotations.ExisteId;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {

	private String campo;
	private Class<?> klass;

	private EntityManager manager;

	public ExisteIdValidator(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void initialize(ExisteId valorParametro) {
		campo = valorParametro.nomeDoCampo();
		klass = valorParametro.classeDeDominio();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + campo + "=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();

		Assert.state(list.size() <= 1,
				"Foi encontrado mais de um " + klass + " com o atributo " + campo + " = " + value);
		return !list.isEmpty();
	}

}
