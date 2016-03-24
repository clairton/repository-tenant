package br.eti.clairton.repository.tenant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import br.eti.clairton.repository.Joinner;
import br.eti.clairton.tenant.Tenantable;

public class TenantableDecorator<T> extends Tenantable<T>{
	private final Joinner joinner;
	private final Tenantable<T> delegate;
	
	@Deprecated
	public TenantableDecorator() {
		this(null, null);
	}
	
	public TenantableDecorator(final Joinner joinner, final Tenantable<T> delegate) {
		this.joinner = joinner;
		this.delegate = delegate;
	}

	@Override
	public Predicate add(final CriteriaBuilder builder, final From<?, T> from, final Object value) {
		final From<?, T> decorator = new FromDecorator<>(from, joinner, builder);
		return delegate.add(builder, decorator, value);
	}	
}
