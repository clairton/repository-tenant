package br.eti.clairton.repository.tenant;

import static java.lang.Boolean.TRUE;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.Logger;

import br.eti.clairton.repository.Model;
import br.eti.clairton.repository.Repository;
import br.eti.clairton.tenant.TenantBuilder;
import br.eti.clairton.tenant.TenantNotFound;

/**
 * Devolve um Repository que recuperara valore usando tenanty.
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

	/**
	 * CDI only.
	 */
	@Deprecated
	protected RepositoryTenant() {
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
			@NotNull final TenantBuilder tenant,
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
	 * {@inheritDoc}. <br/>
	 * 
	 * Call de super.from and if is tenant, add tenant {@link Predicate}
	 */
	@Override
	public <T extends Model> Repository from(@NotNull final Class<T> type) {
		super.from(type);
		if (isTenant) {
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
