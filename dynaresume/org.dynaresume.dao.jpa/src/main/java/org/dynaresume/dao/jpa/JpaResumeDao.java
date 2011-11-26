package org.dynaresume.dao.jpa;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dynaresume.dao.ResumeDao;
import org.dynaresume.domain.hr.Resume;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("resumeDao")
@Transactional(readOnly = true)
public class JpaResumeDao extends AbstractDao<Resume, Long> implements ResumeDao {




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

	/**
	 * Creates a {@link TypedQuery} for the given {@link Specification} and
	 * {@link Sort}.
	 * 
	 * @param spec
	 * @param sort
	 * @return
	 */
	TypedQuery<Resume> getQuery(Specification<Resume> spec, Sort sort) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Resume> query = builder.createQuery(Resume.class);

		Root<Resume> root = applySpecificationToCriteria(spec, query);
		query.select(root);

		if (sort != null) {
			// query.orderBy(toOrders(sort, root, builder));
		}

		return em.createQuery(query);
	}

	public Iterable<Resume> findAll() {
		return getQuery(null, (Sort) null).getResultList();
	}

	@Override
	public Resume findOne(Long id) {
		

		
			return em.find(Resume.class, id);
		
	}

	@Override
	public Resume save(Resume entity) {

		if (entity.getId()==null) {
			em.persist(entity);
			return entity;
		} else {
			return em.merge(entity);
		}

	}

}
