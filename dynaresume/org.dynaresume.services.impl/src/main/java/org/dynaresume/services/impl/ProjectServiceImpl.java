package org.dynaresume.services.impl;

import org.dynaresume.dao.ProjectDao;
import org.dynaresume.domain.project.Project;
import org.dynaresume.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	public Iterable<Project> findAll() {
		return projectDao.findAll();
	}

	public Project findById(long id) {
		return projectDao.findOne(id);
	}

	public Project save(Project project) {
		return projectDao.save(project);
	}

	public Page<Project> findByName(String name, Pageable pageable) {
		return projectDao.findByNameLike(name, pageable);
	}

	

}
