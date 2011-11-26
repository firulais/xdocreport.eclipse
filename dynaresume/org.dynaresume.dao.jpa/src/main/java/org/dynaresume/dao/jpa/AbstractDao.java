package org.dynaresume.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDao<T,ID extends Serializable> implements CrudRepository<T,ID> {
	@PersistenceContext EntityManager em;
	

	
	
	public static final String COUNT_QUERY_STRING = "select count(x) from Resume x";

	public static final String DELETE_ALL_QUERY_STRING = "delete from Resume x";


	public static final String EXISTS_QUERY_STRING = "select count(x) from Resume x where x.id = :id";

	public long count() {
		return em.createQuery(COUNT_QUERY_STRING, Long.class).getSingleResult();
	}

	@Transactional
	public void delete(Iterable<? extends T> entities) {
	
		if (entities == null) {
			return;
		}
	
		for (T entity : entities) {
			delete(entity);
		}
	}

	@Transactional
	public void delete(ID id) {
	
		delete(findOne(id));
	}

	@Transactional
	public void delete(T entity) {
	
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	@Transactional
	public void deleteAll() {
	
		em.createQuery(DELETE_ALL_QUERY_STRING).executeUpdate();
		em.clear();
	}

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

//	public List<Resume> findAll() {
//	
//		return getQuery(null, (Sort) null).getResultList();
//	}
//
//	public List<Resume> findAll(Sort sort) {
//	
//		return getQuery(null, sort).getResultList();
//	}

	public abstract T findOne(ID id) ;

	public Iterable<T> save(Iterable<? extends T> entities) {
	
		List<T> result = new ArrayList<T>();
	
		if (entities == null) {
			return result;
		}
	
		for (T entity : entities) {
			result.add(save(entity));
		}
	
		return result;
	}
	public abstract T save(T entity);
	
//
//	@Transactional
//	public T save(T entity) {
//	
//		if (entity.getId() != null) {
//			em.persist(entity);
//			return entity;
//		} else {
//			return em.merge(entity);
//		}
//	}

}