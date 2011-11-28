package org.dynaresume.eclipse.reporting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.dynaresume.domain.hr.DefaultSkillCategoryCode;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.domain.hr.SkillCategory;
import org.dynaresume.domain.hr.SkillResume;

import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core.XDocReportProcessor;
import fr.opensagres.xdocreport.template.IContext;

public class ResumeReportProcessor extends XDocReportProcessor {

	@Override
	public void populateContext(IContext context, Object model) {
		Resume resume = (Resume) model;
		context.put("resume", resume);
		context.put("person", resume.getOwner());
		context.put("experiences", resume.getExperiences());
		context.put("educations", resume.getEducations());
		context.put("hobbies", resume.getHobbies());
		context.put("references", resume.getReferences());
		context.put("languages", resume.getLanguages());
		if (resume.getPicture() != null) {
			context.put("photo",
					new ByteArrayImageProvider(resume.getPicture()));
		} else {
			try {
				context.put(
						"photo",
						new ByteArrayImageProvider(ResumeReportProcessor.class
								.getResourceAsStream("EmptyPhoto.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		Collection<String> functionalSkills = null;
		Collection<String> processSkills = null;
		Collection<String> technicalSkills = null;
		Collection<String> osTechnicalSkills = null;
		Collection<String> databaseTechnicalSkills = null;
		Collection<String> langagesTechnicalSkills = null;
		Collection<String> technologiesTechnicalSkills = null;
		Collection<String> softwaresTechnicalSkills = null;
		Collection<String> methodsAndToolsSkills = null;

		Set<SkillResume> skills = resume.getSkills();
		if (skills != null) {
			SkillCategory category = null;
			for (SkillResume skill : skills) {
				category = skill.getCategory();
				if (category != null) {
					DefaultSkillCategoryCode codeEnum = DefaultSkillCategoryCode
							.getCodeEnum(category.getCode());
					if (codeEnum != null) {
						switch (codeEnum) {
						case FunctionalSkills:
							if (functionalSkills == null) {
								functionalSkills = new ArrayList<String>();
							}
							functionalSkills.add(skill.getSkillLabel());
							break;
						case ProcessSkills:
							if (processSkills == null) {
								processSkills = new ArrayList<String>();
							}
							processSkills.add(skill.getSkillLabel());
							break;
						case TechnicalSkills:
							if (technicalSkills == null) {
								technicalSkills = new ArrayList<String>();
							}
							technicalSkills.add(skill.getSkillLabel());
							break;
						case OSTechnicalSkills:
							if (osTechnicalSkills == null) {
								osTechnicalSkills = new ArrayList<String>();
							}
							osTechnicalSkills.add(skill.getSkillLabel());
							break;
						case DatabaseTechnicalSkills:
							if (databaseTechnicalSkills == null) {
								databaseTechnicalSkills = new ArrayList<String>();
							}
							databaseTechnicalSkills.add(skill.getSkillLabel());
							break;
						case LangagesTechnicalSkills:
							if (langagesTechnicalSkills == null) {
								langagesTechnicalSkills = new ArrayList<String>();
							}
							langagesTechnicalSkills.add(skill.getSkillLabel());
							break;
						case TechnologiesTechnicalSkills:
							if (technologiesTechnicalSkills == null) {
								technologiesTechnicalSkills = new ArrayList<String>();
							}
							technologiesTechnicalSkills.add(skill
									.getSkillLabel());
							break;
						case SoftwaresTechnicalSkills:
							if (softwaresTechnicalSkills == null) {
								softwaresTechnicalSkills = new ArrayList<String>();
							}
							softwaresTechnicalSkills.add(skill.getSkillLabel());
							break;
						case MethodsAndToolsSkills:
							if (methodsAndToolsSkills == null) {
								methodsAndToolsSkills = new ArrayList<String>();
							}
							methodsAndToolsSkills.add(skill.getSkillLabel());
							break;

						}

					}
				}
			}
		}

		context.put(
				"functionalSkills",
				functionalSkills != null ? functionalSkills : Collections
						.emptyList());
		context.put("processSkills", processSkills != null ? processSkills
				: Collections.emptyList());
		context.put(
				"technicalSkills",
				technicalSkills != null ? technicalSkills : Collections
						.emptyList());
		context.put(
				"osTechnicalSkills",
				osTechnicalSkills != null ? osTechnicalSkills : Collections
						.emptyList());
		context.put("databaseTechnicalSkills",
				databaseTechnicalSkills != null ? databaseTechnicalSkills
						: Collections.emptyList());
		context.put("langagesTechnicalSkills",
				langagesTechnicalSkills != null ? langagesTechnicalSkills
						: Collections.emptyList());
		context.put(
				"technologiesTechnicalSkills",
				technologiesTechnicalSkills != null ? technologiesTechnicalSkills
						: Collections.emptyList());
		context.put("softwaresTechnicalSkills",
				softwaresTechnicalSkills != null ? softwaresTechnicalSkills
						: Collections.emptyList());
		context.put("methodsAndToolsSkills",
				methodsAndToolsSkills != null ? methodsAndToolsSkills
						: Collections.emptyList());
	}
}
