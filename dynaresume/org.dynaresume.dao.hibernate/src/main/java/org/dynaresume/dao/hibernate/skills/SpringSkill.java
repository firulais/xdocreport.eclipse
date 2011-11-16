package org.dynaresume.dao.hibernate.skills;

import org.dynaresume.dao.hibernate.SkillDaoHibernate;
import org.dynaresume.domain.hr.Skill;

public class SpringSkill extends Skill  {

	public SpringSkill(Skill parent) {
		super.setId(SkillDaoHibernate.getCurrentId());
		super.setLabel("Spring");
		super.setParent(parent);
	}
}
