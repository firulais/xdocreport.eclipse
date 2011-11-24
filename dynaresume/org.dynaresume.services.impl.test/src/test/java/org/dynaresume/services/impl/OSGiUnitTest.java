/*
 * Copyright (C) 2011 Toni Menzel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dynaresume.services.impl;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.cleanCaches;
import static org.ops4j.pax.exam.CoreOptions.felix;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.profile;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.exam.CoreOptions.wrappedBundle;
import static org.ops4j.pax.exam.OptionUtils.combine;
import static org.ops4j.pax.exam.spi.container.PaxExamRuntime.createContainer;
import static org.ops4j.pax.exam.spi.container.PaxExamRuntime.createTestSystem;

import java.io.IOException;

import javax.sql.DataSource;

import org.dynaresume.dao.ResumeDao;
import org.junit.Ignore;
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

/**
 * This is what's probably most known to Pax Exam 1.x users. You can recognize
 * the "Junit Driver" approach by the @RunWith() annotation at class level.
 * 
 * This overloads JUnit4s default runner so that Pax Exam is in full control of:
 * - the test roaster - the test invokation
 * 
 * So whats the test roaster ? You know methods annotated with @Test annotations
 * from JUnit4 API, right ? This is the standard roaster. Those will appear in
 * your Test Runner when launching this class in your IDE using
 * "Run with JUnit..".
 * 
 * But: You learned (in Lesson 1) that with Pax Exam you might have your tests
 * executed more than once in different Test Container instances. (Remember the
 * TestContainerFactory.parse() returning a list of TestContainers ?)
 * 
 * So wouldn't it be nice to have that reflected in your JUnit Roaster ? Thats
 * what the @RunWith(JUnit4TestRunner.class) does. Pax Exam re-aranges the
 * JUnit4 Roaster and gives you a single entry for each physical test. In this
 * lesson we are using the NativeTestContainer implementation (see the pom.xml),
 * and additional put two OSGi Frameworks to it: Felix and Equinox. You will see
 * each of the @Test methods below twice. Once for each framework.
 * 
 * The @Configuration is a desclarative way of what you did manually in the
 * previous lessons. Now you only return Option[] in any
 * 
 * @Configuration-annotated method and you are set. A probe will be generated
 *                          underneath with every @Test put into it.
 * 
 *                          Important: It might be subtle at first, but it is
 *                          very important to understand that this test class is
 *                          also the class that will end up in your probe. Just
 *                          because you use it to initially kick of the tests
 *                          (see, you press "Run with JUnit" on this class) it
 *                          does not mean the tests will run in the same
 *                          instance of this class. Underneath, the @Tests are
 *                          invoked on a fresh instance of this class insight
 *                          the OSGi Container (which might be a totally
 *                          different JVM). So @Tests should be rather
 *                          side-effect and stateless and aware of package
 *                          visibility inside the OSGi container.
 */

@RunWith(JUnit4TestRunner.class)
/**
 * NEW & Optional:
 * You can annotate your class with the @ExamReactorStrategy to overwrite the default strategy:
 * @ExamReactorStrategy( AllConfinedStagedReactorFactory.class )
 * This is the default setting.
 * It resembles the way Exam 1.x worked: a new TestContainer instance for every test in your probe(s).
 * Depending on the TestContainerFactory you use (pom.xml!) this may be slower than every other strategy.
 * But its probably also the most side-effect free solution.
 * Lets do the math how many test containers are launched (one after another):
 * 2 tests x 2 test containers in pom = 4 launches
 *
 *
 * or
 *
 * @ExamReactorStrategy( EagerSingleStagedReactorFactory.class )
 * This is the other extreme to AllConfinedStagedReactorFactory. It uses one TestContainer (for all of your tests).
 * Important: You will still get of cause two test container instances for every physical container (like Felix + Equinox).
 * Its just that the Felix container will be started once, all your tests are running against it, then it will shutdown.
 * So in this specific example, you will have two test container launches (one for Felix and another for Equinox.
 * This does not change when adding more Tests to this TestCase.
 *
 *
 */
