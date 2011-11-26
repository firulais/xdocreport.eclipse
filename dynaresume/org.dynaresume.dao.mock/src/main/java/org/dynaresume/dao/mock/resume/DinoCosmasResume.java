package org.dynaresume.dao.mock.resume;

import java.io.IOException;
import java.text.ParseException;

import org.dynaresume.dao.mock.SkillsData;
import org.dynaresume.domain.core.Address;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.commons.utils.DateUtils;
import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class DinoCosmasResume extends BaseResume {

	public DinoCosmasResume() {
		super.setId(getCurrentId());
		super.setTitle("Ingénieur Etude JEE");

		NaturalPerson person = new NaturalPerson();
		person.setId(getCurrentId());
		person.setFirstName("Dino");
		person.setLastName("COSMAS");
		person.setEmail("dino.cosmas@gmail.com");
		try {
			person.setBirthDate(DateUtils.toDate("09/09/1983",
					DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		super.setId(getCurrentId());
		super.setOwner(person);

		try {
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("DinoCOSMAS.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Address
		Address address = new Address();
		address.setId(getCurrentId());
		person.setAddress(address);

		address.setStreet("2 rue Greuze");
		address.setCity("Villeurbanne");
		address.setZipCode("69100");

		// Educations
		addEducation("Diplôme d'ingénieur en informatique", "CNAM Rhône Alpes",
				"01/09/2005", "30/06/2012");
		addEducation("Licence Nouvelles Architectures Applicatives",
				"Université Claude Bernard Lyon1", "01/09/2004", "30/06/2005");
		addEducation(
				"BTS Informatique de Gestion option développeur d'application",
				"Lycée Lamartine", "30/06/2004", "30/06/2004");
		addEducation("DEUG MIAS", "Université Claude Bernard Lyon1",
				"30/06/2004", "30/06/2004");
		addEducation("BAS S option Science de la Vie et de la Terre",
				"Lycée René Cassin", "30/06/2004", "30/06/2004");

		// Experiences
		addExperience(
				"Création d'un protoype applicatif : D3KODE",
				"Gestion / Conception / Développement",
				"Gestion, conception et développement d'un prototype applicatif (D3KODE) permettant la mise en oeuvre de la théorie de la trace modélisée appliquée à l'observation d'activité tracée.",
				"24/01/2011", "04/11/2011");
		addExperience(
				"Projet SIDoc",
				"Conception / Développement",
				"Projet de création et maintenance documentaire sur les différentes thématiques de la caisse d’allocation familiale.",
				"01/07/2009", "21/01/2011");

		// Skills
		addSkill(SkillsData.functionalSkills, "Gestion documentaire");
		addSkill(SkillsData.functionalSkills, "Logistique/Transport");
		addSkill(SkillsData.functionalSkills, "Energie");
		addSkill(SkillsData.functionalSkills, "E-commerce");
		addSkill(SkillsData.functionalSkills, "R&D");
		addSkill(SkillsData.functionalSkills, "Web Sémantique");
		addSkill(
				SkillsData.processSkills,
				"Analyse, conception et développement Nouvelles Technologies de logiciels et applications WEB.");
		addSkill(SkillsData.processSkills,
				"Rédaction de documents, manuel d’utilisation, proposition commerciales");
		addSkill(SkillsData.langagesTechnicalSkills, "Java");
		addSkill(SkillsData.osTechnicalSkills, "Windows");
		addSkill(SkillsData.osTechnicalSkills, "Linux");
		addSkill(SkillsData.osTechnicalSkills, "Mac OS");
		addSkillWithSplit(SkillsData.databaseTechnicalSkills,
				"Oracle 8i,Oracle 9i, Oracle 10g, SQL Server, MySQL, Postgres, KTBS");
		addSkillWithSplit(
				SkillsData.technologiesTechnicalSkills,
				"OSGi , Eclipse RCP, J2EE, JSP, Struts, Ant, POI, Hibernate, Spring, EJB2-3, Freemarker, Velocity, HTML, CSS, JavaScript, jQuery, XML, XSL, XSD, RDF, Ajax, XQuery");
		addSkillWithSplit(SkillsData.softwaresTechnicalSkills,
				"Jetty, Apache/Tomcat 5.0, BEA/WebLogic 6.1-8.1, Eclipse");
		addSkillWithSplit(SkillsData.methodsAndToolsSkills, "Merise, UML, MDA");

		// Hobbies
		addHobby("Sport: football, handball, natation, plongée.");
		addHobby("Voyage");
		addHobby("Cinéma");
		addHobby("Veille technologique");
	}

}
