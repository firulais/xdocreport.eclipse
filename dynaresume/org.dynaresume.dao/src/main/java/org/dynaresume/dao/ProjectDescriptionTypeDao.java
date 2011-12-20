package org.dynaresume.dao;

import org.dynaresume.domain.project.ProjectDescriptionType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectDescriptionTypeDao extends
		PagingAndSortingRepository<ProjectDescriptionType, Long> {
}
