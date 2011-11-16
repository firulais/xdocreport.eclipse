package org.dynaresume.services;

import org.dynaresume.domain.hr.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SkillService {

	Page<Skill> findAll(Pageable pageable);

}
