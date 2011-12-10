package org.dynaresume.dao;

import org.dynaresume.dao.custom.SkillRepositoryCustom;
import org.dynaresume.domain.hr.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillDao extends PagingAndSortingRepository<Skill, Long>,SkillRepositoryCustom {

	Page<Skill> findByNameLike(String name, Pageable pageable);

}
