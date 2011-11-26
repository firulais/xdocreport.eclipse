package org.dynaresume.dao.mock.resume;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.dynaresume.dao.mock.SkillsData;
import org.dynaresume.domain.hr.Education;
import org.dynaresume.domain.hr.Experience;
import org.dynaresume.domain.hr.Hobby;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.hr.SkillCategory;
import org.dynaresume.domain.hr.SkillResume;

import fr.opensagres.xdocreport.commons.utils.DateUtils;

public class BaseResume extends Resume {

	static long currentId = 0;

	protected void addEducation(String label, String institute,
			String startDate, String endDate) {
		Set<Education> educations = getEducations();
		if (educations == null) {
			educations = new HashSet<Education>();
			super.setEducations(educations);
		}
		Education education = new Education();
		education.setId(getCurrentId());
		education.setLabel(label);
		education.setInstitute(institute);
		if (startDate != null) {
			try {
				education.setStartDate(DateUtils.toDate(startDate,
						DateUtils.FRENCH_PATTERN));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if (endDate != null) {
			try {
				education.setEndDate(DateUtils.toDate(endDate,
						DateUtils.FRENCH_PATTERN));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		educations.add(education);

	}

	protected void addExperience(String title, String mission, String detail,
			String startDate, String endDate) {
		Set<Experience> experiences = getExperiences();
		if (experiences == null) {
			experiences = new HashSet<Experience>();
			super.setExperiences(experiences);
		}

		Experience experience = new Experience();
		experience.setId(getCurrentId());
		experience.setTitle(title);
		experience.setMission(mission);
		experience.setDetail(detail);

		if (startDate != null) {
			try {
				experience.setStartDate(DateUtils.toDate(startDate,
						DateUtils.FRENCH_PATTERN));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if (endDate != null) {
			try {
				experience.setEndDate(DateUtils.toDate(endDate,
						DateUtils.FRENCH_PATTERN));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		experiences.add(experience);

	}

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

	protected void addHobby(String label) {
		Set<Hobby> hobbies = getHobbies();
		if (hobbies == null) {
			hobbies = new HashSet<Hobby>();
			super.setHobbies(hobbies);
		}
		Hobby hobby = new Hobby();
		hobby.setId(getCurrentId());
		hobby.setLabel(label);
		hobbies.add(hobby);
	}

	protected static long getCurrentId() {
		return currentId++;
	}
}
