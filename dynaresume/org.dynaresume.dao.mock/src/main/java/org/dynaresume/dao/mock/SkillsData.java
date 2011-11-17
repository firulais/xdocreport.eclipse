package org.dynaresume.dao.mock;

import java.util.HashMap;
import java.util.Map;

import org.dynaresume.domain.hr.Skill;

public class SkillsData {

	static long currentId = 0;
	static final Map<Long, Skill> skills;
	
	public static Skill javaSkill = new Skill();
	public static Skill springSkill = new Skill();
	public static Skill osgiSkill = new Skill();
	public static Skill eclipseSkill = new Skill();
	public static Skill rcpSkill = new Skill();
	public static Skill rapSkill = new Skill();

	static {
		skills = new HashMap<Long, Skill>();
		
		javaSkill.setId(getId());
		javaSkill.setLabel("Java");
		addSkill(javaSkill);
		
		springSkill.setId(getId());
		springSkill.setLabel("Spring");
		springSkill.setParent(javaSkill);
		addSkill(springSkill);
		
		osgiSkill.setId(getId());
		osgiSkill.setLabel("OSGi");
		osgiSkill.setParent(javaSkill);
		addSkill(osgiSkill);
		
		eclipseSkill.setId(getId());
		eclipseSkill.setLabel("Eclipse");
		eclipseSkill.setParent(javaSkill);
		addSkill(eclipseSkill);
		
		rcpSkill.setId(getId());
		rcpSkill.setLabel("Eclipse RCP");
		rcpSkill.setParent(eclipseSkill);
		addSkill(rcpSkill);
		
		rapSkill.setId(getId());
		rapSkill.setLabel("Eclipse RAP");
		rapSkill.setParent(eclipseSkill);
		addSkill(rapSkill);
	}

	private static void addSkill(Skill skill) {
		skills.put(skill.getId(), skill);
	}

	public synchronized static Long getId() {
		return currentId++;
	}
}
