package fr.opensagres.xdocreport.eclipse.demo.resume.services.dao.hibernate;

import java.io.IOException;

import fr.opensagres.xdocreport.core.io.IOUtils;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public class PascalResume extends Resume {

	public PascalResume() {
		// Pascal
		NaturalPerson person = new NaturalPerson();
		person.setId(ResumeDaoHibernate.currentId++);
		person.setFirstName("Pascal");
		person.setLastName("Leclercq");
		person.setEmail("pascal.leclercq@gmail.com");

		super.setId(ResumeDaoHibernate.currentId++);
		super.setOwner(person);
		
		
		try {
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("PascalLeclercq.jpg")));
//			super.setPictureAsStream(Resume.class
//					.getResourceAsStream("PascalLeclercq.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
