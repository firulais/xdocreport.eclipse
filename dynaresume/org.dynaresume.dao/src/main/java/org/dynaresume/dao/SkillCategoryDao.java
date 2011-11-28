package org.dynaresume.dao;

import org.dynaresume.domain.hr.SkillCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillCategoryDao extends PagingAndSortingRepository<SkillCategory, Long> {

	Page<SkillCategory> findByLabel(String label, Pageable pageable);

	Iterable<SkillCategory> findByParentIsNull();
}
