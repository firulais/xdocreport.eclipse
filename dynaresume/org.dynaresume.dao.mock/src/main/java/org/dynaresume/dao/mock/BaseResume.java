package org.dynaresume.dao.mock;

import java.util.HashSet;
import java.util.Set;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.hr.SkillCategory;
import org.dynaresume.domain.hr.SkillResume;

public class BaseResume extends Resume {

	protected void addSkill(SkillCategory category, String skillLabel) {
		Skill skill = SkillsData.getSkillByLabel(skillLabel);
		SkillResume skillResume = new SkillResume();
		skillResume.setCategory(category);
		if (skill != null) {
			skillResume.setSkill(skill);
		} else {
			skillResume.setFreeSkill(skillLabel);
		}
		Set<SkillResume> skills = super.getSkills();
		if (skills == null) {
			skills = new HashSet<SkillResume>();
			super.setSkills(skills);
		}
		skills.add(skillResume);
	}

	protected void addSkillWithSplit(SkillCategory category, String skillLabel) {
		String[] labels = skillLabel.split(",");
		for (int i = 0; i < labels.length; i++) {
			addSkill(category, labels[i].trim());
		}
	}
}
