package br.eti.clairton.repository.tenant;

import static org.apache.logging.log4j.LogManager.getLogger;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.Logger;

import br.eti.clairton.repository.Joinner;
import br.eti.clairton.tenant.TenantBuilder;
import br.eti.clairton.tenant.TenantNotFound;
import br.eti.clairton.tenant.TenantType;
import br.eti.clairton.tenant.Tenantable;

@Specializes
public class RepositoryTenantBuilder extends TenantBuilder{
	private static final Logger logger = getLogger(TenantBuilder.class);
	private final Instance<RepositoryTenantable<?>> tenants;

	/**
	 * Constructor for CDI only.
	 */
	@Deprecated
	public RepositoryTenantBuilder() {
		this(null);
	}

	/**
	 * Default constructor.
	 * 
	 * @param tenants
	 *            instance of tenants
	 */
	@Inject
	public RepositoryTenantBuilder(@Any final Instance<RepositoryTenantable<?>> tenants) {
		super(null);
		this.tenants = tenants;
	}


	/**
	 * Add the tenant for de {@link From}, id exists.
	 * 
	 * @param builder
	 *            instance of {@link CriteriaBuilder}
	 * @param from
	 *            instance of de current {@link From}
	 * @param tenantValue
	 *            value of the tenant
	 * @return {@link Predicate} of the tenant for the {@link From} param
	 */
	public <T, Y> Predicate run(final Joinner joinner, final CriteriaBuilder builder, @NotNull final From<?, T> from, final Object tenantValue) {
		final Class<?> klazz = (Class<?>) from.getJavaType();
		final TenantType qualifier = getType(klazz);
		final Instance<RepositoryTenantable<?>> instance = tenants.select(qualifier);
		if (instance.isUnsatisfied()) {
			logger.debug("Tenant para {} não encontrado", klazz.getSimpleName());
			throw new TenantNotFound();
		} else {
			logger.debug("Adicionando Tenant para {}", klazz.getSimpleName());
			final RepositoryTenantable<T> tenant = getInstance(instance);
			return tenant.add(joinner, builder, from, tenantValue);
		}
	}
	
	protected <T> RepositoryTenantable<T> getInstance(final Instance<RepositoryTenantable<?>> instance){
		@SuppressWarnings("unchecked")
		final RepositoryTenantable<T> t = (RepositoryTenantable<T>) instance.get();
		final RepositoryTenantable<T> tenant = t;
		return tenant;
	}
	
	public <T>Predicate run(final Tenantable<T> tenant, final CriteriaBuilder builder, final From<?, T> from, final Object value){
		throw new UnsupportedOperationException();
	}
}
