package org.dynaresume.dao.hibernate.skills;

import org.dynaresume.dao.hibernate.SkillDaoHibernate;
import org.dynaresume.domain.hr.Skill;

public class OSGiSkill extends Skill  {

	public OSGiSkill(Skill parent) {
		super.setId(SkillDaoHibernate.getCurrentId());
		super.setLabel("OSGi");
		super.setParent(parent);
	}
}
