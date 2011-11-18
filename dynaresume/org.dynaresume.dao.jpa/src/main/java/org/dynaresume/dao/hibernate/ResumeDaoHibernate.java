package org.dynaresume.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dynaresume.dao.ResumeDao;
import org.dynaresume.domain.hr.Resume;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class ResumeDaoHibernate implements ResumeDao {

	public static final String COUNT_QUERY_STRING = "select count(*) from Resume x";

	public static final String DELETE_ALL_QUERY_STRING = "delete from Resume x";


	public static final String EXISTS_QUERY_STRING = "select count(*) from Resume x where x.id = :id";

	@PersistenceContext
	private EntityManager em;

	public ResumeDaoHibernate() {

	}

	/**
	 * Applies the given {@link Specification} to the given
	 * {@link CriteriaQuery}.
	 * 
	 * @param spec
	 *            can be {@literal null}
	 * @param query
	 * @return
	 */
	private <S> Root<Resume> applySpecificationToCriteria(
			Specification<Resume> spec, CriteriaQuery<S> query) {

		Root<Resume> root = query.from(Resume.class);

		if (spec == null) {
			return root;
		}

		CriteriaBuilder builder = em.getCriteriaBuilder();
		Predicate predicate = spec.toPredicate(root, query, builder);

		if (predicate != null) {
			query.where(predicate);
		}

		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.Repository#count()
	 */
	public long count() {
		return em.createQuery(COUNT_QUERY_STRING, Long.class).getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.Repository#delete(java.lang.Iterable)
	 */
	@Transactional
	public void delete(Iterable<? extends Resume> entities) {

		if (entities == null) {
			return;
		}

		for (Resume entity : entities) {
			delete(entity);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.jpa.repository.JpaRepository#delete(java.io.
	 * Serializable)
	 */
	@Transactional
	public void delete(Long id) {

		delete(findOne(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.Repository#delete(java.lang.Object)
	 */
	@Transactional
	public void delete(Resume entity) {

		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.Repository#deleteAll()
	 */
	@Transactional
	public void deleteAll() {

		em.createQuery(DELETE_ALL_QUERY_STRING).executeUpdate();
		em.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#exists(java.io.
	 * Serializable)
	 */
	public boolean exists(Long id) {

		// String placeholder = provider.getCountQueryPlaceholder();
		// String entityName = entityInformation.getEntityName();
		// String idAttributeName =
		// entityInformation.getIdAttribute().getName();
		//
		// String existsQuery = String.format(EXISTS_QUERY_STRING, placeholder,
		// entityName, idAttributeName);

		TypedQuery<Long> query = em
				.createQuery(EXISTS_QUERY_STRING, Long.class);
		query.setParameter("id", id);

		return query.getSingleResult() == 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<Resume> findAll() {

		return getQuery(null, (Sort) null).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.Repository#readAll(org.springframework
	 * .data.domain.Sort)
	 */
	public List<Resume> findAll(Sort sort) {

		return getQuery(null, sort).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.Repository#readById(java.io.Serializable
	 * )
	 */
	public Resume findOne(Long id) {

		return em.find(Resume.class, id);
	}

	/**
	 * Creates a {@link TypedQuery} for the given {@link Specification} and
	 * {@link Sort}.
	 * 
	 * @param spec
	 * @param sort
	 * @return
	 */
	private TypedQuery<Resume> getQuery(Specification<Resume> spec, Sort sort) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Resume> query = builder.createQuery(Resume.class);

		Root<Resume> root = applySpecificationToCriteria(spec, query);
		query.select(root);

		if (sort != null) {
			// query.orderBy(toOrders(sort, root, builder));
		}

		return em.createQuery(query);
	}

	public Iterable<Resume> save(Iterable<? extends Resume> entities) {

		List<Resume> result = new ArrayList<Resume>();

		if (entities == null) {
			return result;
		}

		for (Resume entity : entities) {
			result.add(save(entity));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.Repository#save(java.lang.Object)
	 */
	@Transactional
	public Resume save(Resume entity) {

		if (entity.getId() != null) {
			em.persist(entity);
			return entity;
		} else {
			return em.merge(entity);
		}
	}

}
