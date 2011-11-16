package org.dynaresume.dao.mock.skills;

import org.dynaresume.dao.mock.SkillDaoMock;
import org.dynaresume.domain.hr.Skill;

public class OSGiSkill extends Skill  {

	public OSGiSkill(Skill parent) {
		super.setId(SkillDaoMock.getCurrentId());
		super.setLabel("OSGi");
		super.setParent(parent);
	}
}
