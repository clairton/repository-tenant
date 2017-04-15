package br.eti.clairton.repository.tenant;

import static java.util.logging.Level.FINE;
import static java.util.logging.Logger.getLogger;

import java.util.logging.Logger;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

import br.eti.clairton.repository.Joinner;
import br.eti.clairton.tenant.TenantBuilder;
import br.eti.clairton.tenant.TenantNotFound;
import br.eti.clairton.tenant.TenantType;
import br.eti.clairton.tenant.Tenantable;

@Specializes
public class RepositoryTenantBuilder extends TenantBuilder {
	private static final Logger logger = getLogger(TenantBuilder.class.getSimpleName());
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
	
	@Override
	public <T> Predicate run(@NotNull final CriteriaBuilder builder, @NotNull final From<?, T> from, final Object value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Add the tenant for de {@link From}, id exists.
	 * 
	 * @param builder
	 *            instance of {@link CriteriaBuilder}
	 * @param from
	 *            instance of de current {@link From}
	 * @param value
	 *            value of the tenant
	 * @param <T> type of entity
	 * @return {@link Predicate} of the tenant for the {@link From} param
	 */
	public <T> Predicate run(final Joinner joinner, final CriteriaBuilder builder, @NotNull final From<?, T> from, final Object value) {
		final Class<?> klazz = (Class<?>) from.getJavaType();
		final TenantType qualifier = getType(klazz);
		final Instance<RepositoryTenantable<?>> instance = tenants.select(qualifier);
		if (instance.isUnsatisfied()) {
			logger.log(FINE, "Tenant para {} não encontrado", klazz.getSimpleName());
			throw new TenantNotFound();
		} else {
			logger.log(FINE, "Adicionando Tenant para {}", klazz.getSimpleName());
			final RepositoryTenantable<T> tenant = getInstance(instance);
			return tenant.add(joinner, builder, from, value);
		}
	}
	
	@Deprecated
	public <T> Predicate run(final Tenantable<T> tenant, final CriteriaBuilder builder, final From<?, T> from, final Object value) {
		throw new UnsupportedOperationException();
	}

	protected <T> RepositoryTenantable<T> getInstance(final Instance<RepositoryTenantable<?>> instance) {
		@SuppressWarnings("unchecked")
		final RepositoryTenantable<T> t = (RepositoryTenantable<T>) instance.get();
		final RepositoryTenantable<T> tenant = t;
		return tenant;
	}
}
