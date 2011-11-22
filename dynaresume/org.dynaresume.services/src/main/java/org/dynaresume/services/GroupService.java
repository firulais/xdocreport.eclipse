package org.dynaresume.services;

import org.dynaresume.domain.core.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {

	Iterable<Group> findAll();

	Group findById(long id);

	Group save(Group group);
	
	Page<Group> findByName(String name, Pageable pageable);

}
