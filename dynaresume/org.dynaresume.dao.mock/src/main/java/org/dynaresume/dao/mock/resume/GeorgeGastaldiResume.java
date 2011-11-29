package org.dynaresume.dao.mock.resume;

import org.dynaresume.dao.mock.SkillsData;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.DefaultLanguageCode;
import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public class GeorgeGastaldiResume extends BaseResume {

	public GeorgeGastaldiResume() {
		super.setId(getCurrentId());
		super.setTitle("JBoss Seam 3 Module Lead (Seam Reports)");
		try {
			// InputStream in =Resume.class
			// .getResourceAsStream("AngeloZERR.jpg");
			// IOUtils.toByteArray(Resume.class
			// .getResourceAsStream("AngeloZERR.jpg"));
			setPicture(IOUtils.toByteArray(Resume.class
					.getResourceAsStream("GeorgeGastaldi.jpg")));
			// super.setPictureAsStream(Resume.class
			// .getResourceAsStream("AngeloZERR.jpg"));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		NaturalPerson person = new NaturalPerson();
		person.setId(getCurrentId());
		person.setFirstName("George");
		person.setLastName("Gastaldi");
		person.setEmail("");
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
		addEducation("Pós - Graduado, Engenharia de Software com UML ",
				"SENAI Blumenau", "01/09/2009", "30/06/2010");
		addEducation("", "Universidade do Estado de Santa Catarina",
				"01/09/2000", "30/06/2005");
		addEducation("Computer Science", "Escola Técnica Tupy", "01/09/1996",
				"30/06/1999");

		// Experiences
		addExperience("JBoss Seam 3 Community Integrator", "", "",
				"01/06/2011", null);
		addExperience("JBoss Seam 3 Module Lead (Seam Reports)", "", "",
				"01/06/2011", null);
		addExperience("Consultor JEE - ELOSOFT IT", "", "", "01/02/2006", null);
		addExperience("Programmer - NeoGrid", "", "", "01/01/2000",
				"01/01/2006");

		// Skills
		// addSkill(SkillsData.functionalSkills, "Gestion documentaire");
		// addSkill(SkillsData.functionalSkills, "Logistique/Transport");
		// addSkill(SkillsData.functionalSkills, "Nucléaire");
		// addSkill(SkillsData.functionalSkills, "Agroalimentaire");
		addSkill(SkillsData.processSkills, "Java Architect and Consultant");
		addSkill(SkillsData.langagesTechnicalSkills, "Java");
		addSkillWithSplit(SkillsData.technologiesTechnicalSkills, "JBoss, SOA");

		// References
		addReference("JBoss Seam 3 Module Lead (Seam Reports)", "01/06/2011",
				null);
		addReference("Committer on ServiceMix http://www.servicemix.org ",
				"01/08/2005", null);
		addReference("SCEA : Sun Certified Enterprise Architect", null, null);
		addReference("SCBCD: Sun Certified Business Component Developer 5", null,
				null);
		addReference("SCWCD: Sun Certified Web Component Developer 5", null, null);
		addReference("SCJP: Sun Certified Java Programmer", null, null);

		// Languages
		addLanguage(DefaultLanguageCode.English);

		// Hobbies
		// addHobby("Sport: Badminton.");
		// addHobby("Musique : pratique la batterie dans un groupe.");
		// addHobby("Projets Open source.");
	}
}