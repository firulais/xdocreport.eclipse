package org.dynaresume.data.resume;

import java.io.IOException;

import org.dynaresume.data.SkillsInjector;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class PascalLeclercqResume extends AbstractResumeFactory {

	public PascalLeclercqResume(SkillsInjector skillsInjector) {
		super(skillsInjector);
		super.setTitle("Consultant et développeur Java");

		NaturalPerson person = new NaturalPerson();
		// person.setId(getCurrentId());
		person.setFirstName("Pascal");
		person.setLastName("Leclercq");
		person.setEmail("pascal.leclercq@gmail.com");
		super.setOwner(person);

		try {
			setPicture(IOUtils.toByteArray(PascalLeclercqResume.class
					.getResourceAsStream("PascalLeclercq.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		addSkillWithSplit(getSkillsInjector().technologiesTechnicalSkills,
				"OSGi, Spring, Eclipse RCP, Maven");

		// Languages
		addLanguage(DefaultLanguageCode.English);

	}
}
