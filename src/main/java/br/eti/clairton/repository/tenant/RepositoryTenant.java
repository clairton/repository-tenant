package br.eti.clairton.repository.tenant;

import static java.lang.Boolean.TRUE;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import br.eti.clairton.repository.Model;
import br.eti.clairton.repository.Repository;
import br.eti.clairton.tenant.TenantBuilder;
import br.eti.clairton.tenant.TenantNotFound;

/**
 * Devolve um Repository com o valor de tenant setado.
 * 
 * @author Clairton Rodrigo Heinzen<clairton.rodrigo@gmail.com>
 */
@Tenant
@Dependent
public class RepositoryTenant extends Repository {
	private static final long serialVersionUID = 1L;

	private Object tenantValue;

	private final TenantBuilder tenant;

	private Boolean isTenant = TRUE;

	@Deprecated
	protected RepositoryTenant() {
		this(null, null, null);
	}

	@Inject
	public RepositoryTenant(
			@NotNull final EntityManager em,
			@NotNull final TenantBuilder tenant,
			@NotNull final TenantValue<?> tenantValue) {
		super(em);
		this.tenant = tenant;
		if (tenantValue != null) {
			tenantValue(tenantValue.get());
		}
	}

	public Repository tenantValue(final Object value) {
		this.tenantValue = value;
		this.isTenant = TRUE;
		return this;
	}
	
	public Repository tenant(final Boolean isTenant){
		this.isTenant = isTenant;
		return this;
	}

	public <T extends Model> Repository from(@NotNull final Class<T> type) {
		super.from(type);
		if(isTenant){			
			this.joinner = new JoinnerTenant(tenant, builder, from, tenantValue);
		}
		try {
			predicates.add(tenant.run(builder, from, tenantValue));
		} catch (final TenantNotFound e) {}
		return this;
	}
}
