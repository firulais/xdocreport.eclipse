package fr.opensagres.xdocreport.eclipse.demo.resume.services.dao.hibernate;

import java.io.IOException;

import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.core.io.IOUtils;

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
