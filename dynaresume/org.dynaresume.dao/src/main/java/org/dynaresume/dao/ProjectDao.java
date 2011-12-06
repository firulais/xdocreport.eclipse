package org.dynaresume.dao;

import org.dynaresume.domain.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectDao extends PagingAndSortingRepository<Project, Long> {

	 Page<Project> findByNameLike(String name,Pageable pageable);
}
