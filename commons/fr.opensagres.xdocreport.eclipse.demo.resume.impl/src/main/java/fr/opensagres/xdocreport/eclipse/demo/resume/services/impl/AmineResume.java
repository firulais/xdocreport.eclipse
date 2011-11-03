package fr.opensagres.xdocreport.eclipse.demo.resume.services.impl;

import fr.opensagres.xdocreport.document.images.ClassPathImageProvider;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public class AmineResume extends Resume {

	public AmineResume() {
		// Pascal
		NaturalPerson person = new NaturalPerson();
		person.setId(ResumeServiceImpl.currentId++);
		person.setFirstName("Amine");
		person.setLastName("Bousta");

		super.setId(ResumeServiceImpl.currentId++);
		super.setOwner(person);
		super.setPhoto(new ClassPathImageProvider(Resume.class,
				"AmineBousta.jpg"));
	}
}
