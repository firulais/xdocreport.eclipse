package org.dynaresume.dao;

import org.dynaresume.domain.hr.Skill;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillDao extends PagingAndSortingRepository<Skill, Long> {

}
