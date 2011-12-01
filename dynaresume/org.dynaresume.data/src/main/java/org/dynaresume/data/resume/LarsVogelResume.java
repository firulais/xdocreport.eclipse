package org.dynaresume.data.resume;

import java.io.IOException;

import org.dynaresume.data.SkillsInjector;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class LarsVogelResume extends AbstractResumeFactory {

	public LarsVogelResume(SkillsInjector skillsInjector) {
		super(skillsInjector);
		// super.setId(getCurrentId());
		super.setTitle("Eclipse and Android Evangelist");

		NaturalPerson person = new NaturalPerson();
		// person.setId(getCurrentId());
		person.setFirstName("Lars");
		person.setLastName("Vogel");
		person.setEmail("lars.vogel@gmail.com");

		// super.setId(getCurrentId());
		super.setOwner(person);

		try {
			setPicture(IOUtils.toByteArray(LarsVogelResume.class
					.getResourceAsStream("LarsVogel.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Skills
		addSkillWithSplit(getSkillsInjector().functionalSkills,
				"Product Management");
		addSkillWithSplit(getSkillsInjector().processSkills,
				"IT solutions in Consumer products, Mill, Chemical and Oil and Gas Industry");
		addSkillWithSplit(
				getSkillsInjector().technologiesTechnicalSkills,
				"Eclipse RCP, Eclipse Plugin Development,OSGi,Android Training and Consulting,Ecipse RCP Training and Consulting ");

		// Diplomas
		addEducation("Economics / Operations Research / IT", "University Kiel",
				null, "30/06/1998");
		addEducation("School", "Abitur", null, "30/06/1992");

		// Languages
		addLanguage(DefaultLanguageCode.English);
		addLanguage(DefaultLanguageCode.German);

		// Experiences
		addExperience("Eclipse and Android Trainer and Consultant", "", "",
				"01/09/2007", null);
		addExperience(
				"Solution Management and Product Management (SAP)",
				"",
				"Solution definition in the area of SCM transportation, Customer development requirement gathering via Six-Sigma approach, Customer Requirements Roll-In, Project Lead, Sales and Pre-sales Support, Development of Eclipse RCP applicationsContent Team lead for 18 team members ",
				"01/10/2005", "31/08/2007");
		addExperience("Hacker (Eclipse.org)", "", "", "01/01/2000",
				"30/09/2005");

		// Hobbies
		addHobby("Eclipse, OSGi, Android");

	}

}
