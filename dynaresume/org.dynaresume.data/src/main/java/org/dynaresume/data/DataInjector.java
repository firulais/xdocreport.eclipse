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
		SkillsInjector skillsInjector = new SkillsInjector();
		skillsInjector.setSkillCategoryService(skillCategoryService);
		skillsInjector.setSkillService(skillService);
		skillsInjector.inject();

		// Group data injections
		GroupsInjector groupsInjector = new GroupsInjector();
		groupsInjector.setGroupService(groupService);
		groupsInjector.inject();

		// Languages data injection
		LanguagesInjector languagesInjector = new LanguagesInjector();
		languagesInjector.setLanguageService(languageService);
		languagesInjector.inject();

		// Resumes data injection
		ResumesInjector resumesInjector = new ResumesInjector();
		resumesInjector.setResumeService(resumeService);
		resumesInjector.setSkillsInjector(skillsInjector);
		resumesInjector.inject();

	}

	public void interrupt() {

	}
}
