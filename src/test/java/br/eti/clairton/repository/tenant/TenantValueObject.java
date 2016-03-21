package br.eti.clairton.repository.tenant;

import javax.enterprise.context.RequestScoped;

import br.eti.clairton.repository.tenant.Value;

@RequestScoped
public class TenantValueObject implements Value<String> {
	
	public String get() {
		return "OutroTesteQueNãoDeveAparecerNaConsulta";
	}
}