@ExamReactorStrategy(EagerSingleStagedReactorFactory.class)
public class OSGiUnitTest {

	// private static final String SPRING_VERSION = "3.1.0.RC1";
	private static final String SPRING_VERSION = "3.0.6.RELEASE";

	@Configuration()
	public Option[] config() {
		return options(
				systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level")
						.value("INFO"),
				// profile("spring.dm").version("2.0.O"),
				cleanCaches(),
				junitBundles(),
				felix(),
				// equinox(),
				// ***************** Common dependencies ********************
				mavenBundle().groupId("org.aopalliance")
						.artifactId("com.springsource.org.aopalliance")
						.version("1.0.0"),
				mavenBundle().groupId("org.springframework")
						.artifactId("spring-aop").version(SPRING_VERSION),
				mavenBundle().groupId("org.springframework")
						.artifactId("spring-beans").version(SPRING_VERSION),
				mavenBundle().groupId("org.springframework")
						.artifactId("spring-context").version(SPRING_VERSION),

				mavenBundle().groupId("org.springframework")
						.artifactId("spring-core").version(SPRING_VERSION),

				mavenBundle().groupId("org.springframework.data")
						.artifactId("spring-data-jpa").version("1.0.1.RELEASE"),
				mavenBundle().groupId("org.springframework.data")
						.artifactId("spring-data-commons-core")
						.version("1.1.0.RELEASE"),

				// mavenBundle().groupId("javax.el")
				// .artifactId("com.springsource.javax.el")
				// .version("1.0.0"),
				// mavenBundle().groupId("javax.inject")
				// .artifactId("com.springsource.javax.inject")
				// .version("1.0.0"),

				mavenBundle().groupId("org.apache.derby").artifactId("derby")
						.version("10.8.2.2"),

				mavenBundle().groupId("javax.validation")
						.artifactId("com.springsource.javax.validation")
						.version("1.0.0.GA"),

				mavenBundle().groupId("org.springframework")
						.artifactId("spring-tx").version(SPRING_VERSION),
				mavenBundle().groupId("org.springframework")
						.artifactId("spring-orm").version(SPRING_VERSION),
				mavenBundle().groupId("org.springframework")
						.artifactId("spring-jdbc").version(SPRING_VERSION),
				mavenBundle().groupId("org.springframework")
						.artifactId("spring-asm").version(SPRING_VERSION),
				mavenBundle().groupId("org.springframework")
						.artifactId("spring-expression")
						.version(SPRING_VERSION),
				// mavenBundle().groupId("javax.inject")
				// .artifactId("com.springsource.org.atinject.tck")
				// .version("1.0.0"),
				// mavenBundle().groupId("org.junit")
				// .artifactId("com.springsource.org.junit")
				// .version("4.8.2"),

				mavenBundle().groupId("org.eclipse.gemini.blueprint")
						.artifactId("gemini-blueprint-core")
						.version("1.0.0.RELEASE"),

				mavenBundle().groupId("org.eclipse.gemini.blueprint")
						.artifactId("gemini-blueprint-io")
						.version("1.0.0.RELEASE"),

				mavenBundle().groupId("org.eclipse.gemini.blueprint")
						.artifactId("gemini-blueprint-extender")
						.version("1.0.0.RELEASE").startLevel(5),

				// persistence api
				mavenBundle().groupId("javax.persistence")
						.artifactId("com.springsource.javax.persistence")
						.version("2.0.0"),

				mavenBundle().groupId("com.fasterxml")
						.artifactId("com.springsource.com.fasterxml.classmate")
						.version("0.5.4"),
				mavenBundle()
						.groupId("org.apache.commons")
						.artifactId(
								"com.springsource.org.apache.commons.collections")
						.version("3.2.1"),
				mavenBundle().groupId("org.jboss.javassist")
						.artifactId("com.springsource.javassist")
						.version("3.12.1.GA"),

				mavenBundle().groupId("org.apache.ant")
						.artifactId("com.springsource.org.apache.tools.ant")
						.version("1.8.1"),
				mavenBundle().groupId("org.jboss.logging")
						.artifactId("com.springsource.org.jboss.logging")
						.version("3.0.0.GA"),

				mavenBundle().groupId("org.jboss")
						.artifactId("com.springsource.org.jboss.jandex")
						.version("1.0.3.Final"),
				mavenBundle().groupId("javax.xml.stream")
						.artifactId("com.springsource.javax.xml.stream")
						.version("1.0.1"),

				mavenBundle().groupId("org.dom4j")
						.artifactId("com.springsource.org.dom4j")
						.version("1.6.1"),

				mavenBundle().groupId("org.hibernate")
						.artifactId("com.springsource.org.hibernate.ejb")
						.version("4.0.0.CR4").noStart(),

				mavenBundle().groupId("org.hibernate")
						.artifactId("com.springsource.org.hibernate")
						.version("4.0.0.CR4").noStart(),

				mavenBundle().groupId("org.antlr")
						.artifactId("com.springsource.antlr").version("2.7.7"),

				mavenBundle().groupId("org.apache.log4j")
						.artifactId("com.springsource.org.apache.log4j")
						.version("1.2.16"),

				wrappedBundle(
						mavenBundle().groupId("org.jboss.logmanager")
								.artifactId("jboss-logmanager")
								.version("1.2.0.GA")).exports(
						"org.jboss.logmanager;version=\"1.2.0.GA\""),

				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.domain.core", "1.0.0-SNAPSHOT"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.domain.hr", "1.0.0-SNAPSHOT"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.datasource", "1.0.0-SNAPSHOT"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.dao", "1.0.0-SNAPSHOT"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.dao.jpa", "1.0.0-SNAPSHOT"),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.dao.jpa.hibernate", "1.0.0-SNAPSHOT")

		// mavenBundle("fr.opensagres.xdocreport-eclipse",
		// "org.dynaresume.services", "1.0.0-SNAPSHOT"),
		// mavenBundle("fr.opensagres.xdocreport-eclipse",
		// "org.dynaresume.services.impl", "1.0.0-SNAPSHOT")

		);
	}

	public static void main(String[] args) throws TimeoutException, IOException {
		createContainer(
				createTestSystem(combine(new OSGiUnitTest().config(),
						profile("gogo")))).start();
	}

	/**
	 * Just like any other Test in previous lessons, they can receive an
	 * instance of BundleContext plus optional arguments. Because you have Test
	 * Setup (@Configuration method) and Tests (this method) side by side, there
	 * is no point passing additional arguments.
	 * 
	 * @param ctx
	 *            BundleContext injected. Must be first argument, if any.
	 * @throws InterruptedException
	 */
	@Test
	public void findDataSource(BundleContext ctx) throws InterruptedException {
		assertThat(ctx, is(notNullValue()));
		System.out.println("BundleContext of bundle injected: "
				+ ctx.getBundle().getSymbolicName());

		ServiceTracker tracker = new ServiceTracker(ctx,
				DataSource.class.getName(), null);
		tracker.open();
		Object dataSource = tracker.waitForService(1000);

		tracker.close();
		assertNotNull(dataSource);
	}

	@Ignore
	@Test
	public void findResumeDao(BundleContext ctx) throws InterruptedException {
		assertThat(ctx, is(notNullValue()));
		System.out.println("BundleContext of bundle injected: "
				+ ctx.getBundle().getSymbolicName());

		ServiceTracker tracker = new ServiceTracker(ctx,
				ResumeDao.class.getName(), null);
		tracker.open();
		Object resumeDao = tracker.waitForService(2000);

		tracker.close();
		assertNotNull(resumeDao);
	}

}
