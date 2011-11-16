package org.dynaresume.dao.mock.skills;

import org.dynaresume.dao.mock.SkillDaoMock;
import org.dynaresume.domain.hr.Skill;

public class SpringSkill extends Skill  {

	public SpringSkill(Skill parent) {
		super.setId(SkillDaoMock.getCurrentId());
		super.setLabel("Spring");
		super.setParent(parent);
	}
}
