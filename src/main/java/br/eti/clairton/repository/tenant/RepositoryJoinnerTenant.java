package br.eti.clairton.repository.tenant;

import javax.enterprise.inject.Vetoed;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import br.eti.clairton.repository.Joinner;
import br.eti.clairton.tenant.TenantBuilder;
import br.eti.clairton.tenant.TenantNotFound;

/**
 * Joinner by {@link br.eti.clairton.repository.Predicate} with tenant.
 * 
 * @author Clairton Rodrigo Heinzen clairton.rodrigo@gmail.com
 */
@Vetoed
public class RepositoryJoinnerTenant extends Joinner{
	private final RepositoryTenantBuilder tenant;
	private final Value<?> value;

	/**
	 * CDI only.
	 */
	@Deprecated
	public RepositoryJoinnerTenant() {
		this(null, null, null, null);
	}
	
	/**
	 * Constructor default.
	 * 
	 * @param tenant
	 *            instance of {@link TenantBuilder}
	 * 
	 * @param builder
	 *            instance of {@link CriteriaBuilder}
	 * @param from
	 *            instance of {@link From}
	 * 
	 * @param value
	 * 			 value of tenant
	 */
	public RepositoryJoinnerTenant(
			final RepositoryTenantBuilder tenant, 
			final CriteriaBuilder builder, 
			final From<?, ?> from,
			final Value<?> value) {
		super(builder, from);
		this.tenant = tenant;
		this.value = value;
	}
	
	/**
	 * {@inheritDoc}
	 * Concat {@link Predicate} of super.join with tenant.
	 */
	@Override
	public Predicate join(final br.eti.clairton.repository.Predicate predicate) {
		final Predicate joinPredicate = super.join(predicate);
		try {
			final Predicate tenantPredicate = tenant.run(this, builder, fromLast, value.get());
			final Predicate completePredicate = builder.and(joinPredicate, tenantPredicate);
			return completePredicate;
		} catch (final TenantNotFound e) {
			return joinPredicate;
		}
	}
}
