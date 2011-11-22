package org.dynaresume.dao.mock;

import java.io.IOException;

import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class PascalResume extends Resume {

	public PascalResume() {
		// Pascal
		NaturalPerson person = new NaturalPerson();
		person.setId(MockResumeDao.currentId++);
		person.setFirstName("Pascal");
		person.setLastName("Leclercq");
		person.setEmail("pascal.leclercq@gmail.com");

		super.setId(MockResumeDao.currentId++);
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
