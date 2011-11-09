package fr.opensagres.xdocreport.eclipse.demo.resume.services.dao.hibernate;

import java.io.IOException;

import fr.opensagres.xdocreport.core.io.IOUtils;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public class AmineResume extends Resume {

	public AmineResume() {
		// Pascal
		NaturalPerson person = new NaturalPerson();
		person.setId(ResumeDaoHibernate.currentId++);
		person.setFirstName("Amine");
		person.setLastName("Bousta");

		super.setId(ResumeDaoHibernate.currentId++);
		super.setOwner(person);
		try {
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("AmineBousta.jpg")));
//			super.setPictureAsStream(Resume.class
//					.getResourceAsStream("AmineBousta.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
