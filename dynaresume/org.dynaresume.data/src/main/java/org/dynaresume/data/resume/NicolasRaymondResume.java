package org.dynaresume.data.resume;

import java.io.IOException;

import org.dynaresume.data.DataInjector;
import org.dynaresume.domain.core.Address;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class NicolasRaymondResume extends AbstractResumeFactory {

	public NicolasRaymondResume(DataInjector dataInjector) {
		super(dataInjector);
		super.setTitle("Ingénieur Etude JEE");

		NaturalPerson person = new NaturalPerson();
		// person.setId(getCurrentId());
		person.setFirstName("Nicolas");
		person.setLastName("Raymond");
		person.setEmail("koknico@gmail.com");
		// try {
		// person.setBirthDate(DateUtils.toDate("09/09/1983",
		// DateUtils.FRENCH_PATTERN));
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }

		// super.setId(getCurrentId());
		super.setOwner(person);

		try {
			setPicture(IOUtils.toByteArray(NicolasRaymondResume.class
					.getResourceAsStream("NicolasRaymond.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Address
		Address address = new Address();
		// address.setId(getCurrentId());
		// person.setAddress(address);
		//
		// address.setStreet("2 rue Greuze");
		// address.setCity("Villeurbanne");
		// address.setZipCode("69100");
		//
		// Educations
		addEducation("Diplôme d’ingénieur", "Ecole Centrale de Lyon (69)",
				null, "30/06/2001");
		addEducation("BAC S option Technologies Industrielles", "Trévoux (01)",
				null, "30/06/1995");
		//
		// // Experiences
		// addExperience(
		// "Création d'un protoype applicatif : D3KODE",
		// "Gestion / Conception / Développement",
		// "Gestion, conception et développement d'un prototype applicatif (D3KODE) permettant la mise en oeuvre de la théorie de la trace modélisée appliquée à l'observation d'activité tracée.",
		// "24/01/2011", "04/11/2011");
		// addExperience(
		// "Projet SIDoc",
		// "Conception / Développement",
		// "Projet de création et maintenance documentaire sur les différentes thématiques de la caisse d’allocation familiale.",
		// "01/07/2009", "21/01/2011");
		//
		// Languages
		addLanguage(DefaultLanguageCode.English);

		// // Skills
		// addSkill(SkillsData.functionalSkills, "Gestion documentaire");
		// addSkill(SkillsData.functionalSkills, "Logistique/Transport");
		// addSkill(SkillsData.functionalSkills, "Energie");
		// addSkill(SkillsData.functionalSkills, "E-commerce");
		// addSkill(SkillsData.functionalSkills, "R&D");
		// addSkill(SkillsData.functionalSkills, "Web Sémantique");
		// addSkill(
		// SkillsData.processSkills,
		// "Analyse, conception et développement Nouvelles Technologies de logiciels et applications WEB.");
		// addSkill(SkillsData.processSkills,
		// "Rédaction de documents, manuel d’utilisation, proposition commerciales");
		// addSkill(SkillsData.langagesTechnicalSkills, "Java");
		// addSkill(SkillsData.osTechnicalSkills, "Windows");
		// addSkill(SkillsData.osTechnicalSkills, "Linux");
		// addSkill(SkillsData.osTechnicalSkills, "Mac OS");
		// addSkillWithSplit(SkillsData.databaseTechnicalSkills,
		// "Oracle 8i,Oracle 9i, Oracle 10g, SQL Server, MySQL, Postgres, KTBS");
		// addSkillWithSplit(
		// SkillsData.technologiesTechnicalSkills,
		// "OSGi , Eclipse RCP, J2EE, JSP, Struts, Ant, POI, Hibernate, Spring, EJB2-3, Freemarker, Velocity, HTML, CSS, JavaScript, JQuery, XML, XSL, XSD, RDF, Ajax, XQuery");
		// addSkillWithSplit(SkillsData.softwaresTechnicalSkills,
		// "Jetty, Apache/Tomcat 5.0, BEA/WebLogic 6.1-8.1, Eclipse");
		// addSkillWithSplit(SkillsData.methodsAndToolsSkills,
		// "Merise, UML, MDA");
		//
		// // Hobbies
		// addHobby("Sport: football, handball, natation, plongée.");
		// addHobby("Voyage");
		// addHobby("Cinéma");
		// addHobby("Veille technologique");
	}

}
