package org.dynaresume.services.impl;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.cleanCaches;
import static org.ops4j.pax.exam.CoreOptions.equinox;
import static org.ops4j.pax.exam.CoreOptions.felix;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.profile;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.exam.OptionUtils.combine;
import static org.ops4j.pax.exam.spi.container.PaxExamRuntime.createContainer;
import static org.ops4j.pax.exam.spi.container.PaxExamRuntime.createTestSystem;

import java.io.IOException;

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
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.TimeoutException;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.EagerSingleStagedReactorFactory;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(EagerSingleStagedReactorFactory.class)
public class OSGiEclipseLinkUnitTest {

	private static final int timeout = 30000;
	private static final String GEMINI_BLUEPRINT_VERSION = "1.0.0.RELEASE";
	private static final String STRING_DATA_VERSION = "1.0.1.RELEASE";
	private static final String SPRING_VERSION = "3.1.0.RC2";


	

	@Configuration()
	public Option[] config() {
		return options(
				systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level")
						.value("INFO"),
				// profile("spring.dm").version("2.0.O"),
				cleanCaches(),
				junitBundles(),
				// knopflerfish(),
				felix(),
				equinox(),

						mavenBundle("org.apache.commons","com.springsource.org.apache.commons.logging", "1.1.1"),
				// ***************** Common dependencies ********************
						// ***************** Common dependencies ********************
						mavenBundle("org.aopalliance","com.springsource.org.aopalliance").version("1.0.0"),
						mavenBundle("org.springframework","spring-aop").version(SPRING_VERSION),
						mavenBundle("org.springframework","spring-beans").version(SPRING_VERSION),
						mavenBundle("org.springframework","spring-context").version(SPRING_VERSION),
						mavenBundle("org.springframework","spring-core").version(SPRING_VERSION),
						mavenBundle("org.springframework","spring-tx").version(SPRING_VERSION),
						mavenBundle("org.springframework","spring-orm").version(SPRING_VERSION),
						mavenBundle("org.springframework","spring-jdbc").version(SPRING_VERSION),
						mavenBundle("org.springframework","spring-asm").version(SPRING_VERSION),
						mavenBundle("org.springframework","spring-expression").version(SPRING_VERSION),
						
						mavenBundle("org.springframework.data","spring-data-jpa").version(STRING_DATA_VERSION),
						mavenBundle("org.springframework.data","spring-data-commons-core").version("1.1.0.RELEASE"),
						mavenBundle("org.apache.derby","derby").version("10.8.2.2"),
						mavenBundle("javax.validation","com.springsource.javax.validation").version("1.0.0.GA"),
						
						mavenBundle("org.eclipse.gemini.blueprint","gemini-blueprint-core").version(GEMINI_BLUEPRINT_VERSION),
						mavenBundle("org.eclipse.gemini.blueprint","gemini-blueprint-io").version(GEMINI_BLUEPRINT_VERSION),
						mavenBundle("org.eclipse.gemini.blueprint","gemini-blueprint-extender").version(GEMINI_BLUEPRINT_VERSION).startLevel(5),

				// ***************** EclipseLink dependencies
				// ********************
				mavenBundle("org.eclipse.persistence", "javax.persistence",
						"2.0.3.v201010191057"),
				mavenBundle("org.eclipse.persistence",
						"org.eclipse.persistence.antlr", "2.3.0"),
				mavenBundle("org.eclipse.persistence",
						"org.eclipse.persistence.asm", "2.3.0"),
				mavenBundle("org.eclipse.persistence",
						"org.eclipse.persistence.core", "2.3.0"),
				mavenBundle("org.eclipse.persistence",
						"org.eclipse.persistence.jpa", "2.3.0"),

				mavenBundle("org.apache.commons",
						"com.springsource.org.apache.commons.dbcp",
						"1.2.2.osgi"),
				mavenBundle("org.apache.commons",
						"com.springsource.org.apache.commons.pool", "1.3.0"),
				mavenBundle("org.apache.commons",
						"com.springsource.org.apache.commons.pool", "1.5.3"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.domain.core", "1.0.0-SNAPSHOT"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
							"org.dynaresume.domain.project", "1.0.0-SNAPSHOT"),		
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.domain.hr", "1.0.0-SNAPSHOT"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.datasource", "1.0.0-SNAPSHOT"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.dao", "1.0.0-SNAPSHOT"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.dao.jpa", "1.0.0-SNAPSHOT"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.dao.jpa.eclipselink", "1.0.0-SNAPSHOT")
						.noStart()

		// mavenBundle("fr.opensagres.xdocreport-eclipse",
		// "org.dynaresume.services", "1.0.0-SNAPSHOT"),
		// mavenBundle("fr.opensagres.xdocreport-eclipse",
		// "org.dynaresume.services.impl", "1.0.0-SNAPSHOT")

		);
	}

	public static void main(String[] args) throws TimeoutException, IOException {
		createContainer(
				createTestSystem(combine(
						new OSGiEclipseLinkUnitTest().config(), profile("gogo"))))
				.start();
	}

	@Test
	public void findDataSource(BundleContext ctx) throws InterruptedException {
		Thread.sleep(2000);
		assertThat(ctx, is(notNullValue()));
		System.out.println("BundleContext of bundle injected: "
				+ ctx.getBundle().getSymbolicName());

		ServiceTracker tracker = new ServiceTracker(ctx,
				DataSource.class.getName(), null);
		tracker.open();
		Object dataSource = tracker.waitForService(timeout);

		tracker.close();
		assertNotNull(dataSource);
	}

	@Test
	public void findResumeDao(BundleContext ctx) throws InterruptedException {
		Thread.sleep(2000);
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
	public void testSkillDao(BundleContext ctx) throws InterruptedException {
		Thread.sleep(2000);
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
		assertEquals(1, skillDao.count());

	}

	@Test
	public void testGroupDao(BundleContext ctx) throws InterruptedException {
		Thread.sleep(2000);
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
	public void testSkillCategoryDao(BundleContext ctx)
			throws InterruptedException {
		Thread.sleep(2000);

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
	public void testLanguageDao(BundleContext ctx) throws InterruptedException {
		Thread.sleep(2000);
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
