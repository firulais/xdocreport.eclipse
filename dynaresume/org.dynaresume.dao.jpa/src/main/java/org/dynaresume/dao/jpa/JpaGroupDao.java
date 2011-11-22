package org.dynaresume.dao.jpa;

import org.dynaresume.dao.GroupDao;
import org.dynaresume.domain.core.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class JpaGroupDao implements GroupDao {

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delete(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Group arg0) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Iterable<? extends Group> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<Group> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Group findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Group save(Group arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Group> save(Iterable<? extends Group> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Group> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Group> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
