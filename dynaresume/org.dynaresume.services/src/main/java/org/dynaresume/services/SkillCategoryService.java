package org.dynaresume.services;

import org.dynaresume.domain.hr.SkillCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SkillCategoryService {

	Iterable<SkillCategory> findAll();
	
	Iterable<SkillCategory> findAllRoot();

	SkillCategory findById(long id);

	SkillCategory save(SkillCategory skill);

	Page<SkillCategory> findAll(Pageable pageable);

	Page<SkillCategory> findByLabel(String label, Pageable pageable);

}
