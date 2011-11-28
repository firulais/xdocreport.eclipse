package org.dynaresume.dao.mock.resume;

import java.io.IOException;

import org.dynaresume.dao.mock.SkillsData;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class MickaelIstriaResume extends BaseResume {

	public MickaelIstriaResume() {
		super.setId(getCurrentId());
		super.setTitle("Eclipse (Plugin/RCP) expert developer");
		try {
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("MickaelIstria.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		NaturalPerson person = new NaturalPerson();
		person.setId(getCurrentId());
		person.setFirstName("Mickael");
		person.setLastName("Istria");
		person.setEmail("mickael.istria@petalslink.com");
		// try {
		// person.setBirthDate(DateUtils.toDate("24/02/1977",
		// DateUtils.FRENCH_PATTERN));
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }
		super.setOwner(person);

		// // Address
		// Address address = new Address();
		// address.setId(getCurrentId());
		// person.setAddress(address);
		//
		// address.setStreet("5 avenue Frederic Mistral");
		// address.setCity("Saint Paul 3 Châteaux");
		// address.setZipCode("26130");

		// Educations
		addEducation(
				"Master Degree, Software engineering ",
				"Ecole Nationale Supérieure d'Informatique et de Mathématiques Appliquées de Grenoble",
				"01/09/2005", "30/06/2008");

		// Experiences
		addExperience(
				"RD Engineer - Eclipse-based \"Petals Studio\" developer",
				"",
				"I work as an Eclipse plugin/RCP developer on the Petals Studio.",
				"01/08/2011", null);
		addExperience(
				"Speaker - Eclipse Evangelist",
				"",
				"Presented some Eclipse technologies at the following events:"
						+ "* SoftShake 2011 http://soft-shake.ch/2011/conference/sessions/java/2011/08/23/modeling-with-eclipse.html"
						+ "- Modeling with Eclipse http://www.slideshare.net/mickaelistria/modelingwitheclipse"
						+ "* Eclipse Demo Camp Grenoble 2011 ( http://wiki.eclipse.org/Eclipse_DemoCamps_Indigo_2011/Grenoble )"
						+ "- What's new in Indigo? http://www.slideshare.net/mickaelistria/whats-new-in-eclipse-indigo-democamp-grenoble-2011"
						+ "- Modeling With Eclipse ", "01/08/2011", null);
		addExperience(
				"Speaker - BPM & Bonita",
				"",
				"Presented BPM an Bonita: http://www.slideshare.net/BonitaSoft/bpm-bonita-lyon-jug-mickael-istria ",
				"01/01/2011", null);
		addExperience(
				"Speaker - Agility",
				"",
				"Presented http://www.slideshare.net/mickaelistria/sur-la-route-de-lagilit-mixit-lyon-2011",
				"01/01/2011", null);
		addExperience(
				"RD Engineer - Eclipse-based \"Bonita Studio\" developer",
				"",
				"Bonita Studio (Eclipse RCP) Developer"
						+ "I was part of a 8-to-9-people Scrum-powered RD team and was in charge with 3 other people of the development of the studio of Bonita Open Solution."
						+ "I wrote the first lines of code of the Studio and of industrializing its development."
						+ "With this team, we received the \"Best Modeling Tool\" award during EclipseCon 2011.",
				"01/07/2009", "01/06/2011");
		addExperience(
				"RD Developer",
				"",
				"Trainee of Marc Dutoo and then employee at Open Wide."
						+ "Working on SCOrWare project, contributing to Eclipse JWT and OW2 Frascati. ",
				"01/02/2008", "01/07/2009");
		// Skills
		addSkillWithSplit(SkillsData.technologiesTechnicalSkills,
				"Eclipse, Plugins, Eclipse RCP, GMF, EMF");
		addSkillWithSplit(SkillsData.methodsAndToolsSkills,
				"Model Driven Development, Modeling, BPMN, Agile Scrum");

		// References
		addReference(
				"Committer (on GMF) and contributor onto Eclipse.org projects. As I work a lot with Eclipse tools, I also contribute back some features and patches."
						+ "I am a committer on:"
						+ "* GMF Tooling"
						+ "* Nebula"
						+ "* Java Workflow Tooling"
						+ "and I contribute actively to* SWTBot"
						+ "* GMF Runtime" + "* Tycho" + "* others.,, ",
				"01/03/2008", null);

		// Languages
		addLanguage(DefaultLanguageCode.English);

		// Hobbies
		// addHobby("Sport: Badminton.");
		// addHobby("Musique : pratique la batterie dans un groupe.");
		// addHobby("Projets Open source.");
	}
}