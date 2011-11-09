package fr.opensagres.xdocreport.eclipse.demo.resume.services.dao.hibernate;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import fr.opensagres.xdocreport.core.io.IOUtils;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.Address;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Education;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Experience;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Hobby;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;
import fr.opensagres.xdocreport.eclipse.demo.resume.services.impl.DateUtils;

public class AngeloResume extends Resume {

	public AngeloResume() {
		// resume
		super.setId(ResumeServiceImpl.currentId++);
		try {
			super.setPictureAsStream(Resume.class
					.getResourceAsStream("AngeloZERR.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setTitle("Ingénieur Etude JEE/Eclipse RCP");

		
		// Person
		NaturalPerson person = new NaturalPerson();
<<<<<<< HEAD:commons/fr.opensagres.xdocreport.eclipse.demo.resume.impl/src/main/java/fr/opensagres/xdocreport/eclipse/demo/resume/services/dao/hibernate/AngeloResume.java
		person.setId(ResumeDaoHibernate.currentId++);
=======
		super.setOwner(person);
		person.setId(ResumeServiceImpl.currentId++);
>>>>>>> 60349a5d501240c66f99c92cd01bcefc6ace937c:commons/fr.opensagres.xdocreport.eclipse.demo.resume.impl/src/main/java/fr/opensagres/xdocreport/eclipse/demo/resume/services/impl/AngeloResume.java
		person.setFirstName("Angelo");
		person.setLastName("ZERR");
		person.setEmail("angelo.zerr@gmail.com");
		try {
			person.setBirthDate(DateUtils.toDate("24/02/1977", DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
<<<<<<< HEAD:commons/fr.opensagres.xdocreport.eclipse.demo.resume.impl/src/main/java/fr/opensagres/xdocreport/eclipse/demo/resume/services/dao/hibernate/AngeloResume.java
		super.setId(ResumeDaoHibernate.currentId++);
		super.setOwner(person);
		try {
//			InputStream in =Resume.class
//					.getResourceAsStream("AngeloZERR.jpg");
//			IOUtils.toByteArray(Resume.class
//					.getResourceAsStream("AngeloZERR.jpg"));
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("AngeloZERR.jpg")));
//			super.setPictureAsStream(Resume.class
//					.getResourceAsStream("AngeloZERR.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
=======
>>>>>>> 60349a5d501240c66f99c92cd01bcefc6ace937c:commons/fr.opensagres.xdocreport.eclipse.demo.resume.impl/src/main/java/fr/opensagres/xdocreport/eclipse/demo/resume/services/impl/AngeloResume.java

		// Address
		Address address = new Address();
		address.setId(ResumeDaoHibernate.currentId++);
		person.setAddress(address);

		address.setStreet("5 avenue Frederic Mistral");
		address.setCity("Saint Paul 3 Châteaux");
		address.setZipCode("26130");

<<<<<<< HEAD:commons/fr.opensagres.xdocreport.eclipse.demo.resume.impl/src/main/java/fr/opensagres/xdocreport/eclipse/demo/resume/services/dao/hibernate/AngeloResume.java
		// Diplomas
		Set<Education> educations = new HashSet<Education>();

		setEducations(educations);
		Education diploma = null;

		diploma = new Education();
		diploma.setId(ResumeDaoHibernate.currentId++);
		diploma.setLabel("Diplôme d'ingénieur en informatique");
		diploma.setInstitute("INSA de Lyon");
		educations.add(diploma);
=======
		// Educations
		Set<Education> educations = new HashSet<Education>();
		super.setEducations(educations);

		Education education = null;

		// Education 1
		education = new Education();
		education.setId(ResumeServiceImpl.currentId++);
		try {
			education.setDate(DateUtils.toDate("30/06/2001", DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		education.setLabel("Diplôme d'ingénieur en informatique");
		education.setInstitute("INSA de Lyon");
		educations.add(education);

		// Education 2
		education = new Education();
		education.setId(ResumeServiceImpl.currentId++);
		try {
			education.setDate(DateUtils.toDate("30/06/1996", DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		education.setLabel("BAC S option Physique C");
		education.setInstitute("Lycée Georges de la Tour de Nancy");
		educations.add(education);
>>>>>>> 60349a5d501240c66f99c92cd01bcefc6ace937c:commons/fr.opensagres.xdocreport.eclipse.demo.resume.impl/src/main/java/fr/opensagres/xdocreport/eclipse/demo/resume/services/impl/AngeloResume.java

		// Experiences
		Set<Experience> experiences = new HashSet<Experience>();
		super.setExperiences(experiences);
		Experience experience = null;

		// Experience 1
		experience = new Experience();
		experience.setId(ResumeDaoHibernate.currentId++);
		experience.setTitle("Projet SIDoc");
		experience.setMission("Conception / Développement");
		experience
				.setDetail("Mise en place de l'application <b>WEB de diffusion</b> (qui sera accessible dans les accueils des CAF) qui permet de publier les documents XML produits par l'application WEB de production.");
		try {
			experience.setStartDate(DateUtils.toDate("01/04/2009", DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		experiences.add(experience);

		// Experience 2
		experience = new Experience();
		experience.setId(ResumeDaoHibernate.currentId++);
		experience.setTitle("ERP AgroV3");
		experience.setMission("Conception / Développement");
		experience
				.setDetail("Conception et développement de fonctionnalités dans le  module VENTES/ACHATS et COMPTABILITE de l'ERP agrolimentaire AgroV3 de <b>INFOLOGIC</b>. Cet ERP est basé sur les technologies d'<b>Eclipse SWT et JFace</b>.");
		try {
			experience.setStartDate(DateUtils.toDate("01/08/2007", DateUtils.FRENCH_PATTERN));
			experience.setEndDate(DateUtils.toDate("31/03/2009", DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		experiences.add(experience);

		// Hobbies
		Set<Hobby> hobbies = new HashSet<Hobby>();
		super.setHobbies(hobbies);
		Hobby hobby = null;

		// Hobby 1
		hobby = new Hobby();
		hobby.setId(ResumeDaoHibernate.currentId++);
		hobby.setLabel("Sport: Badminton.");
		hobbies.add(hobby);

		// Hobby 2
		hobby = new Hobby();
		hobby.setId(ResumeDaoHibernate.currentId++);
		hobby.setLabel("Musique : pratique la batterie dans un groupe.");
		hobbies.add(hobby);

		// Hobby 3
		hobby = new Hobby();
		hobby.setId(ResumeDaoHibernate.currentId++);
		hobby.setLabel("Projets Open source");
		hobbies.add(hobby);
	}
}