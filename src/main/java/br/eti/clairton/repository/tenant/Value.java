package br.eti.clairton.repository.tenant;

/**
 * Valor para o Tenant
 * 
 * @author Clairton Rodrigo Heinzen clairton.rodrigo@gmail.com
 */
public interface Value<T> {

	/**
	 * Mus me retrieve and return de tenant value.
	 * 
	 * @return value of tenant
	 */
	T get();
}
