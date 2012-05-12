package org.dynaresume.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.dynaresume.dao.ResumeDao;
import org.dynaresume.dao.SkillDao;
import org.dynaresume.domain.core.MaritalStatus;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.domain.hr.Skill;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/module-context.xml" })
public class NonOSGiUnitTest {

	@Inject
	private ResumeDao resumeDao;
	@Inject
	private SkillDao skillDao;

	@Ignore
	@Test
	public void validate() {
		assertNotNull(resumeDao);
		assertEquals(0,resumeDao.count());

		Resume resume = new Resume();
		resume.setTitle("test");
		NaturalPerson owner = new NaturalPerson();
		owner.setMaritalStatus(MaritalStatus.SINGLE);

		owner.setFirstName("demo");
		owner.setLastName("demo");
		owner.setEmail("demo@demo.com");
		resume.setOwner(owner);
		Resume other = resumeDao.save(resume);
		System.out.println(other);
		assertEquals(1,resumeDao.count());
		Pageable page = new PageRequest(0,100);
		Page<Resume> resumes=	resumeDao.findByOwnerFirstNameLikeAndOwnerLastNameLike("demo","demo", page);

		assertNotNull(resumes);
		System.out.println(resumes.getContent().size());
		assertEquals(1,resumes.getContent().size());

	}

	@Ignore
	@Test
	public void customeRepo() {

		assertNotNull(skillDao);
		assertEquals(0, skillDao.count());

		Skill jedi = new Skill();
		jedi.setName("Jedi Master");
		skillDao.save(jedi);

		Skill padawan = new Skill();
		padawan.setName("Padawan");
		skillDao.save(padawan);

		assertEquals(2, skillDao.count());

		Pageable pageable = new PageRequest(10, 10);
		Page<Skill> skills = skillDao.findAll(pageable);
		assertNotNull(skills);


		List<String> names = new ArrayList<String>();
		names.add("Java");
		names.add("Padawan");
		Iterable<Skill> skilsFound=	skillDao.findByNames(names);
		//System.out.println(skilsFound);
		for (Skill skill : skilsFound) {
			System.out.println(skill.getName());
		}
	}
}
