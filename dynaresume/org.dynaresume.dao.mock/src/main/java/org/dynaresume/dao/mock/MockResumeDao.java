package org.dynaresume.dao.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dynaresume.dao.ResumeDao;
import org.dynaresume.domain.core.Address;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Education;
import org.dynaresume.domain.hr.Experience;
import org.dynaresume.domain.hr.Hobby;
import org.dynaresume.domain.hr.Reference;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.domain.hr.SkillLanguage;
import org.dynaresume.domain.hr.SkillResume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository("resumeDao")
public class MockResumeDao extends AbstractDaoMock<Resume> implements ResumeDao {

	protected Resume clone(Resume resume) {
		NaturalPerson person = resume.getOwner();
		NaturalPerson newPerson = clone(person);

		Resume newResume = new Resume();
		newResume.setId(resume.getId());
		newResume.setTitle(resume.getTitle());
		newResume.setOwner(newPerson);
		newResume.setPicture(resume.getPicture());

		// Diplomas
		Set<Education> educations = resume.getEducations();
		if (educations != null) {
			Set<Education> newDiplomas = new HashSet<Education>();
			for (Education diploma : educations) {
				newDiplomas.add(clone(diploma));
			}
			newResume.setEducations(educations);
		}

		// Experiences
		Set<Experience> experiences = resume.getExperiences();
		if (experiences != null) {
			Set<Experience> newExperiences = new HashSet<Experience>();
			for (Experience experience : experiences) {
				newExperiences.add(clone(experience));
			}
			newResume.setExperiences(newExperiences);
		}

		// Skills
		Set<SkillResume> skills = resume.getSkills();
		if (skills != null) {
			Set<SkillResume> newSkills = new HashSet<SkillResume>();
			for (SkillResume skill : skills) {
				newSkills.add(clone(skill));
			}
			newResume.setSkills(newSkills);
		}

		// References
		Set<Reference> references = resume.getReferences();
		if (references != null) {
			Set<Reference> newReferences = new HashSet<Reference>();
			for (Reference reference : references) {
				newReferences.add(clone(reference));
			}
			newResume.setReferences(newReferences);
		}

		// Languages
		Set<SkillLanguage> languages = resume.getLanguages();
		if (languages != null) {
			Set<SkillLanguage> newLanguages = new HashSet<SkillLanguage>();
			for (SkillLanguage language : languages) {
				newLanguages.add(clone(language));
			}
			newResume.setLanguages(newLanguages);
		}

		// Hobbies
		Set<Hobby> hobbies = resume.getHobbies();
		if (hobbies != null) {
			Set<Hobby> newHobbies = new HashSet<Hobby>();
			for (Hobby hobby : hobbies) {
				newHobbies.add(clone(hobby));
			}
			newResume.setHobbies(newHobbies);
		}

		return newResume;
	}

	private NaturalPerson clone(NaturalPerson person) {
		NaturalPerson newPerson = new NaturalPerson();
		Long id = person.getId();
		if (id == null) {
			id = getId();
		}
		newPerson.setId(id);
		newPerson.setLastName(person.getLastName());
		newPerson.setFirstName(person.getFirstName());
		newPerson.setBirthDate(person.getBirthDate());
		newPerson.setEmail(person.getEmail());
		newPerson.setAddress(clone(person.getAddress()));
		return newPerson;
	}

	private Experience clone(Experience experience) {
		Experience newExperience = new Experience();
		Long id = experience.getId();
		if (id == null) {
			id = getId();
		}
		newExperience.setId(id);
		newExperience.setTitle(experience.getTitle());
		newExperience.setDetail(experience.getDetail());
		newExperience.setMission(experience.getMission());
		newExperience.setEndDate(experience.getEndDate());
		newExperience.setStartDate(experience.getStartDate());
		return newExperience;
	}

	private Education clone(Education diploma) {
		Education newDiploma = new Education();
		Long id = diploma.getId();
		if (id == null) {
			id = getId();
		}
		newDiploma.setId(id);
		newDiploma.setLabel(diploma.getLabel());
		newDiploma.setInstitute(diploma.getInstitute());
		return newDiploma;
	}

	private Hobby clone(Hobby hobby) {
		Hobby newHobby = new Hobby();
		Long id = hobby.getId();
		if (id == null) {
			id = getId();
		}
		newHobby.setId(id);
		newHobby.setLabel(hobby.getLabel());
		return newHobby;
	}

	private Reference clone(Reference reference) {
		Reference newReference = new Reference();
		Long id = reference.getId();
		if (id == null) {
			id = getId();
		}
		newReference.setId(id);
		newReference.setLabel(reference.getLabel());
		newReference.setStartDate(reference.getStartDate());
		newReference.setEndDate(reference.getEndDate());
		return newReference;
	}

	private SkillResume clone(SkillResume skill) {
		SkillResume newSkill = new SkillResume();
		Long id = skill.getId();
		if (id == null) {
			id = getId();
		}
		newSkill.setId(id);
		newSkill.setCategory(skill.getCategory());
		newSkill.setSkill(skill.getSkill());
		newSkill.setFreeSkill(skill.getFreeSkill());
		return newSkill;
	}

	private SkillLanguage clone(SkillLanguage language) {
		SkillLanguage newLanguage = new SkillLanguage();
		Long id = language.getId();
		if (id == null) {
			id = getId();
		}
		newLanguage.setId(id);
		newLanguage.setLanguage(language.getLanguage());
		return newLanguage;
	}

	private Address clone(Address address) {
		if (address == null) {
			return null;
		}
		Address newAddress = new Address();
		Long id = address.getId();
		if (id == null) {
			id = getId();
		}
		newAddress.setId(id);
		newAddress.setCity(address.getCity());
		newAddress.setCountry(address.getCountry());
		newAddress.setFax(address.getFax());
		newAddress.setStreet(address.getStreet());
		newAddress.setTelephone(address.getTelephone());
		newAddress.setZipCode(address.getZipCode());
		return newAddress;
	}

	public Page<Resume> findByOwnerFirstNameLikeAndOwnerLastNameLike(
			String firstNameCriteria, String lastNameCriteria, Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();
		Iterable<Resume> allResumes = findAll();
		// List<Resume> fullList = new ArrayList<Resume>(allResumes);

		firstNameCriteria = Utils.getCriteria(firstNameCriteria);
		lastNameCriteria = Utils.getCriteria(lastNameCriteria);

		List<Resume> filteredList = new ArrayList<Resume>();
		for (Resume resume : allResumes) {
			if (isPersonOK(resume, firstNameCriteria, lastNameCriteria)) {
				filteredList.add(resume);
			}
		}
		long totalSize = filteredList.size();
		List<Resume> paginatedList = new ArrayList<Resume>();
		for (int i = pageIndex; i < pageIndex + pageSize && i < totalSize; i++) {
			Resume resume = filteredList.get(i);
			paginatedList.add(resume);
		}

		return new PageImpl<Resume>(paginatedList, pageable, totalSize);

	}

	private boolean isPersonOK(Resume resume, String firstNameCriteria,
			String lastNameCriteria) {
		NaturalPerson person = resume.getOwner();
		if (person == null) {
			return false;
		}
		String firstName = person.getFirstName();
		if (firstNameCriteria != null) {
			if (!(firstName != null && firstName.toUpperCase().startsWith(
					firstNameCriteria.toUpperCase()))) {
				return false;
			}

		}
		String lastName = person.getLastName();
		if (lastNameCriteria != null) {
			if (!(lastName != null && lastName.toUpperCase().startsWith(
					lastNameCriteria.toUpperCase()))) {
				return false;
			}
		}
		return true;

	}

}
