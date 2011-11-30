package org.dynaresume.data.resume;

import org.dynaresume.data.SkillsInjector;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class JesseMcConnellResume extends AbstractResumeFactory {

	public JesseMcConnellResume(SkillsInjector skillsInjector) {
		super(skillsInjector);
		// super.setId(getCurrentId());
		super.setTitle("US Lead Developer at Intalio");
		try {
			setPicture(IOUtils.toByteArray(JesseMcConnellResume.class
					.getResourceAsStream("JesseMcConnell.jpg")));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		NaturalPerson person = new NaturalPerson();
		// person.setId(getCurrentId());
		person.setFirstName("Jesse");
		person.setLastName("McConnell");
		person.setEmail("");
		// try {
		// person.setBirthDate(DateUtils.toDate("24/02/1977",
		// DateUtils.FRENCH_PATTERN));
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }
		super.setOwner(person);

		// Address
		// Address address = new Address();
		// address.setId(getCurrentId());
		// person.setAddress(address);

		// address.setStreet("5 avenue Frederic Mistral");
		// address.setCity("Saint Paul 3 Châteaux");
		// address.setZipCode("26130");

		// Educations
		addEducation("", "University of Idaho", "01/09/1993", "30/06/2000");

		// Experiences
		addExperience("Intalio", "US Lead Developer", "", "01/06/2011", null);
		addExperience("Webtide", "Senior Engineer", "", "01/12/2007",
				"01/09/2009");
		addExperience("DevZuz", "Software Developer", "", "01/02/2006",
				"01/11/2007");
		addExperience("The Gallup Organization", "Java Developer", "",
				"01/06/2001", "01/02/2006");

		// Skills
		// addSkill(getSkillsInjector().functionalSkills,
		// "Gestion documentaire");
		// addSkill(getSkillsInjector().functionalSkills,
		// "Logistique/Transport");
		// addSkill(getSkillsInjector().functionalSkills, "Nucléaire");
		// addSkill(getSkillsInjector().functionalSkills, "Agroalimentaire");
		// addSkill(getSkillsInjector().processSkills,
		// "Java Architect and Consultant");
		addSkill(getSkillsInjector().langagesTechnicalSkills, "Java");
		addSkillWithSplit(getSkillsInjector().technologiesTechnicalSkills,
				"Java, Maven, Eclipse, Jetty");

		// References
		addReference("PMC Member, Committer - Eclipse Foundation",
				"01/03/2009", null);
		addReference("PMC Member, Committer - EApache Software Foundation",
				"01/04/2006", null);
		addReference("Hausmate - The Codehaus", "01/11/2005", null);

		// Languages
		addLanguage(DefaultLanguageCode.English);

		// Hobbies
		// addHobby("Sport: Badminton.");
		// addHobby("Musique : pratique la batterie dans un groupe.");
		// addHobby("Projets Open source.");
	}
}