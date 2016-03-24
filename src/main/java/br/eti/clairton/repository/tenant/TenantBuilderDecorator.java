package br.eti.clairton.repository.tenant;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Vetoed;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import br.eti.clairton.repository.Joinner;
import br.eti.clairton.tenant.TenantBuilder;
import br.eti.clairton.tenant.Tenantable;

@Vetoed
public class TenantBuilderDecorator extends TenantBuilder{
	private final TenantBuilder delegate;
	private Joinner joinner;

	public TenantBuilderDecorator(final TenantBuilder delegate, final Joinner joinner) {
		super(null);
		this.delegate = delegate;
		this.joinner = joinner;
	}

	public <T> Tenantable<T> get(final Instance<Tenantable<?>> instance){
		@SuppressWarnings("unchecked")
		final Tenantable<T> t = (Tenantable<T>) new TenantableDecorator<>(joinner, delegate.get(instance));
		return t;
	}

	public Boolean exist(Class<?> klazz) {
		return delegate.exist(klazz);
	}

	public <T, Y> Predicate run(CriteriaBuilder builder, From<?, T> from, Object tenantValue) {
		return this.delegate.run(builder, from, tenantValue);
	}

	public int hashCode() {
		return delegate.hashCode();
	}

	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	public String toString() {
		return delegate.toString();
	}
	
	public void setJoinner(final Joinner joinner) {
		this.joinner = joinner;
	}
}
