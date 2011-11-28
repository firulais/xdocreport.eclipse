package org.dynaresume.services.impl;

import org.dynaresume.dao.GroupDao;
import org.dynaresume.domain.core.Group;
import org.dynaresume.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;

	public Iterable<Group> findAll() {
		return groupDao.findAll();
	}

	public Group findById(long id) {
		return groupDao.findOne(id);
	}

	public Group save(Group group) {
		return groupDao.save(group);
	}

	public Page<Group> findByName(String name, Pageable pageable) {
		return groupDao.findByNameLike(name, pageable);
	}

	

}
