package org.dynaresume.data.resume;

import java.io.IOException;

import org.dynaresume.data.SkillsInjector;
import org.dynaresume.domain.core.Address;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class RalfSternbergResume extends AbstractResumeFactory {

	public RalfSternbergResume(SkillsInjector skillsInjector) {
		super(skillsInjector);
		super.setTitle("Co-Lead of Rich Ajax Platform ");
		try {
			// InputStream in =Resume.class
			// .getResourceAsStream("AngeloZERR.jpg");
			// IOUtils.toByteArray(Resume.class
			// .getResourceAsStream("AngeloZERR.jpg"));
			setPicture(IOUtils.toByteArray(RalfSternbergResume.class
					.getResourceAsStream("RalfSternberg.jpg")));
			// super.setPictureAsStream(Resume.class
			// .getResourceAsStream("AngeloZERR.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		NaturalPerson person = new NaturalPerson();
		// person.setId(getCurrentId());
		person.setFirstName("Ralf");
		person.setLastName("Sternberg");
		person.setEmail("rsternberg@eclipsesource.com");
		// try {
		// person.setBirthDate(DateUtils.toDate("24/02/1977",
		// DateUtils.FRENCH_PATTERN));
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }
		super.setOwner(person);

		// Address
		Address address = new Address();
		// address.setId(getCurrentId());
		// person.setAddress(address);
		//
		// address.setStreet("5 avenue Frederic Mistral");
		// address.setCity("Saint Paul 3 Châteaux");
		// address.setZipCode("26130");

		// Educations
		addEducation("Computer Science, Psychology (minor) ",
				"Eberhard-Karls-Universität Tübingen", null, "30/06/2006");

		// Experiences

		addExperience("Project Co-Lead of Eclipse Rich Ajax Platform",
				"EclipseSource", "", "01/04/2011", null);
		addExperience("Eclipse Commiter", "Eclipse", "", "01/01/2007", null);
		addExperience("Senior Developer and Consultant", "EclipseSource", "",
				"01/12/2006", null);
		// Skills
		addSkillWithSplit(
				getSkillsInjector().technologiesTechnicalSkills,
				"Java, JavaScript, Web technologies, Eclipse, SWT, Eclipse RCP, Eclipse RAP, jQuery");

		// Languages
		addLanguage(DefaultLanguageCode.English);
		addLanguage(DefaultLanguageCode.German);

		addReference("Project Co-Lead of Eclipse Rich Ajax Platform",
				"01/04/2011", null);
		// Hobbies
		// addHobby("Sport: Badminton.");
		// addHobby("Musique : pratique la batterie dans un groupe.");
		// addHobby("Projets Open source.");
	}
}