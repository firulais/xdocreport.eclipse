package org.dynaresume.dao;

import org.dynaresume.domain.hr.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillDao extends PagingAndSortingRepository<Skill, Long> {

	Page<Skill> findByNameLike(String name, Pageable pageable);

}
