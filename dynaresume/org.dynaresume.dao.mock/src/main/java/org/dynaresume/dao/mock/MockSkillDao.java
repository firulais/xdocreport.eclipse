package org.dynaresume.dao.mock;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.dao.SkillDao;
import org.dynaresume.dao.mock.internal.PageListHelper;
import org.dynaresume.domain.hr.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Page<Skill> findByNameLike(String name, Pageable pageable) {
		name= Utils.getCriteria(name);
		Iterable<Skill> allSkills = findAll();
		List<Skill> filteredList = new ArrayList<Skill>();
		for (Skill skill : allSkills) {
			if (isSkillOK(skill, name)) {
				filteredList.add(skill);
			}
		}
		return PageListHelper.createPage(filteredList, pageable);
	}

	private boolean isSkillOK(Skill skill, String label) {
		if (label == null) {
			return true;
		}
		if (skill.getName() == null) {
			return false;
		}
		return skill.getName().toUpperCase().startsWith(label.toUpperCase());
	}

}
