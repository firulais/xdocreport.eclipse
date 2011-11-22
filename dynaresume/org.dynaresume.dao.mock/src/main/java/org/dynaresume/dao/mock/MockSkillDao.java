package org.dynaresume.dao.mock;

import java.util.Map;

import org.dynaresume.dao.SkillDao;
import org.dynaresume.domain.hr.Skill;

@org.springframework.stereotype.Repository("skillDao")
public class MockSkillDao extends AbstractDaoMock<Skill> implements SkillDao {

	private final Map<Long, Skill> skills = SkillsData.skills;

	public Iterable<Skill> findAll() {
		return skills.values();
	}

	public Skill findOne(Long id) {
		Skill skill = skills.get(id);
		if (skill != null) {
			return clone(skill);
		}
		return skills.get(id);
	}

	public Skill save(Skill skill) {
		if (skill.getId() == null) {
			skill.setId(SkillsData.getId());
		}
		skills.put(skill.getId(), skill);
		return clone(skill);
	}

	private Skill clone(Skill skill) {
		Skill newSkill = new Skill();
		newSkill.setId(skill.getId());
		newSkill.setLabel(skill.getLabel());
		newSkill.setParent(skill.getParent());
		return newSkill;
	}

}
