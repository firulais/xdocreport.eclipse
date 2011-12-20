package org.dynaresume.services.impl;

import org.dynaresume.dao.ProjectDescriptionTypeDao;
import org.dynaresume.domain.project.ProjectDescriptionType;
import org.dynaresume.services.ProjectDescriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("projectDescriptionTypeService")
public class ProjectDescriptionTypeServiceImpl implements
		ProjectDescriptionTypeService {

	@Autowired
	private ProjectDescriptionTypeDao projectDescriptionTypeDao;

	public Iterable<ProjectDescriptionType> findAll() {
		return projectDescriptionTypeDao.findAll();
	}

	public ProjectDescriptionType findById(long id) {
		return projectDescriptionTypeDao.findOne(id);
	}

	public ProjectDescriptionType save(ProjectDescriptionType type) {
		return projectDescriptionTypeDao.save(type);
	}
}
