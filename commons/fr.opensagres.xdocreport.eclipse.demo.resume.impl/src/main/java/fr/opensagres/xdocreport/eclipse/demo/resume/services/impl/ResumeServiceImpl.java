package fr.opensagres.xdocreport.eclipse.demo.resume.services.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.Address;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Diploma;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Experience;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Hobby;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;
import fr.opensagres.xdocreport.eclipse.demo.resume.services.ResumeService;

public class ResumeServiceImpl implements ResumeService {

	private static final Map<Long, Resume> resumes;
	static long currentId = 0;
	static {
		resumes = new HashMap<Long, Resume>();

		// Angelo
		Resume angeloResume = new AngeloResume();
		resumes.put(angeloResume.getId(), angeloResume);

		// Pascal
		Resume pascalResume = new PascalResume();
		resumes.put(pascalResume.getId(), pascalResume);

		// Amine
		Resume amineResume = new AmineResume();
		resumes.put(amineResume.getId(), amineResume);
	}

	public Collection<Resume> findAll() {
		return resumes.values();
	}

	public Resume findById(long id) {
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
		newResume.setOwner(newPerson);
		newResume.setPhoto(resume.getPhoto());

		// Diplomas
		Set<Diploma> diplomas = resume.getDiplomas();
		if (diplomas != null) {
			Set<Diploma> newDiplomas = new HashSet<Diploma>();
			for (Diploma diploma : diplomas) {
				newDiplomas.add(clone(diploma));
			}
			newResume.setDiplomas(newDiplomas);
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

	private Diploma clone(Diploma diploma) {
		Diploma newDiploma = new Diploma();
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
