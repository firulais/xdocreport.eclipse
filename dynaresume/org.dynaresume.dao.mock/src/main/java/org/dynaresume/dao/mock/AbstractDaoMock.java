package org.dynaresume.dao.mock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class AbstractDaoMock<T> implements CrudRepository<T, Long>,
		PagingAndSortingRepository<T, Long> {

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

}
