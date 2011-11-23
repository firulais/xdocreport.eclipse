package org.dynaresume.dao.mock;

import java.io.IOException;

import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class AmineResume extends BaseResume {

	public AmineResume() {
		// Pascal
		NaturalPerson person = new NaturalPerson();
		person.setId(getCurrentId());
		person.setFirstName("Amine");
		person.setLastName("Bousta");

		super.setId(getCurrentId());
		super.setOwner(person);
		try {
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("AmineBousta.jpg")));
			// super.setPictureAsStream(Resume.class
			// .getResourceAsStream("AmineBousta.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
