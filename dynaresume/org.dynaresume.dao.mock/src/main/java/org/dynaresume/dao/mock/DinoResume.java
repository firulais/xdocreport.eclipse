package org.dynaresume.dao.mock;

import java.io.IOException;
import java.text.ParseException;

import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.commons.utils.DateUtils;
import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class DinoResume extends Resume {

	public DinoResume() {
		super.setId(ResumeDaoMock.currentId++);
		super.setTitle("Ingénieur Etude JEE");

		NaturalPerson person = new NaturalPerson();
		person.setId(ResumeDaoMock.currentId++);
		person.setFirstName("Dino");
		person.setLastName("COSMAS");
		person.setEmail("dinous@gmail.com");
		try {
			person.setBirthDate(DateUtils.toDate("09/09/1983",
					DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		super.setId(ResumeDaoMock.currentId++);
		super.setOwner(person);

		try {
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("DinoCOSMAS.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
