package br.eti.clairton.repository.tenant;

import static javax.persistence.criteria.JoinType.INNER;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;


/**
 * {@link From} decorator do add tenant in join.
 * 
 * @author Clairton Rodrigo Heinzen <clairton.rodrigo@gmail.com>
 *
 * @param <Z>  the source type
 * @param <X>  the target type
 */
public class FromDecorator<Z, X> implements From<Z, X> {
	private final From<Z, X> from;
	private final RepositoryJoinnerTenant joinner;
	private final CriteriaBuilder builder;

	/**
	 * Default Constructor.
	 * 
	 * @param from
	 *            original instance {@link From}
	 * @param joinner
	 *            instance of {@link RepositoryJoinnerTenant}
	 * @param builder
	 *            instance of {@link CriteriaBuilder}
	 */
	public FromDecorator(final From<Z, X> from, final RepositoryJoinnerTenant joinner, final CriteriaBuilder builder) {
		super();
		this.from = from;
		this.joinner = joinner;
		this.builder = builder;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends X> getJavaType() {
		return from.getJavaType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Predicate isNull() {
		return from.isNull();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAlias() {
		return from.getAlias();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Selection<X> alias(String name) {
		return from.alias(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Fetch<X, ?>> getFetches() {
		return from.getFetches();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Predicate isNotNull() {
		return from.isNotNull();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bindable<X> getModel() {
		return from.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Predicate in(Object... values) {
		return from.in(values);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCompoundSelection() {
		return from.isCompoundSelection();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Path<?> getParentPath() {
		return from.getParentPath();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute) {
		return from.fetch(attribute);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Selection<?>> getCompoundSelectionItems() {
		return from.getCompoundSelectionItems();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> Path<Y> get(SingularAttribute<? super X, Y> attribute) {
		return from.get(attribute);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Predicate in(Expression<?>... values) {
		return from.in(values);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Join<X, ?>> getJoins() {
		return from.getJoins();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute, JoinType jt) {
		return from.fetch(attribute, jt);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E, C extends Collection<E>> Expression<C> get(PluralAttribute<X, C, E> collection) {
		return from.get(collection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Predicate in(Collection<?> values) {
		return from.in(values);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCorrelated() {
		return from.isCorrelated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> attribute) {
		return from.fetch(attribute);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Predicate in(Expression<Collection<?>> values) {
		return from.in(values);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <K, V, M extends Map<K, V>> Expression<M> get(MapAttribute<X, K, V> map) {
		return from.get(map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public From<Z, X> getCorrelationParent() {
		return from.getCorrelationParent();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> attribute, JoinType jt) {
		return from.fetch(attribute, jt);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> Expression<T> as(Class<T> type) {
		return from.as(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Expression<Class<? extends X>> type() {
		return from.type();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, Y> Fetch<T, Y> fetch(String attributeName) {
		return from.fetch(attributeName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute) {
		@SuppressWarnings("unchecked")
		final Join<X, Y> j = (Join<X, Y>) joinner.join(builder, from, INNER, attribute);		
		return j;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> Path<Y> get(String attributeName) {
		return from.get(attributeName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute, JoinType jt) {
		@SuppressWarnings("unchecked")
		final Join<X, Y> j = (Join<X, Y>) joinner.join(builder, from, jt, attribute);		
		return j;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, Y> Fetch<T, Y> fetch(String attributeName, JoinType jt) {
		return from.fetch(attributeName, jt);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection) {
		@SuppressWarnings("unchecked")
		final CollectionJoin<X, Y> j = (CollectionJoin<X, Y>) joinner.join(builder, from, INNER, collection);		
		return j;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set) {
		@SuppressWarnings("unchecked")
		final SetJoin<X, Y> j = (SetJoin<X, Y>) joinner.join(builder, from, INNER, set);		
		return j;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list) {
		@SuppressWarnings("unchecked")
		final ListJoin<X, Y> j = (ListJoin<X, Y>) joinner.join(builder, from, INNER, list);		
		return j;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map) {
		@SuppressWarnings("unchecked")
		final MapJoin<X, K, V> j = (MapJoin<X, K, V>) joinner.join(builder, from, INNER, map);		
		return j;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection, JoinType jt) {
		@SuppressWarnings("unchecked")
		final CollectionJoin<X, Y> j = (CollectionJoin<X, Y>) joinner.join(builder, from, jt, collection);		
		return j;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set, JoinType jt) {
		return from.join(set, jt);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list, JoinType jt) {
		@SuppressWarnings("unchecked")
		final ListJoin<X, Y> j = (ListJoin<X, Y>) joinner.join(builder, from, jt, list);		
		return j;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map, JoinType jt) {
		@SuppressWarnings("unchecked")
		final MapJoin<X, K, V> j = (MapJoin<X, K, V>) joinner.join(builder, from, jt, map);		
		return j;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, Y> Join<T, Y> join(String attributeName) {
		return from.join(attributeName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, Y> CollectionJoin<T, Y> joinCollection(String attributeName) {
		return from.joinCollection(attributeName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, Y> SetJoin<T, Y> joinSet(String attributeName) {
		return from.joinSet(attributeName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, Y> ListJoin<T, Y> joinList(String attributeName) {
		return from.joinList(attributeName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, K, V> MapJoin<T, K, V> joinMap(String attributeName) {
		return from.joinMap(attributeName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, Y> Join<T, Y> join(String attributeName, JoinType jt) {
		return from.join(attributeName, jt);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, Y> CollectionJoin<T, Y> joinCollection(String attributeName, JoinType jt) {
		return from.joinCollection(attributeName, jt);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, Y> SetJoin<T, Y> joinSet(String attributeName, JoinType jt) {
		return from.joinSet(attributeName, jt);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, Y> ListJoin<T, Y> joinList(String attributeName, JoinType jt) {
		return from.joinList(attributeName, jt);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, K, V> MapJoin<T, K, V> joinMap(String attributeName, JoinType jt) {
		return from.joinMap(attributeName, jt);
	}
}
