package br.eti.clairton.repository.tenant;

import static java.lang.Boolean.TRUE;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.Logger;

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

	private final Logger logger = getLogger(RepositoryTenant.class);

	private Value<?> tenantValue;

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
			@NotNull final Value<?> tenantValue) {
		super(em);
		this.tenant = tenant;
		this.tenantValue = tenantValue;
	}

	public Repository tenantValue(final Object value) {
		this.tenantValue = new Value<Object>() {
			@Override
			public Object get() {
				return value;
			}
		};
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
			logger.debug("Tenant is able");
			this.joinner = new JoinnerTenant(tenant, builder, from, tenantValue);
			try {
				predicates.add(tenant.run(builder, from, tenantValue.get()));
			} catch (final TenantNotFound e) {
				logger.debug("TenantNotFound");				
			}
		}
		return this;
	}
}
