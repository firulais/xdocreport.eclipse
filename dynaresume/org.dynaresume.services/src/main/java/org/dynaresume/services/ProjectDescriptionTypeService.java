package org.dynaresume.services;

import org.dynaresume.domain.project.ProjectDescriptionType;

public interface ProjectDescriptionTypeService {

	Iterable<ProjectDescriptionType> findAll();

	ProjectDescriptionType findById(long id);

	ProjectDescriptionType save(ProjectDescriptionType type);

}
