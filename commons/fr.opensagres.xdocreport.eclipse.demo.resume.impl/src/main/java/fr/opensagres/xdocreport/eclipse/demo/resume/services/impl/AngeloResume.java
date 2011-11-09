package fr.opensagres.xdocreport.eclipse.demo.resume.services.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.Address;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Education;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Experience;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Hobby;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

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
		super.setOwner(person);
		person.setId(ResumeServiceImpl.currentId++);
		person.setFirstName("Angelo");
		person.setLastName("ZERR");
		person.setEmail("angelo.zerr@gmail.com");
		try {
			person.setBirthDate(DateUtils.toDate("24/02/1977", DateUtils.FRENCH_PATTERN));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		// Address
		Address address = new Address();
		address.setId(ResumeServiceImpl.currentId++);
		person.setAddress(address);

		address.setStreet("5 avenue Frederic Mistral");
		address.setCity("Saint Paul 3 Châteaux");
		address.setZipCode("26130");

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

		// Experiences
		Set<Experience> experiences = new HashSet<Experience>();
		super.setExperiences(experiences);
		Experience experience = null;

		// Experience 1
		experience = new Experience();
		experience.setId(ResumeServiceImpl.currentId++);
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
		experience.setId(ResumeServiceImpl.currentId++);
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
		hobby.setId(ResumeServiceImpl.currentId++);
		hobby.setLabel("Sport: Badminton.");
		hobbies.add(hobby);

		// Hobby 2
		hobby = new Hobby();
		hobby.setId(ResumeServiceImpl.currentId++);
		hobby.setLabel("Musique : pratique la batterie dans un groupe.");
		hobbies.add(hobby);

		// Hobby 3
		hobby = new Hobby();
		hobby.setId(ResumeServiceImpl.currentId++);
		hobby.setLabel("Projets Open source");
		hobbies.add(hobby);
	}
}