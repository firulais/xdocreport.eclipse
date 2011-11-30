package org.dynaresume.dao.mock;

import org.dynaresume.dao.SkillDao;
import org.dynaresume.domain.hr.Skill;
import org.springframework.stereotype.Repository;

@Repository("skillDao")
public class MockSkillDao extends AbstractDaoMock<Skill> implements SkillDao {

	protected Skill clone(Skill skill) {
		Skill newSkill = new Skill();
		newSkill.setId(skill.getId());
		newSkill.setName(skill.getName());
		newSkill.setDescription(skill.getDescription());
		newSkill.setURL(skill.getURL());
		newSkill.setParent(skill.getParent());
		return newSkill;
	}

}
