package org.dynaresume.data.resume;

import java.io.IOException;

import org.dynaresume.data.DataInjector;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class AmineBoustaResume extends AbstractResumeFactory {

	public AmineBoustaResume(DataInjector dataInjector) {
		super(dataInjector);
		NaturalPerson person = new NaturalPerson();
		// person.setId(getCurrentId());
		person.setFirstName("Amine");
		person.setLastName("Bousta");

		// super.setId(getCurrentId());
		super.setOwner(person);
		try {
			setPicture(IOUtils.toByteArray(AmineBoustaResume.class
					.getResourceAsStream("AmineBousta.jpg")));
			// super.setPictureAsStream(Resume.class
			// .getResourceAsStream("AmineBousta.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Languages
		addLanguage(DefaultLanguageCode.English);
	}

}
