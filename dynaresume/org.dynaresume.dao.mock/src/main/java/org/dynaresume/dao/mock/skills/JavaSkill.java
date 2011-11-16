package org.dynaresume.dao.mock.skills;

import org.dynaresume.dao.mock.SkillDaoMock;
import org.dynaresume.domain.hr.Skill;

public class JavaSkill extends Skill  {

	public JavaSkill() {
		super.setId(SkillDaoMock.getCurrentId());
		super.setLabel("Java");
	}
}
