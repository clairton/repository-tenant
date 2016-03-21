package br.eti.clairton.repository.tenant;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableCollection;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.eti.clairton.identificator.Identificator;
import br.eti.clairton.repository.Model;

/**
 * Representa uma Aplicação.
 * 
 * @author Clairton Rodrigo Heinzen<clairton.rodrigo@gmail.com>
 */
@Entity
@Table(name = "aplicacoes")
public class Aplicacao extends Model {
	private static final long serialVersionUID = 1L;

	@NotNull
	@OneToMany(mappedBy = "aplicacao")
	private Collection<Recurso> recursos = new HashSet<Recurso>();

	@NotNull
	@Size(min = 1, max = 250)
	@Identificator
	private String nome;

	/*
	 * Atributo que server somente para testar os metodos equals, hashCode e
	 * toString
	 */
	private final @Transient Long transientField = new Date().getTime();

	/**
	 * Construtor padrão.
	 */
	@Deprecated
	protected Aplicacao() {
	}

	/**
	 * Construtor com argumentos.
	 * 
	 * @param nome
	 *            nome da aplicação
	 * @param recursos
	 *            recursos da aplicação
	 */
	public Aplicacao(final String nome, final Collection<Recurso> recursos) {
		super();
		this.nome = nome;
		adicionar(recursos);
	}

	/**
	 * Construtor com argumentos.
	 * 
	 * @param nome
	 *            nome da aplicação
	 * @param recurso
	 *            recurso da aplicação
	 */
	public Aplicacao(final String nome, final Recurso recurso) {
		this(nome, asList(recurso));
	}

	/**
	 * Construtor com parametros.
	 * 
	 * @param nome
	 *            da aplicação
	 */
	public Aplicacao(final String nome) {
		this(nome, Collections.<Recurso> emptyList());
	}

	public void adicionar(Recurso recurso) {
		recursos.add(recurso);
	}

	public void adicionar(Collection<Recurso> recursos) {
		this.recursos.addAll(recursos);
	}

	public void remover(Recurso recurso) {
		recursos.remove(recurso);
	}

	public Collection<Recurso> getRecursos() {
		return unmodifiableCollection(recursos);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
