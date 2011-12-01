package org.dynaresume.data;

import org.dynaresume.services.GroupService;
import org.dynaresume.services.LanguageService;
import org.dynaresume.services.ResumeService;
import org.dynaresume.services.SkillCategoryService;
import org.dynaresume.services.SkillService;

public class DataInjector implements Runnable {

	private ResumeService resumeService;
	private SkillService skillService;
	private SkillCategoryService skillCategoryService;
	private GroupService groupService;
	private LanguageService languageService;
	private SkillsInjector skillsInjector;
	private GroupsInjector groupsInjector;
	private LanguagesInjector languagesInjector;
	private ResumesInjector resumesInjector;

	public void setResumeService(ResumeService resumeService) {
		this.resumeService = resumeService;
	}

	public void setSkillCategoryService(
			SkillCategoryService skillCategoryService) {
		this.skillCategoryService = skillCategoryService;
	}

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	public void run() {
		// Skills + Skills categories data injections
		skillsInjector = new SkillsInjector(this);
		skillsInjector.inject();

		// Group data injections
		groupsInjector = new GroupsInjector(this);
		groupsInjector.inject();

		// Languages data injection
		languagesInjector = new LanguagesInjector(this);
		languagesInjector.inject();

		// Resumes data injection
		resumesInjector = new ResumesInjector(this);
		resumesInjector.inject();

	}

	public void interrupt() {

	}

	public SkillsInjector getSkillsInjector() {
		return skillsInjector;
	}

	public GroupsInjector getGroupsInjector() {
		return groupsInjector;
	}

	public ResumesInjector getResumesInjector() {
		return resumesInjector;
	}

	public LanguagesInjector getLanguagesInjector() {
		return languagesInjector;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public LanguageService getLanguageService() {
		return languageService;
	}

	public ResumeService getResumeService() {
		return resumeService;
	}

	public SkillCategoryService getSkillCategoryService() {
		return skillCategoryService;
	}

	public SkillService getSkillService() {
		return skillService;
	}
}
