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

public class DinoResume extends Resume {

	public DinoResume() {
        super.setId(ResumeDaoMock.currentId++);
        super.setTitle("Ingénieur Etude JEE");

        NaturalPerson person = new NaturalPerson();
        person.setId(ResumeDaoMock.currentId++);
        person.setFirstName("Dino");
        person.setLastName("COSMAS");
        person.setEmail("dino.cosmas@gmail.com");
        try {
                person.setBirthDate(DateUtils.toDate("09/09/1983",
                                DateUtils.FRENCH_PATTERN));
        } catch (ParseException e1) {
                e1.printStackTrace();
        }

        super.setId(ResumeDaoMock.currentId++);
        super.setOwner(person);

        try {
                setPicture(IOUtils.toByteArray(Resume.class
                                .getResourceAsStream("DinoCOSMAS.jpg")));
        } catch (IOException e) {
                e.printStackTrace();
        }
		 // Address
        Address address = new Address();
        address.setId(ResumeDaoMock.currentId++);
        person.setAddress(address);

        address.setStreet("2 rue Greuze");
        address.setCity("Villeurbanne");
        address.setZipCode("69100");

        // Diplomas
        Set<Education> educations = new HashSet<Education>();

        setEducations(educations);
        Education education = null;

        // Education 1
        education = new Education();
        education.setId(ResumeDaoMock.currentId++);
        education.setLabel("Diplôme d'ingénieur en informatique");
        education.setInstitute("CNAM Rhône Alpes");
        try {
                education.setDate(DateUtils.toDate("30/06/2012",
                                DateUtils.FRENCH_PATTERN));
        } catch (ParseException e1) {
                e1.printStackTrace();
        }
        educations.add(education);
		
		// Education 2
        education = new Education();
        education.setId(ResumeDaoMock.currentId++);
        education.setLabel("Licence Nouvelles Architectures APplicatives");
        education.setInstitute("Université Claude Bernard Lyon1");
        try {
                education.setDate(DateUtils.toDate("30/06/2005",
                                DateUtils.FRENCH_PATTERN));
        } catch (ParseException e1) {
                e1.printStackTrace();
        }
        educations.add(education);
		
		// Education 3
        education = new Education();
        education.setId(ResumeDaoMock.currentId++);
        education.setLabel("BTS Informatique de Gestion option développeur d'application");
        education.setInstitute("Lycée Lamartine");
        try {
                education.setDate(DateUtils.toDate("30/06/2004",
                                DateUtils.FRENCH_PATTERN));
        } catch (ParseException e1) {
                e1.printStackTrace();
        }
        educations.add(education);
		
		// Education 4
        education = new Education();
        education.setId(ResumeDaoMock.currentId++);
        education.setLabel("DEUG MIAS");
        education.setInstitute("Université Claude Bernard Lyon1");
        try {
                education.setDate(DateUtils.toDate("30/06/2004",
                                DateUtils.FRENCH_PATTERN));
        } catch (ParseException e1) {
                e1.printStackTrace();
        }
        educations.add(education);
		
		// Education 5
        education = new Education();
        education.setId(ResumeDaoMock.currentId++);
        education.setLabel("BAS S option Science de la Vie et de la Terre");
        education.setInstitute("Lycée René Cassin");
        try {
                education.setDate(DateUtils.toDate("30/06/2004",
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
        experience.setId(ResumeDaoMock.currentId++);
        experience.setTitle("Création d'un protoype applicatif : D3KODE");
        experience.setMission("Gestion / Conception / Développement");
        experience
                        .setDetail("Gestion, conception et développement d'un prototype applicatif (D3KODE) permettant la mise en oeuvre de la théorie de la trace modélisée appliquée à l'observation d'activité tracée.");
        try {
                experience.setStartDate(DateUtils.toDate("24/01/2011",
                                DateUtils.FRENCH_PATTERN));
                experience.setEndDate(DateUtils.toDate("04/11/2011",
                                DateUtils.FRENCH_PATTERN));
        } catch (ParseException e1) {
                e1.printStackTrace();
        }
        experiences.add(experience);
		
        // Experience 2
        experience = new Experience();
        experience.setId(ResumeDaoMock.currentId++);
        experience.setTitle("Projet SIDoc");
        experience.setMission("Conception / Développement");
        experience
                        .setDetail("Mise en place de l'application <b>WEB de diffusion</b> (qui sera accessible dans les accueils des CAF) qui permet de publier les documents XML produits par l'application WEB de production.");
        try {
                experience.setStartDate(DateUtils.toDate("01/07/2009",
                                DateUtils.FRENCH_PATTERN));
				experience.setEndDate(DateUtils.toDate("21/01/2011",
                                DateUtils.FRENCH_PATTERN));
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
        hobby.setId(ResumeDaoMock.currentId++);
        hobby.setLabel("Sport: football, handball, natation, plongée.");
        hobbies.add(hobby);

        // Hobby 2
        hobby = new Hobby();
        hobby.setId(ResumeDaoMock.currentId++);
        hobby.setLabel("Voyage");
        hobbies.add(hobby);

		// Hobby 2
        hobby = new Hobby();
        hobby.setId(ResumeDaoMock.currentId++);
        hobby.setLabel("Cinéma");
        hobbies.add(hobby);
		
        // Hobby 3
        hobby = new Hobby();
        hobby.setId(ResumeDaoMock.currentId++);
        hobby.setLabel("Veille technologique");
        hobbies.add(hobby);
}
}
