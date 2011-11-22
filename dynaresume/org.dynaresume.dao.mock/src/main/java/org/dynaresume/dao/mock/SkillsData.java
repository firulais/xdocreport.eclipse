package org.dynaresume.dao.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.hr.SkillCategory;

public class SkillsData {

	static long currentId = 0;
	static final Map<Long, SkillCategory> categories;
	static final Map<Long, Skill> skills;

	static {
		skills = new HashMap<Long, Skill>();
		categories = new HashMap<Long, SkillCategory>();

		SkillCategory functionalSkills = addCategory("Functional skills", null);
		SkillCategory processSkills = addCategory("Process skills", null);

		SkillCategory technicalSkills = addCategory("Technical skills", null);
		addCategory("OS", technicalSkills);
		addCategory("Database", technicalSkills);
		addCategory("Langages", technicalSkills);
		addCategory("Technologies", technicalSkills);
		addCategory("Sofwares", technicalSkills);

		SkillCategory toolsSkills = addCategory("Methods and associated tools",
				null);

		Skill javaSkill = addSkill("Java", null);
		addSkill("Spring", javaSkill);
		addSkill("OSGi", javaSkill);

		Skill eclipseSkill = addSkill("Eclipse", javaSkill);
		addSkill("Eclipse RCP", eclipseSkill);
		addSkill("Eclipse RAP", eclipseSkill);
		addSkill("EMF", eclipseSkill);
		addSkill("GEF", eclipseSkill);
		addSkill("GMF", eclipseSkill);

		Skill jeeSkill = addSkill("JEE", javaSkill);
		addSkill("JSP", jeeSkill);
		Skill webservicesSkill = addSkill("WebServices (Java)", jeeSkill);
		addSkill("CXF", webservicesSkill);
		addSkill("Axis 1", webservicesSkill);
		addSkill("Axis 2", webservicesSkill);
		Skill fwkJEESkill = addSkill("Framework (JEE)", jeeSkill);
		addSkill("Play!", fwkJEESkill);
		addSkill("JBoss Seam", fwkJEESkill);
		addSkill("Struts 1", fwkJEESkill);
		addSkill("Struts 2", fwkJEESkill);
		addSkill("Wicket", fwkJEESkill);
		addSkill("GWT", fwkJEESkill);

		Skill javaScriptSkill = addSkill("JavaScript", null);
		Skill fwkJavaScriptSkill = addSkill("Framework (Javascript)",
				javaScriptSkill);
		addSkill("Dojo", fwkJavaScriptSkill);
		addSkill("Qooxdoo", fwkJavaScriptSkill);
		addSkill("JQuery", fwkJavaScriptSkill);

		Skill dotNetSkill = addSkill(".Net", null);
		addSkill("C#", dotNetSkill);
		addSkill("VB.Net", dotNetSkill);
		addSkill("XAML", dotNetSkill);
	}

	private static Skill addSkill(String label, Skill parent) {
		Skill skill = new Skill();
		skill.setId(getId());
		skill.setLabel(label);
		skill.setParent(parent);
		addSkill(skill);
		return skill;
	}

	private static SkillCategory addCategory(String label, SkillCategory parent) {
		SkillCategory skill = new SkillCategory();
		skill.setChildren(new ArrayList<SkillCategory>());
		skill.setId(getId());
		skill.setLabel(label);
		if (parent != null) {
			skill.setParent(parent);
			parent.getChildren().add(skill);
		}
		addCategory(skill);
		return skill;
	}

	private static void addSkill(Skill skill) {
		skills.put(skill.getId(), skill);
	}

	private static void addCategory(SkillCategory category) {
		categories.put(category.getId(), category);
	}

	public synchronized static Long getId() {
		return currentId++;
	}
}