package org.dynaresume.data.resume;

import java.io.InputStream;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.dynaresume.data.SkillsInjector;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;
import org.dynaresume.domain.hr.Education;
import org.dynaresume.domain.hr.Experience;
import org.dynaresume.domain.hr.Hobby;
import org.dynaresume.domain.hr.Language;
import org.dynaresume.domain.hr.Reference;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.hr.SkillCategory;
import org.dynaresume.domain.hr.SkillLanguage;
import org.dynaresume.domain.hr.SkillResume;

import fr.opensagres.xdocreport.commons.utils.DateUtils;

public abstract class AbstractResumeFactory extends Resume {

	private final Resume resume;
	private final SkillsInjector skillsInjector;

	public AbstractResumeFactory(SkillsInjector skillsInjector) {
		this.resume = new Resume();
		this.skillsInjector = skillsInjector;
	}
	
	public boolean equals(Object arg0) {
		return resume.equals(arg0);
	}

	public Long getId() {
		return resume.getId();
	}

	public String getTitle() {
		return resume.getTitle();
	}

	public byte[] getPicture() {
		return resume.getPicture();
	}

	public InputStream getPictureAsStream() {
		return resume.getPictureAsStream();
	}

	public NaturalPerson getOwner() {
		return resume.getOwner();
	}

	public Set<Experience> getExperiences() {
		return resume.getExperiences();
	}

	public Set<Education> getEducations() {
		return resume.getEducations();
	}

	public Set<SkillResume> getSkills() {
		return resume.getSkills();
	}

	public Set<Hobby> getHobbies() {
		return resume.getHobbies();
	}

	public Set<SkillLanguage> getLanguages() {
		return resume.getLanguages();
	}

	public Set<Reference> getReferences() {
		return resume.getReferences();
	}

	public int hashCode() {
		return resume.hashCode();
	}

	public void setId(long id) {
		resume.setId(id);
	}

	public void setTitle(String title) {
		resume.setTitle(title);
	}

	public void setPicture(byte[] picture) {
		resume.setPicture(picture);
	}

	public void setOwner(NaturalPerson owner) {
		resume.setOwner(owner);
	}

	public void setExperiences(Set<Experience> experiences) {
		resume.setExperiences(experiences);
	}

	public void setEducations(Set<Education> educations) {
		resume.setEducations(educations);
	}

	public void setSkills(Set<SkillResume> skills) {
		resume.setSkills(skills);
	}

	public void setHobbies(Set<Hobby> hobbies) {
		resume.setHobbies(hobbies);
	}

	public void setLanguages(Set<SkillLanguage> languages) {
		resume.setLanguages(languages);
	}

	public void setReferences(Set<Reference> references) {
		resume.setReferences(references);
	}

	public String toString() {
		return resume.toString();
	}

	

	public Resume getResume() {
		return resume;
	}

	protected void addEducation(String label, String institute,
			String startDate, String endDate) {
		Set<Education> educations = getEducations();
		if (educations == null) {
			educations = new HashSet<Education>();
			setEducations(educations);
		}
		Education education = new Education();
		// education.setId(getCurrentId());
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
			setExperiences(experiences);
		}

		Experience experience = new Experience();
		// experience.setId(getCurrentId());
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
		Skill skill = skillsInjector.getSkillByLabel(skillLabel);
		SkillResume skillResume = new SkillResume();
		skillResume.setCategory(category);
		if (skill != null) {
			skillResume.setSkill(skill);
		} else {
			skillResume.setFreeSkill(skillLabel);
		}
		Set<SkillResume> skills = getSkills();
		if (skills == null) {
			skills = new HashSet<SkillResume>();
			setSkills(skills);
		}
		skills.add(skillResume);
	}

	protected void addSkillWithSplit(SkillCategory category, String skillLabel) {
		String[] labels = skillLabel.split(",");
		for (int i = 0; i < labels.length; i++) {
			addSkill(category, labels[i].trim());
		}
	}

	protected void addReference(String label, String startDate, String endDate) {
		Set<Reference> references = getReferences();
		if (references == null) {
			references = new HashSet<Reference>();
			setReferences(references);
		}
		Reference reference = new Reference();
		// reference.setId(getCurrentId());
		reference.setLabel(label);
		if (startDate != null) {
			try {
				reference.setStartDate(DateUtils.toDate(startDate,
						DateUtils.FRENCH_PATTERN));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if (endDate != null) {
			try {
				reference.setEndDate(DateUtils.toDate(endDate,
						DateUtils.FRENCH_PATTERN));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		references.add(reference);
	}

	protected void addLanguage(DefaultLanguageCode code) {
		Language language = null;//LanguagesData.getLanguageByCode(code.getCode());
		if (language != null) {
			addLanguage(language);
		}
	}

	protected void addLanguage(Language language) {
		Set<SkillLanguage> languages = getLanguages();
		if (languages == null) {
			languages = new HashSet<SkillLanguage>();
			setLanguages(languages);
		}
		SkillLanguage skillLanguage = new SkillLanguage();
		// language.setId(getCurrentId());
		skillLanguage.setLanguage(language);
		languages.add(skillLanguage);
	}

	protected void addHobby(String label) {
		Set<Hobby> hobbies = getHobbies();
		if (hobbies == null) {
			hobbies = new HashSet<Hobby>();
			setHobbies(hobbies);
		}
		Hobby hobby = new Hobby();
		// hobby.setId(getCurrentId());
		hobby.setLabel(label);
		hobbies.add(hobby);
	}

	public SkillsInjector getSkillsInjector() {
		return skillsInjector;
	}
}
