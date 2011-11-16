package org.dynaresume.dao;

import java.util.Collection;

import org.dynaresume.domain.hr.Skill;

public interface SkillDao {

	Collection<Skill> findAll();

	Skill findById(long id);

	Skill save(Skill skill);
}
