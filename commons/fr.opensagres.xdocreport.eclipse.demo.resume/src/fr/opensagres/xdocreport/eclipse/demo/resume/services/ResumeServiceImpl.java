package fr.opensagres.xdocreport.eclipse.demo.resume.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.opensagres.xdocreport.document.images.ClassPathImageProvider;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Experience;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public class ResumeServiceImpl implements ResumeService {

	private static final Map<Long, Resume> resumes;
	private static long currentId = 0;
	static {
		resumes = new HashMap<Long, Resume>();

		// Angelo
		Resume angeloResume = createAngeloResume();
		resumes.put(angeloResume.getId(), angeloResume);

		// Pascal
		Resume pascalResume = createPascalResume();
		resumes.put(pascalResume.getId(), pascalResume);
	}

	private static Resume createAngeloResume() {
		// Angelo
		NaturalPerson person = new NaturalPerson();
		person.setId(currentId++);
		person.setFirstName("Angelo");
		person.setLastName("ZERR");

		Resume resume = new Resume();
		resume.setId(currentId++);
		resume.setOwner(person);
		resume.setPhoto(new ClassPathImageProvider(Resume.class,
				"AngeloZERR.jpg"));

		Set<Experience> experiences = new HashSet<Experience>();
		resume.setExperiences(experiences);
		
		Experience experience = null;

		// Experience 1
		experience = new Experience();
		experience.setId(currentId++);
		experience.setProjectName("Projet SIDoc");
		experience.setMission("Conception / Développement");
		experience
				.setContext("Mise en place de l'application WEB de diffusion (qui sera accéssible dans les accueils des CAF) qui permet de publier les documents XML produits par l'application WEB de production.");
		experiences.add(experience);

		// Experience 2
		experience = new Experience();
		experience.setId(currentId++);
		experience.setProjectName("ERP AgroV3");
		experience.setMission("Conception / Développement");
		experience
				.setContext("Conception et développement de fonctionnalités dans le  module VENTES/ACHATS et COMPTABILITE de l'ERP agrolimentaire AgroV3 de INFOLOGIC. Cet ERP est basé sur les technologies d'Eclipse SWT et JFace.");
		experiences.add(experience);

		return resume;
	}

	private static Resume createPascalResume() {
		// Pascal
		NaturalPerson person = new NaturalPerson();
		person.setId(currentId++);
		person.setFirstName("Pascal");
		person.setLastName("Leclercq");

		Resume resume = new Resume();
		resume.setId(currentId++);
		resume.setOwner(person);
		resume.setPhoto(new ClassPathImageProvider(Resume.class,
				"PascalLeclercq.jpg"));

		Set<Experience> experiences = new HashSet<Experience>();
		resume.setExperiences(experiences);

		return resume;
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

		// Experiences
		Set<Experience> experiences = resume.getExperiences();
		if (experiences != null) {
			Set<Experience> newExperiences = new HashSet<Experience>();
			for (Experience experience : experiences) {
				newExperiences.add(clone(experience));
			}
			newResume.setExperiences(newExperiences);
		}

		return newResume;
	}

	private NaturalPerson clone(NaturalPerson person) {
		NaturalPerson newPerson = new NaturalPerson();
		newPerson.setId(person.getId());
		newPerson.setLastName(person.getLastName());
		newPerson.setFirstName(person.getFirstName());
		newPerson.setBirthDate(person.getBirthDate());
		return newPerson;
	}

	private Experience clone(Experience experience) {
		Experience newExperience = new Experience();
		newExperience.setId(experience.getId());
		newExperience.setProjectName(experience.getProjectName());
		newExperience.setContext(experience.getContext());
		newExperience.setMission(experience.getMission());
		newExperience.setEndDate(experience.getEndDate());
		newExperience.setStartDate(experience.getStartDate());
		return newExperience;
	}
}
