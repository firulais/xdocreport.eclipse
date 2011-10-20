package fr.opensagres.xdocreport.eclipse.demo.resume.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fr.opensagres.xdocreport.document.images.ClassPathImageProvider;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public class ResumeServiceImpl implements ResumeService {

	private static final Map<Long, Resume> resumes;
	private static long currentId = 0;
	static {
		resumes = new HashMap<Long, Resume>();

		// Angelo
		NaturalPerson angelo = new NaturalPerson();
		angelo.setId(currentId++);
		angelo.setFirstName("Angelo");
		angelo.setLastName("ZERR");

		Resume angeloResume = new Resume();
		angeloResume.setId(currentId++);
		angeloResume.setOwner(angelo);
		angeloResume.setPhoto(new ClassPathImageProvider(Resume.class,
				"AngeloZERR.jpg"));

		resumes.put(angeloResume.getId(), angeloResume);

		// Pascal
		NaturalPerson pascal = new NaturalPerson();
		pascal.setId(currentId++);
		pascal.setFirstName("Pascal");
		pascal.setLastName("Leclercq");

		Resume pascalResume = new Resume();
		pascalResume.setId(currentId++);
		pascalResume.setOwner(pascal);
		pascalResume.setPhoto(new ClassPathImageProvider(Resume.class,
				"PascalLeclercq.jpg"));

		resumes.put(pascalResume.getId(), pascalResume);
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
		NaturalPerson person= resume.getOwner();
		NaturalPerson newPerson = clone(person);
		
		Resume newResume = new Resume();
		newResume.setId(resume.getId());
		newResume.setOwner(newPerson);
		newResume.setPhoto(resume.getPhoto());
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
}
