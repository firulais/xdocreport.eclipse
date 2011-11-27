package org.dynaresume.dao.mock;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.dynaresume.dao.ResumeDao;
import org.dynaresume.dao.mock.resume.AmineBoustaResume;
import org.dynaresume.dao.mock.resume.AngeloZerrResume;
import org.dynaresume.dao.mock.resume.DinoCosmasResume;
import org.dynaresume.dao.mock.resume.LarsVogelResume;
import org.dynaresume.dao.mock.resume.NicolasRaymondResume;
import org.dynaresume.dao.mock.resume.PascalLeclercqResume;
import org.dynaresume.dao.mock.resume.RalfSternbergResume;
import org.dynaresume.domain.core.Address;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Education;
import org.dynaresume.domain.hr.Experience;
import org.dynaresume.domain.hr.Hobby;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.domain.hr.SkillResume;
import org.springframework.stereotype.Repository;

@Repository("resumeDao")
public class MockResumeDao extends AbstractDaoMock<Resume> implements ResumeDao {

	private static final Map<Long, Resume> resumes;
	static long currentId = 0;
	static {
		resumes = new LinkedHashMap<Long, Resume>();
		addResume(new AngeloZerrResume());
		addResume(new PascalLeclercqResume());
		addResume(new AmineBoustaResume());
		addResume(new RalfSternbergResume());
		addResume(new LarsVogelResume());
		addResume(new DinoCosmasResume());
		addResume(new NicolasRaymondResume());
	}

	private static void addResume(Resume resume) {
		resumes.put(resume.getId(), resume);
	}

	public Iterable<Resume> findAll() {
		return resumes.values();
	}

	public Resume findOne(Long id) {
		Resume Resume = resumes.get(id);
		if (Resume != null) {
			return clone(Resume);
		}
		return resumes.get(id);
	}

	public Resume save(Resume resume) {
		if (resume.getId() == null) {
			resume.setId(currentId++);
		}
		resumes.put(resume.getId(), resume);
		return clone(resume);
	}

	private Resume clone(Resume resume) {
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

		// Hobbies
		Set<SkillResume> skills = resume.getSkills();
		if (skills != null) {
			Set<SkillResume> newSkills = new HashSet<SkillResume>();
			for (SkillResume skill : skills) {
				newSkills.add(clone(skill));
			}
			newResume.setSkills(newSkills);
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
		newPerson.setId(person.getId());
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
			id = currentId++;
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
			id = currentId++;
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
			id = currentId++;
		}
		newHobby.setId(id);
		newHobby.setLabel(hobby.getLabel());
		return newHobby;
	}
	
	private SkillResume clone(SkillResume skill) {
		SkillResume newSkill = new SkillResume();
		newSkill.setCategory(skill.getCategory());
		newSkill.setSkill(skill.getSkill());
		newSkill.setFreeSkill(skill.getFreeSkill());
		return newSkill;
	}

	private Address clone(Address address) {
		if (address == null) {
			return null;
		}
		Address newAddress = new Address();
		Long id = address.getId();
		if (id == null) {
			id = currentId++;
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

}
