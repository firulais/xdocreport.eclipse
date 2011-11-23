package org.dynaresume.dao.mock;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.dynaresume.domain.core.Address;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Education;
import org.dynaresume.domain.hr.Experience;
import org.dynaresume.domain.hr.Hobby;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.commons.utils.DateUtils;
import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class AngeloResume extends BaseResume {

	public AngeloResume() {
		super.setId(MockResumeDao.currentId++);
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
		person.setId(MockResumeDao.currentId++);
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
		address.setId(MockResumeDao.currentId++);
		person.setAddress(address);

		address.setStreet("5 avenue Frederic Mistral");
		address.setCity("Saint Paul 3 Châteaux");
		address.setZipCode("26130");

		// Diplomas
		Set<Education> educations = new HashSet<Education>();

		setEducations(educations);
		Education education = null;

		// Education 1
		education = new Education();
		education.setId(MockResumeDao.currentId++);
		education.setLabel("Diplôme d'ingénieur en informatique");
		education.setInstitute("INSA de Lyon");
		try {
			education.setDate(DateUtils.toDate("30/06/2001",
					DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		educations.add(education);

		// Education 2
		education = new Education();
		education.setId(MockResumeDao.currentId++);
		education.setLabel("BAC S option Physique C");
		education.setInstitute("Georges de la Tour (Nancy)");
		try {
			education.setDate(DateUtils.toDate("30/06/1996",
					DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		educations.add(education);

		// Experiences
		Set<Experience> experiences = new HashSet<Experience>();
		super.setExperiences(experiences);
		Experience experience = null;

		// Experience 1
		experience = new Experience();
		experience.setId(MockResumeDao.currentId++);
		experience.setTitle("Projet SIDoc");
		experience.setMission("Conception / Développement");
		experience
				.setDetail("Mise en place de l'application <b>WEB de diffusion</b> (qui sera accessible dans les accueils des CAF) qui permet de publier les documents XML produits par l'application WEB de production.");
		try {
			experience.setStartDate(DateUtils.toDate("01/04/2009",
					DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		experiences.add(experience);

		// Experience 2
		experience = new Experience();
		experience.setId(MockResumeDao.currentId++);
		experience.setTitle("ERP AgroV3");
		experience.setMission("Conception / Développement");
		experience
				.setDetail("Conception et développement de fonctionnalités dans le  module VENTES/ACHATS et COMPTABILITE de l'ERP agrolimentaire AgroV3 de <b>INFOLOGIC</b>. Cet ERP est basé sur les technologies d'<b>Eclipse SWT et JFace</b>.");
		try {
			experience.setStartDate(DateUtils.toDate("30/08/2007",
					DateUtils.FRENCH_PATTERN));
			experience.setEndDate(DateUtils.toDate("31/03/2009",
					DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		experiences.add(experience);

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
		addSkill(SkillsData.osTechnicalSkills, "Windos");
		addSkillWithSplit(SkillsData.databaseTechnicalSkills,
				"Oracle 8i-9i-10g, SQL Server 7/2k, MySQL, Sybase ASA 7 et ASE 11.9.2");
		addSkillWithSplit(
				SkillsData.technologiesTechnicalSkills,
				"OSGI , Spring DM, Eclipse RCP, SWT/JFace, EMF, GEF, J2EE, JSP, Struts, Ant, POI, Hibernate, Spring, EJB2, Freemarker, Velocity, Web Service (AXIS), HTML, CSS, JavaScript, XML, XSL, Ajax, XQuery");
		addSkillWithSplit(
				SkillsData.softwaresTechnicalSkills,
				"Jetty, Apache/Tomcat 5.0, BEA/WebLogic 6.1-8.1, Orion, Eclipse, JBuilder 7 et 9, JBuilder X, Visual Studio");
		addSkillWithSplit(SkillsData.methodsAndToolsSkills,
				"Merise, UML (Power Designer)");

		// Hobbies
		Set<Hobby> hobbies = new HashSet<Hobby>();
		super.setHobbies(hobbies);
		Hobby hobby = null;

		// Hobby 1
		hobby = new Hobby();
		hobby.setId(MockResumeDao.currentId++);
		hobby.setLabel("Sport: Badminton.");
		hobbies.add(hobby);

		// Hobby 2
		hobby = new Hobby();
		hobby.setId(MockResumeDao.currentId++);
		hobby.setLabel("Musique : pratique la batterie dans un groupe.");
		hobbies.add(hobby);

		// Hobby 3
		hobby = new Hobby();
		hobby.setId(MockResumeDao.currentId++);
		hobby.setLabel("Projets Open source");
		hobbies.add(hobby);
	}

}