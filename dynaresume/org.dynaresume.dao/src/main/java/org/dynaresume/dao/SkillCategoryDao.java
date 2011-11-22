package org.dynaresume.dao;

import org.dynaresume.domain.hr.SkillCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillCategoryDao extends CrudRepository<SkillCategory, Long>,
		PagingAndSortingRepository<SkillCategory, Long> {

}
