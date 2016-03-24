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

import br.eti.clairton.repository.Joinner;

public class FromDecorator<Z, X> implements From<Z, X> {
	private final From<Z, X> from;
	private final Joinner joinner;
	private final CriteriaBuilder builder;

	public FromDecorator(final From<Z, X> from, final Joinner joinner, final CriteriaBuilder builder) {
		super();
		this.from = from;
		this.joinner = joinner;
		this.builder = builder;
	}

	public Class<? extends X> getJavaType() {
		return from.getJavaType();
	}

	public Predicate isNull() {
		return from.isNull();
	}

	public String getAlias() {
		return from.getAlias();
	}

	public Selection<X> alias(String name) {
		return from.alias(name);
	}

	public Set<Fetch<X, ?>> getFetches() {
		return from.getFetches();
	}

	public Predicate isNotNull() {
		return from.isNotNull();
	}

	public Bindable<X> getModel() {
		return from.getModel();
	}

	public Predicate in(Object... values) {
		return from.in(values);
	}

	public boolean isCompoundSelection() {
		return from.isCompoundSelection();
	}

	public Path<?> getParentPath() {
		return from.getParentPath();
	}

	public <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute) {
		return from.fetch(attribute);
	}

	public List<Selection<?>> getCompoundSelectionItems() {
		return from.getCompoundSelectionItems();
	}

	public <Y> Path<Y> get(SingularAttribute<? super X, Y> attribute) {
		return from.get(attribute);
	}

	public Predicate in(Expression<?>... values) {
		return from.in(values);
	}

	public Set<Join<X, ?>> getJoins() {
		return from.getJoins();
	}

	public <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute, JoinType jt) {
		return from.fetch(attribute, jt);
	}

	public <E, C extends Collection<E>> Expression<C> get(PluralAttribute<X, C, E> collection) {
		return from.get(collection);
	}

	public Predicate in(Collection<?> values) {
		return from.in(values);
	}

	public boolean isCorrelated() {
		return from.isCorrelated();
	}

	public <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> attribute) {
		return from.fetch(attribute);
	}

	public Predicate in(Expression<Collection<?>> values) {
		return from.in(values);
	}

	public <K, V, M extends Map<K, V>> Expression<M> get(MapAttribute<X, K, V> map) {
		return from.get(map);
	}

	public From<Z, X> getCorrelationParent() {
		return from.getCorrelationParent();
	}

	public <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> attribute, JoinType jt) {
		return from.fetch(attribute, jt);
	}

	public <T> Expression<T> as(Class<T> type) {
		return from.as(type);
	}

	public Expression<Class<? extends X>> type() {
		return from.type();
	}

	public <T, Y> Fetch<T, Y> fetch(String attributeName) {
		return from.fetch(attributeName);
	}

	public <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute) {
		@SuppressWarnings("unchecked")
		final Join<X, Y> j = (Join<X, Y>) joinner.join(builder, from, INNER, attribute);		
		return j;
	}

	public <Y> Path<Y> get(String attributeName) {
		return from.get(attributeName);
	}

	public <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute, JoinType jt) {
		@SuppressWarnings("unchecked")
		final Join<X, Y> j = (Join<X, Y>) joinner.join(builder, from, jt, attribute);		
		return j;
	}

	public <T, Y> Fetch<T, Y> fetch(String attributeName, JoinType jt) {
		return from.fetch(attributeName, jt);
	}

	public <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection) {
		@SuppressWarnings("unchecked")
		final CollectionJoin<X, Y> j = (CollectionJoin<X, Y>) joinner.join(builder, from, INNER, collection);		
		return j;
	}

	public <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set) {
		@SuppressWarnings("unchecked")
		final SetJoin<X, Y> j = (SetJoin<X, Y>) joinner.join(builder, from, INNER, set);		
		return j;
	}

	public <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list) {
		@SuppressWarnings("unchecked")
		final ListJoin<X, Y> j = (ListJoin<X, Y>) joinner.join(builder, from, INNER, list);		
		return j;
	}

	public <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map) {
		@SuppressWarnings("unchecked")
		final MapJoin<X, K, V> j = (MapJoin<X, K, V>) joinner.join(builder, from, INNER, map);		
		return j;
	}

	public <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection, JoinType jt) {
		@SuppressWarnings("unchecked")
		final CollectionJoin<X, Y> j = (CollectionJoin<X, Y>) joinner.join(builder, from, jt, collection);		
		return j;
	}

	public <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set, JoinType jt) {
		return from.join(set, jt);
	}

	public <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list, JoinType jt) {
		@SuppressWarnings("unchecked")
		final ListJoin<X, Y> j = (ListJoin<X, Y>) joinner.join(builder, from, jt, list);		
		return j;
	}

	public <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map, JoinType jt) {
		@SuppressWarnings("unchecked")
		final MapJoin<X, K, V> j = (MapJoin<X, K, V>) joinner.join(builder, from, jt, map);		
		return j;
	}

	public <T, Y> Join<T, Y> join(String attributeName) {
		return from.join(attributeName);
	}

	public <T, Y> CollectionJoin<T, Y> joinCollection(String attributeName) {
		return from.joinCollection(attributeName);
	}

	public <T, Y> SetJoin<T, Y> joinSet(String attributeName) {
		return from.joinSet(attributeName);
	}

	public <T, Y> ListJoin<T, Y> joinList(String attributeName) {
		return from.joinList(attributeName);
	}

	public <T, K, V> MapJoin<T, K, V> joinMap(String attributeName) {
		return from.joinMap(attributeName);
	}

	public <T, Y> Join<T, Y> join(String attributeName, JoinType jt) {
		return from.join(attributeName, jt);
	}

	public <T, Y> CollectionJoin<T, Y> joinCollection(String attributeName, JoinType jt) {
		return from.joinCollection(attributeName, jt);
	}

	public <T, Y> SetJoin<T, Y> joinSet(String attributeName, JoinType jt) {
		return from.joinSet(attributeName, jt);
	}

	public <T, Y> ListJoin<T, Y> joinList(String attributeName, JoinType jt) {
		return from.joinList(attributeName, jt);
	}

	public <T, K, V> MapJoin<T, K, V> joinMap(String attributeName, JoinType jt) {
		return from.joinMap(attributeName, jt);
	}
}
