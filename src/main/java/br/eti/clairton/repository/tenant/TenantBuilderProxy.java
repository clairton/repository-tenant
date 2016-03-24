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
public class TenantBuilderProxy extends TenantBuilder{
	private final TenantBuilder builder;
	private final Joinner joinner;

	public TenantBuilderProxy(TenantBuilder builder, Joinner joinner) {
		super(null);
		this.builder = builder;
		this.joinner = joinner;
	}

	public <T> Tenantable<T> get(final Instance<Tenantable<?>> instance){
		@SuppressWarnings("unchecked")
		final Tenantable<T> t = (Tenantable<T>) new TenantableProxy<>(joinner, builder.get(instance));
		return t;
	}

	public Boolean exist(Class<?> klazz) {
		return builder.exist(klazz);
	}

	public <T, Y> Predicate run(CriteriaBuilder builder, From<?, T> from, Object tenantValue) {
		return this.builder.run(builder, from, tenantValue);
	}

	public int hashCode() {
		return builder.hashCode();
	}

	public boolean equals(Object obj) {
		return builder.equals(obj);
	}

	public String toString() {
		return builder.toString();
	}
}
