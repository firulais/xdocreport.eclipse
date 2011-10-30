package fr.opensagres.xdocreport.eclipse.demo.resume.services;

import java.util.HashSet;
import java.util.Set;

import fr.opensagres.xdocreport.document.images.ClassPathImageProvider;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Diploma;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Experience;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Hobby;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public class AngeloResume extends Resume {

	public AngeloResume() {
		// Angelo
		NaturalPerson person = new NaturalPerson();
		person.setId(ResumeServiceImpl.currentId++);
		person.setFirstName("Angelo");
		person.setLastName("ZERR");
		person.setEmail("angelo.zerr@gmail.com");
		
		super.setId(ResumeServiceImpl.currentId++);
		super.setOwner(person);
		super.setPhoto(new ClassPathImageProvider(Resume.class,
				"AngeloZERR.jpg"));

		// Diplomas
		Set<Diploma> diplomas = new HashSet<Diploma>();
		super.setDiplomas(diplomas);

		Diploma diploma = null;

		diploma = new Diploma();
		diploma.setId(ResumeServiceImpl.currentId++);
		diploma.setLabel("Diplôme d'ingénieur en informatique");
		diploma.setInstitute("INSA de Lyon");
		diplomas.add(diploma);

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
				.setDetail("Mise en place de l'application WEB de diffusion (qui sera accessible dans les accueils des CAF) qui permet de publier les documents XML produits par l'application WEB de production.");
		experiences.add(experience);

		// Experience 2
		experience = new Experience();
		experience.setId(ResumeServiceImpl.currentId++);
		experience.setTitle("ERP AgroV3");
		experience.setMission("Conception / Développement");
		experience
				.setDetail("Conception et développement de fonctionnalités dans le  module VENTES/ACHATS et COMPTABILITE de l'ERP agrolimentaire AgroV3 de INFOLOGIC. Cet ERP est basé sur les technologies d'Eclipse SWT et JFace.");
		experiences.add(experience);
		
		// Hobbies
		Set<Hobby> hobbies =new HashSet<Hobby>();
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
