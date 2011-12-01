package org.dynaresume.data.resume;

import java.io.IOException;
import java.text.ParseException;

import org.dynaresume.data.DataInjector;
import org.dynaresume.domain.core.Address;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.DateUtils;
import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class YannickVimalResume extends AbstractResumeFactory {

	public YannickVimalResume(DataInjector dataInjector) {
		super(dataInjector);
		super.setTitle("Ingénieur Etude JEE");
		try {
			setPicture(IOUtils.toByteArray(YannickVimalResume.class
					.getResourceAsStream("YannickVIMAL.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// yannick
		NaturalPerson person = new NaturalPerson();
		// person.setId(getCurrentId());
		person.setFirstName("Yannick");
		person.setLastName("Vimal");
		person.setEmail("yannick.vimal@gmail.com");
		try {
			person.setBirthDate(DateUtils.toDate("09/11/1983",
					DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		super.setOwner(person);

		// Address
		Address address = new Address();
		// address.setId(getCurrentId());
		person.setAddress(address);

		address.setStreet("255 rue andré philip");
		address.setCity("Lyon");
		address.setZipCode("69003");

		// Educations
		addEducation("Ecole Ingénieur informatique", "3iL", null, "30/06/2009");
		addEducation("Master management international par projet",
				"ISMANS (Le mans) et L’UQO (Gatineau, Quebec)", null,
				"30/06/2010");

		// Experiences
		addExperience(
				"Projet SIDoc",
				"Développement / Cahiers de tests fonctionnels",
				"Conception et développement de l’outil de production et de gestion documentaire SIDOC qui gère les documents législatifs et leurs cycles de vie. Mise en place et application de cahiers de tests fonctionnels.",
				"01/10/2009", null);
		addExperience(
				"Agatt",
				"Intégration continue",
				"Mise en place d'une plateforme d'intégration continue sous Visual Studio dans le cadre d'un projet regroupant environ 150 personnes à travers 4 pays (France, Espagne, Argentine et Inde)",
				"01/10/2008", "31/03/2009");
		addExperience(
				"Avisor",
				"Développement Web",
				"Mise en place de scripts PHP sur une plateforme d'envoi de sms.",
				"15/06/2007", "30/08/2007");

		// Skills
		addSkill(getSkillsInjector().functionalSkills, "Gestion documentaire");
		addSkill(getSkillsInjector().functionalSkills, "Emploi");
		addSkill(getSkillsInjector().functionalSkills, "Téléphonie mobile");
		addSkill(
				getSkillsInjector().processSkills,
				"Analyse, conception et développement Nouvelles Technologies de logiciels et applications WEB.s");
		addSkill(getSkillsInjector().langagesTechnicalSkills, "Java");
		addSkill(getSkillsInjector().osTechnicalSkills, "Windows");
		addSkillWithSplit(getSkillsInjector().databaseTechnicalSkills,
				"SQL Server, MySQL, XHive");
		addSkillWithSplit(getSkillsInjector().technologiesTechnicalSkills,
				"Eclipse,JAVA, JEE, PHP, HTML, CSS, JavaScript, XML, XSL, XSD, Xforms, XQuery");
		addSkillWithSplit(getSkillsInjector().softwaresTechnicalSkills,
				"Eclipse, Visual Studio, Team Foundation server, Photoshop");
		addSkillWithSplit(getSkillsInjector().methodsAndToolsSkills,
				"Merise, UML");

		// Languages
		addLanguage(DefaultLanguageCode.English);

		// Hobbies
		addHobby("Moto");
		addHobby("Cinéma");
	}
}