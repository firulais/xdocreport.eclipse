package org.dynaresume.data.resume;

import org.dynaresume.data.SkillsInjector;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class JawherMoussaResume extends AbstractResumeFactory {

	public JawherMoussaResume(SkillsInjector skillsInjector) {
		super(skillsInjector);
		// super.setId(getCurrentId());
		super.setTitle("Consultant chez Zenika");
		try {
			setPicture(IOUtils.toByteArray(JawherMoussaResume.class
					.getResourceAsStream("JawherMoussa.jpg")));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		NaturalPerson person = new NaturalPerson();
		// person.setId(getCurrentId());
		person.setFirstName("Jawher");
		person.setLastName("Moussa");
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
		addEducation("Engineering, Computer Science/Informatique", "ENSI",
				"01/09/2004", "30/06/2007");
		addEducation(
				"Preperatory studies",
				"Engineering, Institut Préparatoire aux Etudes Scientifiques et Technologiques",
				"01/09/2001", "30/06/2003");
		addEducation(" ", "Lycée Pilote de Gafsa", "01/09/1994", "30/06/2001");

		// Experiences
		addExperience(
				"Consultant Zenika",
				"",
				"I'm working on a desktop client (using Eclipse RCP) that'll be used by AFP journalists to produce and consume news ",
				"01/03/2011", null);
		addExperience("Engineer IP-TECH", "",
				"I was part of the team who made http://www.autoforfait.com/ ",
				"01/03/2010", "01/01/2011");
		addExperience(
				"Engineer Waycon",
				"",
				"Création d'une application web de suivi et de gestion de temps et de visites en utilisant JSF+Richfaces, Spring, Jasper Reports."
						+ "Conception d'une application modulaire et multi-clients serveur de gestion de temps, développement du serveur métier et de divers clients web en utilisant OSGi, Spring et Spring DM, Wicket, Apache POI."
						+ "Participé à la conception et implémentation du client Eclipse RCP. ",
				"01/10/2007", "01/03/2010");

		// Skills
		// addSkill(getSkillsInjector().functionalSkills, "Gestion documentaire");
		// addSkill(getSkillsInjector().functionalSkills, "Logistique/Transport");
		// addSkill(getSkillsInjector().functionalSkills, "Nucléaire");
		// addSkill(getSkillsInjector().functionalSkills, "Agroalimentaire");
		addSkill(getSkillsInjector().processSkills, "Java Architect and Consultant");
		addSkill(getSkillsInjector().langagesTechnicalSkills, "Java");
		addSkillWithSplit(getSkillsInjector().technologiesTechnicalSkills,
				"Java, OSGi, Eclipse RCP, Spring, Spring Dynamic Modules, Wicket");

		// References
		addReference(
				"Eclipse Zone Leader/Moderator/Writer/FAQ Leader - Developpez.com",
				"01/06/2008", "01/08/2009");

		// Languages
		addLanguage(DefaultLanguageCode.English);

		// Hobbies
		// addHobby("Sport: Badminton.");
		// addHobby("Musique : pratique la batterie dans un groupe.");
		// addHobby("Projets Open source.");
	}
}