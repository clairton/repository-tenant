# repository-tenant [![Build Status](https://travis-ci.org/clairton/repository-tenant.svg?branch=master)](https://travis-ci.org/clairton/repository-tenant)

Integrado ao projeto https://github.com/clairton/repository e https://github.com/clairton/tenant.
```java
repository.from(Aplicacao.class).tenantValue("ValorFiltrado");
```

Injetar um repository com o qualifier @Tenant, para isso será necessário implementar
o contrato TenantValue.
```java
@RequestScoped
public class TenantValueObject implements TenantValue<String> {

	@Override
	public String get() {
		return "Valor que não deve aparecer na consulta";
	}

}
...
@Inject @Tenant Repository repository;
...
repository.from(Aplicacao.class).collection();
```

Para usar será necessário adicionar as depêndencias:
```xml
<dependency>
    <groupId>br.eti.clairton</groupId>
	<artifactId>repository-tenant</artifactId>
	<version>${latest}</version>
</dependency>
```
