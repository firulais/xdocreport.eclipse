package org.dynaresume.dao;

import org.dynaresume.domain.core.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupDao extends PagingAndSortingRepository<Group, Long> {

	 Page<Group> findByNameLike(String name,Pageable pageable);
}
