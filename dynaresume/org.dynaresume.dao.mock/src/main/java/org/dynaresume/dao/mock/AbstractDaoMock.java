package org.dynaresume.dao.mock;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class AbstractDaoMock<T> implements CrudRepository<T, Long>,
		PagingAndSortingRepository<T, Long> {

	private long currentId = 0;

	private final Map<Long, T> data = new LinkedHashMap<Long, T>();

	public Iterable<T> findAll() {
		return data.values();
	}

	public T findOne(Long id) {
		T model = data.get(id);
		if (model != null) {
			return clone(model);
		}
		return null;
	}

	public T save(T model) {
		Long id = getId(model);
		if (id == null) {
			id = getId();
			setId(model, id);
		}
		data.put(id, model);
		return clone(model);
	}

	protected Long getId(T model) {
		try {
			return (Long) model.getClass().getMethod("getId").invoke(model);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void setId(T model, Long id) {
		try {
			model.getClass().getMethod("setId", long.class).invoke(model, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long count() {
		throw new UnsupportedOperationException("Not Implemented");
	}

	public void delete(Long arg0) {
		throw new UnsupportedOperationException("Not Implemented");

	}

	public void delete(T arg0) {
		throw new UnsupportedOperationException("Not Implemented");

	}

	public void delete(Iterable<? extends T> arg0) {
		throw new UnsupportedOperationException("Not Implemented");

	}

	public void deleteAll() {
		throw new UnsupportedOperationException("Not Implemented");

	}

	public boolean exists(Long arg0) {
		throw new UnsupportedOperationException("Not Implemented");
	}

	public Iterable<T> save(Iterable<? extends T> arg0) {
		throw new UnsupportedOperationException("Not Implemented");
	}

	public Iterable<T> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<T> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	protected abstract T clone(T d);

	public synchronized Long getId() {
		return currentId++;
	}
}
