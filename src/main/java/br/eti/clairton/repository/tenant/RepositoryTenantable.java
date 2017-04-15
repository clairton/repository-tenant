package br.eti.clairton.repository.tenant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import br.eti.clairton.repository.Joinner;
import br.eti.clairton.tenant.Tenantable;

/**
 * Constract for tenant in repository.
 * 
 * @author Clairton Rodrigo Heinzen clairton.rodrigo@gmail.com
 *
 * @param <T>
 *            type of model
 */
public abstract class RepositoryTenantable<T> extends Tenantable<T>{

	/**
	 * Create a predicate do join de tenantable entity.
	 * 
	 * @param joinner
	 *            instance of {@link Joinner}
	 * @param builder
	 *            instance of {@link CriteriaBuilder}
	 * @param from
	 *            instance of current {@link From}
	 * @param value
	 *            value of de tenant
	 * @return {@link Predicate} for the tenant
	 */
	public abstract Predicate add(final Joinner joinner, final CriteriaBuilder builder, From<?, T> from, final Object value);
	

	@Override
	@Deprecated
	public Predicate add(final CriteriaBuilder builder, final From<?, T> from, final Object value) {
		throw new UnsupportedOperationException();
	}
}
