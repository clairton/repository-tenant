package br.eti.clairton.repository.tenant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import br.eti.clairton.repository.Joinner;
import br.eti.clairton.tenant.Tenantable;

public class TenantableProxy<T> extends Tenantable<T>{
	private final Joinner joinner;
	private final Tenantable<T> tenantable;
	
	@Deprecated
	public TenantableProxy() {
		this(null, null);
	}
	
	public TenantableProxy(final Joinner joinner, final Tenantable<T> tenantable) {
		this.joinner = joinner;
		this.tenantable = tenantable;
	}

	@Override
	public Predicate add(final CriteriaBuilder builder, final From<?, T> from, final Object value) {
		final From<?, T> proxy = new FromProxy<>(from, joinner, builder);
		return tenantable.add(builder, proxy, value);
	}
	
}
