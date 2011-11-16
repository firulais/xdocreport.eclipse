package org.dynaresume.dao.hibernate.skills;

import org.dynaresume.dao.hibernate.SkillDaoHibernate;
import org.dynaresume.domain.hr.Skill;

public class JavaSkill extends Skill  {

	public JavaSkill() {
		super.setId(SkillDaoHibernate.getCurrentId());
		super.setLabel("Java");
	}
}
