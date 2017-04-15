package br.eti.clairton.repository.tenant;

import static br.eti.clairton.repository.tenant.Recurso_.aplicacao;
import static javax.persistence.criteria.JoinType.INNER;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

import br.eti.clairton.repository.Joinner;
import br.eti.clairton.tenant.TenantType;

@Dependent
@TenantType(Recurso.class)
public class RecursoTenant extends RepositoryTenantable<Recurso> {
	private final RepositoryTenantBuilder builder;
	
	@Deprecated
	public RecursoTenant() {
		this(null);
	}

	@Inject
	public RecursoTenant(final RepositoryTenantBuilder builder) {
		this.builder = builder;
	}

	@Override
	public Predicate add(@NotNull final Joinner joinner, final CriteriaBuilder builder, final From<?, Recurso> from, final Object value) {
		final Join<?, ?> j = joinner.join(from, INNER, aplicacao);
		@SuppressWarnings("unchecked")
		final Join<Recurso, Aplicacao> join = (Join<Recurso, Aplicacao>) j;
		return this.builder.run(joinner, builder, join, value);
	}
}
