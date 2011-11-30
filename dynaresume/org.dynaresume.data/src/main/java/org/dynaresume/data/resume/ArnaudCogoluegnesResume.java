package org.dynaresume.data.resume;

import java.io.IOException;

import org.dynaresume.data.SkillsInjector;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class ArnaudCogoluegnesResume extends AbstractResumeFactory {

	public ArnaudCogoluegnesResume(SkillsInjector skillsInjector) {
		super(skillsInjector);
		super.setTitle("Consultant Zenika");
		try {
			setPicture(IOUtils.toByteArray(ArnaudCogoluegnesResume.class
					.getResourceAsStream("ArnaudCogoluegnes.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		NaturalPerson person = new NaturalPerson();
		// person.setId(getCurrentId());
		person.setFirstName("Arnaud");
		person.setLastName("Cogoluegnes");
		// person.setEmail("");
		// try {
		// // person.setBirthDate(DateUtils.toDate("24/02/1977",
		// // DateUtils.FRENCH_PATTERN));
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
		addEducation(
				"Internship at the mechanical and aeronautical engineering departement.",
				"University of California, Davis", "01/09/1996", "30/06/2001");
		addEducation("Ingénieur", "Ecole catholique d'Arts et Métiers de Lyon",
				"01/09/1997", "30/06/2002");

		// Experiences
		addExperience("Consultant Zenika", "", "", "01/01/2009", null);
		addExperience("Software architect - SQLI", "", "", "01/08/2004",
				"30/11/2009");

		// addExperience(
		// "ERP AgroV3",
		// "Conception / Développement",
		// "Conception et développement de fonctionnalités dans le  module VENTES/ACHATS et COMPTABILITE de l'ERP agrolimentaire AgroV3 de <b>INFOLOGIC</b>. Cet ERP est basé sur les technologies d'<b>Eclipse SWT et JFace</b>.",
		// "30/08/2007", "31/03/2009");

		// Skills
		// addSkill(getSkillsInjector().functionalSkills,
		// "Gestion documentaire");
		// addSkill(getSkillsInjector().functionalSkills,
		// "Logistique/Transport");
		// addSkill(getSkillsInjector().functionalSkills, "Nucléaire");
		// addSkill(getSkillsInjector().functionalSkills, "Agroalimentaire");
		// addSkill(
		// getSkillsInjector().processSkills,
		// "Analyse, conception et développement Nouvelles Technologies de logiciels et applications WEB.s");
		// addSkill(getSkillsInjector().processSkills,
		// "Rédaction de documents, manuel d’utilisation, proposition commerciales");
		// addSkill(getSkillsInjector().langagesTechnicalSkills, "Java");
		// addSkill(getSkillsInjector().osTechnicalSkills, "Windows");
		// addSkillWithSplit(getSkillsInjector().databaseTechnicalSkills,
		// "Oracle 8i, Oracle 9i, Oracle 10g, SQL Server, MySQL");
		addSkillWithSplit(getSkillsInjector().technologiesTechnicalSkills,
				"OSGi , Spring, Spring DM");
		// addSkillWithSplit(
		// getSkillsInjector().softwaresTechnicalSkills,
		// "Jetty, Apache/Tomcat 5.0, BEA/WebLogic 6.1-8.1, Orion, Eclipse, JBuilder 7 et 9, JBuilder X, Visual Studio");
		// addSkillWithSplit(getSkillsInjector().methodsAndToolsSkills,
		// "Merise, UML (Power Designer)");

		// References
		addReference("Co-author of \"Spring Batch in Action\"", "01/05/2010",
				null);
		addReference("Co-author of \"Spring Dynamic Modules in action\"",
				"01/03/2009", null);
		addReference("Co-author of \"Spring par la pratique\", 2nd edition",
				"01/07/2008", "01/07/2009");

		// Languages
		addLanguage(DefaultLanguageCode.English);

		// Hobbies
		// addHobby("Sport: Badminton.");
		// addHobby("Musique : pratique la batterie dans un groupe.");
		// addHobby("Projets Open source.");
	}
}