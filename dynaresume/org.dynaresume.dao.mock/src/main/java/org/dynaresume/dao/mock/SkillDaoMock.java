package org.dynaresume.dao.mock;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.dynaresume.dao.SkillDao;
import org.dynaresume.dao.mock.skills.JavaSkill;
import org.dynaresume.dao.mock.skills.OSGiSkill;
import org.dynaresume.dao.mock.skills.SpringSkill;
import org.dynaresume.domain.hr.Skill;

public class SkillDaoMock implements SkillDao {

	private static final Map<Long, Skill> skills;
	static long currentId = 0;
	static {
		skills = new HashMap<Long, Skill>();

		JavaSkill javaSkill = new JavaSkill();
		addSkill(javaSkill);
		SpringSkill springSkill = new SpringSkill(javaSkill);
		addSkill(springSkill);
		OSGiSkill osgiSkill = new OSGiSkill(javaSkill);
		addSkill(osgiSkill);

	}

	private static void addSkill(Skill skill) {
		skills.put(skill.getId(), skill);
	}

	public Collection<Skill> findAll() {
		return skills.values();
	}

	public Skill findById(long id) {
		Skill skill = skills.get(id);
		if (skill != null) {
			return clone(skill);
		}
		return skills.get(id);
	}

	public Skill save(Skill resume) {
		if (resume.getId() == null) {
			resume.setId(getCurrentId());
		}
		skills.put(resume.getId(), resume);
		return clone(resume);
	}

	private Skill clone(Skill resume) {
		Skill newSkill = new Skill();
		newSkill.setId(resume.getId());
		newSkill.setLabel(resume.getLabel());
		newSkill.setParent(resume.getParent());
		return newSkill;
	}

	public static long getCurrentId() {
		return currentId++;
	}
}
