package br.eti.clairton.repository.tenant;

import static java.lang.Boolean.TRUE;
import static java.util.logging.Logger.getLogger;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

import br.eti.clairton.repository.Repository;
import br.eti.clairton.tenant.TenantBuilder;
import br.eti.clairton.tenant.TenantNotFound;

/**
 * A Tenant Repository.
 * 
 * @author Clairton Rodrigo Heinzen clairton.rodrigo@gmail.com
 */
@Tenant
@Dependent
public class RepositoryTenant extends Repository {
	private static final long serialVersionUID = 1L;

	private final Logger logger = getLogger(RepositoryTenant.class.getSimpleName());

	private final RepositoryTenantBuilder tenant;

	private Value<?> tenantValue;


	private Boolean isTenant = TRUE;

	/**
	 * CDI only.
	 */
	@Deprecated
	public RepositoryTenant() {
		this(null, null, null);
	}

	/**
	 * Default constructor.
	 * 
	 * @param manager
	 *            instance of {@link EntityManager}
	 * @param tenant
	 *            instance of {@link TenantBuilder}
	 * @param tenantValue
	 *            value for tenant
	 */
	@Inject
	public RepositoryTenant(
			@NotNull final EntityManager manager,
			@NotNull final RepositoryTenantBuilder tenant,
			@NotNull final Value<?> tenantValue) {
		super(manager);
		this.tenant = tenant;
		this.tenantValue = tenantValue;
	}

	/**
	 * Set manual tenant value
	 * 
	 * @param value
	 *            tenant value
	 * @return it self
	 */
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

	/**
	 * Toggle tenant.
	 * 
	 * @param isTenant
	 *            true/false
	 * @return it self
	 */
	public Repository tenant(final Boolean isTenant) {
		this.isTenant = isTenant;
		return this;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Call de super.from and if is tenant, add tenant {@link Predicate}
	 */
	@Override
	public <T> Repository from(@NotNull final Class<T> type) {
		super.from(type);
		if (isTenant) {
			this.joinner = new RepositoryJoinnerTenant(tenant, builder, from, tenantValue);
			logger.fine("Tenant is able");
			try {
				final Object value = tenantValue.get();
				final Predicate predicate = tenant.run(joinner, builder, from, value);
				predicates.add(predicate);
			} catch (final TenantNotFound e) {
				logger.fine("TenantNotFound");
			}
		}
		return this;
	}
}
