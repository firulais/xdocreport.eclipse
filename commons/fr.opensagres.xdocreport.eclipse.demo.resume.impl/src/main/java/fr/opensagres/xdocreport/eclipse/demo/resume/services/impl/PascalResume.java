package fr.opensagres.xdocreport.eclipse.demo.resume.services.impl;

import fr.opensagres.xdocreport.document.images.ClassPathImageProvider;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public class PascalResume extends Resume {

	public PascalResume() {
		// Pascal
		NaturalPerson person = new NaturalPerson();
		person.setId(ResumeServiceImpl.currentId++);
		person.setFirstName("Pascal");
		person.setLastName("Leclercq");
		person.setEmail("pascal.leclercq@gmail.com");

		super.setId(ResumeServiceImpl.currentId++);
		super.setOwner(person);
		super.setPhoto(new ClassPathImageProvider(Resume.class,
				"PascalLeclercq.jpg"));
	}
}
