package org.dynaresume.dao.mock;

import java.io.IOException;

import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class PascalResume extends BaseResume {

	public PascalResume() {
		// Pascal
		NaturalPerson person = new NaturalPerson();
		person.setId(getCurrentId());
		person.setFirstName("Pascal");
		person.setLastName("Leclercq");
		person.setEmail("pascal.leclercq@gmail.com");

		super.setId(getCurrentId());
		super.setOwner(person);
		
		
		try {
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("PascalLeclercq.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
