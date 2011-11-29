package org.dynaresume.dao.mock.resume;

import java.io.IOException;

import org.dynaresume.dao.mock.SkillsData;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class KaiTodterResume extends BaseResume {

	public KaiTodterResume() {
		super.setId(getCurrentId());
		super.setTitle("Senior Engineer at Siemens AG ");
		try {
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("KaiTodter.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		NaturalPerson person = new NaturalPerson();
		person.setId(getCurrentId());
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
		// addSkill(SkillsData.functionalSkills, "Gestion documentaire");
		// addSkill(SkillsData.functionalSkills, "Logistique/Transport");
		// addSkill(SkillsData.functionalSkills, "Nucléaire");
		// addSkill(SkillsData.functionalSkills, "Agroalimentaire");
		// addSkill(
		// SkillsData.processSkills,
		// "Analyse, conception et développement Nouvelles Technologies de logiciels et applications WEB.s");
		// addSkill(SkillsData.processSkills,
		// "Rédaction de documents, manuel d’utilisation, proposition commerciales");
		addSkill(SkillsData.langagesTechnicalSkills, "Java");
		// addSkill(SkillsData.osTechnicalSkills, "Windows");
		// addSkillWithSplit(SkillsData.databaseTechnicalSkills,
		// "Oracle 8i, Oracle 9i, Oracle 10g, SQL Server, MySQL");
		addSkillWithSplit(SkillsData.technologiesTechnicalSkills,
				"OSGi , Spring DM, Eclipse RCP, SWT/JFace, JEE, JSP, Spring");
		// addSkillWithSplit(
		// SkillsData.softwaresTechnicalSkills,
		// "Jetty, Apache/Tomcat 5.0, BEA/WebLogic 6.1-8.1, Orion, Eclipse, JBuilder 7 et 9, JBuilder X, Visual Studio");
		// addSkillWithSplit(SkillsData.methodsAndToolsSkills,
		// "Merise, UML (Power Designer)");

		// Languages
		addLanguage(DefaultLanguageCode.English);
		addLanguage(DefaultLanguageCode.Germany);

		// Hobbies
		// addHobby("Sport: Badminton.");
		// addHobby("Musique : pratique la batterie dans un groupe.");
		// addHobby("Projets Open source.");
	}
}