package org.dynaresume.data.resume;

import org.dynaresume.data.SkillsInjector;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class TomSchindlResume extends AbstractResumeFactory {

	public TomSchindlResume(SkillsInjector skillsInjector) {
		super(skillsInjector);
		// super.setId(getCurrentId());
		super.setTitle("Owner, BestSolution Systemhaus Gmbh and Information Technology and Services Consultant");
		try {
			setPicture(IOUtils.toByteArray(TomSchindlResume.class
					.getResourceAsStream("TomSchindl.jpeg")));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		NaturalPerson person = new NaturalPerson();
		// person.setId(getCurrentId());
		person.setFirstName("Tom");
		person.setLastName("Schindl");
		person.setEmail("tom.schindl@bestsolution.at");
		// try {
		// person.setBirthDate(DateUtils.toDate("24/02/1977",
		// DateUtils.FRENCH_PATTERN));
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }
		super.setOwner(person);

		// Address
		// Address address = new Address();
		// address.setId(getCurrentId());
		// person.setAddress(address);

		// address.setStreet("5 avenue Frederic Mistral");
		// address.setCity("Saint Paul 3 Châteaux");
		// address.setZipCode("26130");

		// Educations
		addEducation("Adolf-Pichler-Platz", "", "01/09/1989", "30/06/1997");

		// Experiences
		addExperience("BestSolution Systemhaus Gmbh", "",
				"- Designing Software in Java (Eclipse-RCP, J2EE, ...)"
						+ "- Contributing to OS-Software ", "01/04/2002", null);
		addExperience("IMP/Profile", "Software Developer", "", "01/02/1999",
				"01/04/2003");

		// Skills
		// addSkill(getSkillsInjector().functionalSkills,
		// "Gestion documentaire");
		// addSkill(getSkillsInjector().functionalSkills,
		// "Logistique/Transport");
		// addSkill(getSkillsInjector().functionalSkills, "Nucléaire");
		// addSkill(getSkillsInjector().functionalSkills, "Agroalimentaire");
		addSkill(getSkillsInjector().processSkills,
				"Eclipse-Platform Committer");
		addSkill(getSkillsInjector().langagesTechnicalSkills, "Java");
		addSkillWithSplit(getSkillsInjector().technologiesTechnicalSkills,
				"OSGi, SWT, JFace, Eclipse RCP, EJB3, JBoss");

		// References
		addReference("Eclipse Platform-UI Committer", "01/01/2007", null);
		addReference("Eclipse JFace Commiter", "01/03/2007", null);

		// Languages
		addLanguage(DefaultLanguageCode.English);
		addLanguage(DefaultLanguageCode.German);

		// Hobbies
		// addHobby("Sport: Badminton.");
		// addHobby("Musique : pratique la batterie dans un groupe.");
		// addHobby("Projets Open source.");
	}
}