package org.dynaresume.services;

import org.dynaresume.domain.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

	Iterable<Project> findAll();

	Project findById(long id);

	Project save(Project group);
	
	Page<Project> findByName(String name, Pageable pageable);
}
