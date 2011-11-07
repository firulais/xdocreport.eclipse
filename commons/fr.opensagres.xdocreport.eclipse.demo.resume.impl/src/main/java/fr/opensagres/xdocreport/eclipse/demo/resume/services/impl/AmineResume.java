package fr.opensagres.xdocreport.eclipse.demo.resume.services.impl;

import java.io.IOException;

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
		try {
			super.setPictureAsStream(Resume.class
					.getResourceAsStream("AmineBousta.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
