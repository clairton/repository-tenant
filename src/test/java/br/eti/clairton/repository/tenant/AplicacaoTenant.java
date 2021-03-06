package br.eti.clairton.repository.tenant;

import javax.enterprise.context.Dependent;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import br.eti.clairton.repository.Joinner;
import br.eti.clairton.tenant.TenantType;

@Dependent
@TenantType(Aplicacao.class)
public class AplicacaoTenant extends RepositoryTenantable<Aplicacao> {
	@Override
	public Predicate add(final Joinner joinner, final CriteriaBuilder builder, final From<?, Aplicacao> from, final Object value) {
		final Path<String> path = from.get(Aplicacao_.nome);
		final Predicate predicate = builder.notEqual(path, value);
		return predicate;
	}
}
