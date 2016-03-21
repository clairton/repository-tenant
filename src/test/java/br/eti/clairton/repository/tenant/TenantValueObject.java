package br.eti.clairton.repository.tenant;

import javax.enterprise.context.RequestScoped;

import br.eti.clairton.repository.tenant.TenantValue;

@RequestScoped
public class TenantValueObject implements TenantValue<String> {
	
	public String get() {
		return "OutroTesteQueNãoDeveAparecerNaConsulta";
	}
}
