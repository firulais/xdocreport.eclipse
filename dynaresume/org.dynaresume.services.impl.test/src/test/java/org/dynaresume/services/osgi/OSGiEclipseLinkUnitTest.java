package org.dynaresume.services.osgi;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.composite;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.profile;
import static org.ops4j.pax.exam.OptionUtils.combine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.dynaresume.dao.AgencyDao;
import org.dynaresume.dao.GroupDao;
import org.dynaresume.dao.LanguageDao;
import org.dynaresume.dao.ResumeDao;
import org.dynaresume.dao.SkillCategoryDao;
import org.dynaresume.dao.SkillDao;
import org.dynaresume.domain.core.Agency;
import org.dynaresume.domain.core.Group;
import org.dynaresume.domain.core.MaritalStatus;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Language;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.hr.SkillCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.TimeoutException;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.PaxExamRuntime;
import org.ops4j.pax.exam.spi.reactors.EagerSingleStagedReactorFactory;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(EagerSingleStagedReactorFactory.class)
public class OSGiEclipseLinkUnitTest extends AbstractOSGiUnitTest {

	private static final int timeout = 30000;
	

	@Configuration()
	public Option[] config() {

		
		return CoreOptions.options(
				
				//CoreOptions.composite(xdocreportCommonBundles()),
				// ***************** EclipseLink dependencies
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.antlr").versionAsInProject(),
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.asm").versionAsInProject(),
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.core").versionAsInProject(),
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.jpa").versionAsInProject(),
				//eclipselink fragment 
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.dao.jpa.eclipselink").versionAsInProject().noStart(),
				composite(commonOptions()),
				composite(xdocreportCommonBundles()))
;
	}

	public static void main(String[] args) throws TimeoutException, IOException {
		PaxExamRuntime
				.createContainer(
						PaxExamRuntime.createTestSystem(combine(
								new OSGiEclipseLinkUnitTest().config(),
								profile("gogo")))).start();
	}

	@Inject
	BundleContext ctx;
	
	@Test
	public void findDataSource() throws InterruptedException {
		Thread.sleep(10);
		assertThat(ctx, is(notNullValue()));
		System.out.println("BundleContext of bundle injected: "
				+ ctx.getBundle().getSymbolicName());

		ServiceTracker tracker = new ServiceTracker(ctx,
				DataSource.class.getName(), null);
		tracker.open();
		DataSource dataSource =(DataSource) tracker.waitForService(timeout);

		tracker.close();
		assertNotNull(dataSource);
	}

	@Test
	public void findResumeDao() throws InterruptedException {
		
		assertThat(ctx, is(notNullValue()));
		System.out.println("BundleContext of bundle injected: "
				+ ctx.getBundle().getSymbolicName());

		ServiceTracker tracker = new ServiceTracker(ctx,
				ResumeDao.class.getName(), null);
		tracker.open();
		ResumeDao resumeDao = (ResumeDao) tracker.waitForService(timeout);

		tracker.close();
		assertNotNull(resumeDao);
		assertEquals(0, resumeDao.count());

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
		assertEquals(1, resumeDao.count());
		Pageable page = new PageRequest(0, 100);
		Page<Resume> resumes = resumeDao
				.findByOwnerFirstNameLikeAndOwnerLastNameLike("demo", "demo",
						page);

		assertNotNull(resumes);
		System.out.println(resumes.getContent().size());
		assertEquals(1, resumes.getContent().size());

	}

	@Test
	public void testSkillDao() throws InterruptedException {
		
		assertThat(ctx, is(notNullValue()));

		ServiceTracker tracker = new ServiceTracker(ctx,
				SkillDao.class.getName(), null);
		tracker.open();
		SkillDao skillDao = (SkillDao) tracker.waitForService(timeout);

		tracker.close();
		assertNotNull(skillDao);
		assertEquals(0, skillDao.count());

		Skill entity = new Skill();
		entity.setName("Jedi Master");
		skillDao.save(entity);

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
		Iterable<Skill> skilsFound = skillDao.findByNames(names);
		// System.out.println(skilsFound);

		for (Skill skill : skilsFound) {
			assertEquals("Padawan", skill.getName());
		}

	}

	@Test
	public void testGroupDao() throws InterruptedException {
		
		assertThat(ctx, is(notNullValue()));

		ServiceTracker groupTracker = new ServiceTracker(ctx,
				GroupDao.class.getName(), null);
		groupTracker.open();
		GroupDao groupDao = (GroupDao) groupTracker.waitForService(timeout);

		groupTracker.close();
		assertNotNull(groupDao);
		assertEquals(0, groupDao.count());

		Group aGroup = new Group();
		aGroup.setName("demo");
		groupDao.save(aGroup);
		assertEquals(1, groupDao.count());
		Pageable page = new PageRequest(0, 100);
		Page<Group> groups = groupDao.findByNameLike("demo", page);
		assertEquals(1, groups.getContent().size());
		ServiceTracker agencyTracker = new ServiceTracker(ctx,
				AgencyDao.class.getName(), null);
		agencyTracker.open();
		AgencyDao agencyDao = (AgencyDao) agencyTracker.waitForService(timeout);

		agencyTracker.close();
		assertNotNull(agencyDao);
		assertEquals(0, agencyDao.count());
		Agency entity = new Agency();
		entity.setName("Opensagres, New-York");
		entity.setGroup(aGroup);
		agencyDao.save(entity);

		assertEquals(1, agencyDao.count());
	}

	@Test
	public void testSkillCategoryDao()
			throws InterruptedException {
		

		ServiceTracker skillCategoryDaoTracker = new ServiceTracker(ctx,
				SkillCategoryDao.class.getName(), null);
		skillCategoryDaoTracker.open();
		SkillCategoryDao skillCategoryDao = (SkillCategoryDao) skillCategoryDaoTracker
				.waitForService(timeout);

		skillCategoryDaoTracker.close();
		assertNotNull(skillCategoryDao);
		assertEquals(0, skillCategoryDao.count());

		SkillCategory skillCategory = new SkillCategory();
		skillCategory.setLabel("demo");
		skillCategoryDao.save(skillCategory);
		assertEquals(1, skillCategoryDao.count());

	}

	@Test
	public void testLanguageDao() throws InterruptedException {
		
		ServiceTracker tracker = new ServiceTracker(ctx,
				LanguageDao.class.getName(), null);
		tracker.open();
		LanguageDao languageDao = (LanguageDao) tracker.waitForService(timeout);

		tracker.close();
		assertNotNull(languageDao);
		assertEquals(0, languageDao.count());

		Language language = new Language();
		language.setLabel("demo");
		languageDao.save(language);
		assertEquals(1, languageDao.count());
	}
}
