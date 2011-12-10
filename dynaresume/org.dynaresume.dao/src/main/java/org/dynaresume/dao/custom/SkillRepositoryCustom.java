package org.dynaresume.dao.custom;

import java.util.List;

import org.dynaresume.domain.hr.Skill;

public interface SkillRepositoryCustom {
	public Iterable<Skill> findByNames(List<String> names) ;
}
