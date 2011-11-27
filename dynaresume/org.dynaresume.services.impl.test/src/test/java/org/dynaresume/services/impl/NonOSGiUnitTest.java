package org.dynaresume.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.dynaresume.dao.ResumeDao;
import org.dynaresume.domain.core.MaritalStatus;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;
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
}
