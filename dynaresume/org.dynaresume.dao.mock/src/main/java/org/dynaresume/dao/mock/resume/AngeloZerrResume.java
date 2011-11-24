package org.dynaresume.dao.mock.resume;

import java.io.IOException;
import java.text.ParseException;

import org.dynaresume.dao.mock.SkillsData;
import org.dynaresume.domain.core.Address;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.commons.utils.DateUtils;
import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class AngeloZerrResume extends BaseResume {

	public AngeloZerrResume() {
		super.setId(getCurrentId());
		super.setTitle("Ingénieur Etude JEE/Eclipse RCP");
		try {
			// InputStream in =Resume.class
			// .getResourceAsStream("AngeloZERR.jpg");
			// IOUtils.toByteArray(Resume.class
			// .getResourceAsStream("AngeloZERR.jpg"));
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("AngeloZERR.jpg")));
			// super.setPictureAsStream(Resume.class
			// .getResourceAsStream("AngeloZERR.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Angelo
		NaturalPerson person = new NaturalPerson();
		person.setId(getCurrentId());
		person.setFirstName("Angelo");
		person.setLastName("ZERR");
		person.setEmail("angelo.zerr@gmail.com");
		try {
			person.setBirthDate(DateUtils.toDate("24/02/1977",
					DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		super.setOwner(person);

		// Address
		Address address = new Address();
		address.setId(getCurrentId());
		person.setAddress(address);

		address.setStreet("5 avenue Frederic Mistral");
		address.setCity("Saint Paul 3 Châteaux");
		address.setZipCode("26130");

		// Educations
		addEducation("Diplôme d'ingénieur en informatique", "INSA de Lyon",
				"30/06/2001");
		addEducation("BAC S option Physique C", "Georges de la Tour (Nancy)",
				"30/06/1996");

		// Experiences
		addExperience(
				"Projet SIDoc",
				"Conception / Développement",
				"Mise en place de l'application <b>WEB de diffusion</b> (qui sera accessible dans les accueils des CAF) qui permet de publier les documents XML produits par l'application WEB de production.",
				"01/04/2009", null);
		addExperience(
				"ERP AgroV3",
				"Conception / Développement",
				"Conception et développement de fonctionnalités dans le  module VENTES/ACHATS et COMPTABILITE de l'ERP agrolimentaire AgroV3 de <b>INFOLOGIC</b>. Cet ERP est basé sur les technologies d'<b>Eclipse SWT et JFace</b>.",
				"30/08/2007", "31/03/2009");

		// Skills
		addSkill(SkillsData.functionalSkills, "Gestion documentaire");
		addSkill(SkillsData.functionalSkills, "Logistique/Transport");
		addSkill(SkillsData.functionalSkills, "Nucléaire");
		addSkill(SkillsData.functionalSkills, "Agroalimentaire");
		addSkill(
				SkillsData.processSkills,
				"Analyse, conception et développement Nouvelles Technologies de logiciels et applications WEB.s");
		addSkill(SkillsData.processSkills,
				"Rédaction de documents, manuel d’utilisation, proposition commerciales");
		addSkill(SkillsData.langagesTechnicalSkills, "Java");
		addSkill(SkillsData.osTechnicalSkills, "Windows");
		addSkillWithSplit(SkillsData.databaseTechnicalSkills,
				"Oracle 8i, Oracle 9i, Oracle 10g, SQL Server, MySQL");
		addSkillWithSplit(
				SkillsData.technologiesTechnicalSkills,
				"OSGi , Spring DM, Eclipse RCP, SWT/JFace, EMF, GEF, JEE, JSP, Struts 1.x, Ant, POI, Hibernate, Spring, EJB2, Freemarker, Velocity, Web Service (AXIS), HTML, CSS, JavaScript, XML, XSL, XSD, Ajax, XQuery");
		addSkillWithSplit(
				SkillsData.softwaresTechnicalSkills,
				"Jetty, Apache/Tomcat 5.0, BEA/WebLogic 6.1-8.1, Orion, Eclipse, JBuilder 7 et 9, JBuilder X, Visual Studio");
		addSkillWithSplit(SkillsData.methodsAndToolsSkills,
				"Merise, UML (Power Designer)");

		// Hobbies
		addHobby("Sport: Badminton.");
		addHobby("Musique : pratique la batterie dans un groupe.");
		addHobby("Projets Open source.");
	}
}