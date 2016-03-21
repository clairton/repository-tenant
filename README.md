# repository-tenant[![Build Status](https://drone.io/github.com/clairton/repository-tenant/status.png)](https://drone.io/github.com/clairton/repository-tenant/latest)

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

Para usar será necessário adicionar os repositórios maven:

```xml
<repository>
	<id>mvn-repo-releases</id>
	<url>https://raw.github.com/clairton/mvn-repo/releases</url>
</repository>
<repository>
	<id>mvn-repo-snapshot</id>
	<url>https://raw.github.com/clairton/mvn-repo/snapshots</url>
</repository>
```
 Também adicionar as depêndencias:
```xml
<dependency>
    <groupId>br.eti.clairton</groupId>
	<artifactId>repository-tenant</artifactId>
	<version>0.1.0</version>
</dependency>
```
