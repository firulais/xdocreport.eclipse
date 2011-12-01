package org.dynaresume.data.resume;

import java.io.IOException;

import org.dynaresume.data.SkillsInjector;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class KaiTodterResume extends AbstractResumeFactory {

	public KaiTodterResume(SkillsInjector skillsInjector) {
		super(skillsInjector);
		//super.setId(getCurrentId());
		super.setTitle("Senior Engineer at Siemens AG ");
		try {
			setPicture(IOUtils.toByteArray(KaiTodterResume.class
					.getResourceAsStream("KaiTodter.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		NaturalPerson person = new NaturalPerson();
		//person.setId(getCurrentId());
		person.setFirstName("Kai");
		person.setLastName("Tödter");
		person.setEmail("kai@toedter.com");
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
		//
		// address.setStreet("5 avenue Frederic Mistral");
		// address.setCity("Saint Paul 3 Châteaux");
		// address.setZipCode("26130");

		// Educations
		addEducation("Computer Science", "Technische Universität Braunschweig",
				"01/09/1986", "30/06/1992");
		//
		// // Experiences
		addExperience("Siemens AG", "Senior Architect/Engineer", "",
				"01/01/2007", null);
		addExperience("Siemens", "Principal Engineer", "", "01/12/1999",
				"31/12/2006");
		// Skills
		// addSkill(getSkillsInjector().functionalSkills, "Gestion documentaire");
		// addSkill(getSkillsInjector().functionalSkills, "Logistique/Transport");
		// addSkill(getSkillsInjector().functionalSkills, "Nucléaire");
		// addSkill(getSkillsInjector().functionalSkills, "Agroalimentaire");
		// addSkill(
		// getSkillsInjector().processSkills,
		// "Analyse, conception et développement Nouvelles Technologies de logiciels et applications WEB.s");
		// addSkill(getSkillsInjector().processSkills,
		// "Rédaction de documents, manuel d’utilisation, proposition commerciales");
		addSkill(getSkillsInjector().langagesTechnicalSkills, "Java");
		// addSkill(getSkillsInjector().osTechnicalSkills, "Windows");
		// addSkillWithSplit(getSkillsInjector().databaseTechnicalSkills,
		// "Oracle 8i, Oracle 9i, Oracle 10g, SQL Server, MySQL");
		addSkillWithSplit(getSkillsInjector().technologiesTechnicalSkills,
				"OSGi , Spring DM, Eclipse RCP, SWT/JFace, JEE, JSP, Spring");
		// addSkillWithSplit(
		// getSkillsInjector().softwaresTechnicalSkills,
		// "Jetty, Apache/Tomcat 5.0, BEA/WebLogic 6.1-8.1, Orion, Eclipse, JBuilder 7 et 9, JBuilder X, Visual Studio");
		// addSkillWithSplit(getSkillsInjector().methodsAndToolsSkills,
		// "Merise, UML (Power Designer)");

		// Languages
		addLanguage(DefaultLanguageCode.English);
		addLanguage(DefaultLanguageCode.German);

		// Hobbies
		// addHobby("Sport: Badminton.");
		// addHobby("Musique : pratique la batterie dans un groupe.");
		// addHobby("Projets Open source.");
	}
}