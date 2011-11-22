package org.dynaresume.services;

import org.dynaresume.domain.hr.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SkillService {

	Iterable<Skill> findAll();

	Skill findById(long id);

	Skill save(Skill skill);

	Page<Skill> findAll(Pageable pageable);

	Page<Skill> findByLabel(String label, Pageable pageable);

}
