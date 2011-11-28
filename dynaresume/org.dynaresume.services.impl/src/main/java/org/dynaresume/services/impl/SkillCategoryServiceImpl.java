package org.dynaresume.services.impl;

import org.dynaresume.dao.SkillCategoryDao;
import org.dynaresume.domain.hr.SkillCategory;
import org.dynaresume.services.SkillCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("skillCategoryService")
public class SkillCategoryServiceImpl implements SkillCategoryService {

	@Autowired
	private SkillCategoryDao categoryDao;


	public Iterable<SkillCategory> findAll() {
		return categoryDao.findAll();
	}
	
	public Iterable<SkillCategory> findAllRoot() {
		return categoryDao.findByParentIsNull();
		
	}

	public SkillCategory findById(long id) {
		return categoryDao.findOne(id);
	}

	public SkillCategory save(SkillCategory category) {
		return categoryDao.save(category);
	}

	public Page<SkillCategory> findAll(Pageable pageable) {
		return categoryDao.findAll(pageable);
	}

	public Page<SkillCategory> findByLabel(String label, Pageable pageable) {
		return categoryDao.findByLabel(label, pageable);
	}

}
