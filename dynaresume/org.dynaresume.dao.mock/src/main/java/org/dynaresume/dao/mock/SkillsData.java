package org.dynaresume.dao.mock;

import java.util.HashMap;
import java.util.Map;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.hr.SkillCategory;

public class SkillsData {

	static long currentId = 0;

	public static final SkillCategory functionalSkills;
	public static final SkillCategory processSkills;
	public static final SkillCategory technicalSkills;
	public static final SkillCategory osTechnicalSkills;
	public static final SkillCategory databaseTechnicalSkills;
	public static final SkillCategory langagesTechnicalSkills;
	public static final SkillCategory technologiesTechnicalSkills;
	public static final SkillCategory softwaresTechnicalSkills;
	public static final SkillCategory methodsAndToolsSkills;

	static final Map<Long, SkillCategory> categories;
	static final Map<Long, Skill> skills;
	private static Map<String, Skill> skillsByLabel;

	static {
		skills = new HashMap<Long, Skill>();
		skillsByLabel = new HashMap<String, Skill>();
		categories = new HashMap<Long, SkillCategory>();

		functionalSkills = addCategory("Functional skills", null);
		processSkills = addCategory("Process skills", null);

		technicalSkills = addCategory("Technical skills", null);
		osTechnicalSkills = addCategory("OS", technicalSkills);
		databaseTechnicalSkills = addCategory("Database", technicalSkills);
		langagesTechnicalSkills = addCategory("Langages", technicalSkills);
		technologiesTechnicalSkills = addCategory("Technologies",
				technicalSkills);
		softwaresTechnicalSkills = addCategory("Softwares", technicalSkills);

		methodsAndToolsSkills = addCategory("Methods and associated tools",
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
		Skill jeeServerSkill = addSkill("JEE WebServer", jeeSkill);
		addSkill("Tomcat", jeeServerSkill);
		addSkill("Jetty", jeeServerSkill);
		addSkill("WebSphere", jeeServerSkill);
		addSkill("WebLogic", jeeServerSkill);
		addSkill("JBoss", jeeServerSkill);
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

		Skill xmlSkill = addSkill("XML", null);
		addSkill("XSD", xmlSkill);
		addSkill("XSL", xmlSkill);
		addSkill("XQuery", xmlSkill);
		addSkill("XForms", xmlSkill);

		Skill osSkill = addSkill("OS", null);
		addSkill("Windows", osSkill);
		addSkill("Linux", osSkill);
		addSkill("Mac OS", osSkill);

		Skill databaseSkill = addSkill("Database", null);
		Skill oracleDatabaseSkill = addSkill("Oracle", databaseSkill);
		addSkill("Oracle 8i", oracleDatabaseSkill);
		addSkill("Oracle 9i", oracleDatabaseSkill);
		addSkill("Oracle 10g", oracleDatabaseSkill);
		addSkill("SQL Server", databaseSkill);
		addSkill("MySQL", databaseSkill);
		addSkill("KTBS", databaseSkill);
		addSkill("Postgres", databaseSkill);
		addSkill("Derby", databaseSkill);
		addSkill("H2", databaseSkill);

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
		// skill.setChildren(new ArrayList<SkillCategory>());
		skill.setId(getId());
		skill.setLabel(label);
		if (parent != null) {
			skill.setParent(parent);
			// parent.getChildren().add(skill);
		}
		addCategory(skill);
		return skill;
	}

	private static void addSkill(Skill skill) {
		skills.put(skill.getId(), skill);
		skillsByLabel.put(skill.getLabel(), skill);
	}

	private static void addCategory(SkillCategory category) {
		categories.put(category.getId(), category);
	}

	public synchronized static Long getId() {
		return currentId++;
	}

	public static Skill getSkillByLabel(String label) {
		return skillsByLabel.get(label);
	}
}