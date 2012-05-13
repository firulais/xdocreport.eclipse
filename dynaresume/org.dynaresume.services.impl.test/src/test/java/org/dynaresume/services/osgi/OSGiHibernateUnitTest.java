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
package org.dynaresume.services.osgi;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.composite;
import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.CoreOptions.profile;
import static org.ops4j.pax.exam.CoreOptions.wrappedBundle;
import static org.ops4j.pax.exam.OptionUtils.combine;

import java.io.IOException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.dynaresume.dao.ResumeDao;
import org.junit.Ignore;
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

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(EagerSingleStagedReactorFactory.class)
public class OSGiHibernateUnitTest extends AbstractOSGiUnitTest {


	@Configuration()
	public Option[] config() {


		return CoreOptions.options(
				CoreOptions.localRepository(System.getProperty("maven.repo.local")),
				CoreOptions.repositories("http://repository.springsource.com/maven/bundles/external","http://repo1.maven.org/maven2/","http://download.eclipse.org/rt/eclipselink/maven.repo","http://repository-opensagres.forge.cloudbees.com/snapshot/","http://repository.springsource.com/maven/bundles/release"),
				
				// ***************** Hibernate OSGi dependencies
				// ********************
				mavenBundle("javax.persistence","com.springsource.javax.persistence","2.0.0"),
				mavenBundle("com.fasterxml","com.springsource.com.fasterxml.classmate","0.5.4"),
				mavenBundle("org.apache.commons","com.springsource.org.apache.commons.collections","3.2.1"),
				mavenBundle("org.jboss.javassist","com.springsource.javassist","3.12.1.GA"),
				mavenBundle("org.apache.ant","com.springsource.org.apache.tools.ant","1.8.1"),
				mavenBundle("org.jboss.logging","com.springsource.org.jboss.logging","3.0.0.GA"),
				mavenBundle("org.jboss","com.springsource.org.jboss.jandex","1.0.3.Final"),
				//mavenBundle("javax.xml.stream","com.springsource.javax.xml.stream","1.0.1"),
				mavenBundle("org.dom4j","com.springsource.org.dom4j","1.6.1"),
				mavenBundle("org.hibernate","com.springsource.org.hibernate.ejb","4.0.0.CR4").noStart(),
				mavenBundle("org.hibernate","com.springsource.org.hibernate","4.0.0.CR4").noStart(),
				mavenBundle("org.antlr","com.springsource.antlr","2.7.7"),
				mavenBundle("org.apache.log4j","com.springsource.org.apache.log4j","1.2.16"),

				wrappedBundle(maven("org.jboss.logmanager","jboss-logmanager","1.2.0.GA")).exports("org.jboss.logmanager;version=\"1.2.0.GA\""),
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.dao.jpa.hibernate").versionAsInProject().noStart(),
				composite(commonOptions()),
				composite(xdocreportCommonBundles())
		);
	}

	public static void main(String[] args) throws TimeoutException, IOException {
		PaxExamRuntime.createContainer(
				PaxExamRuntime.createTestSystem(combine(new OSGiHibernateUnitTest().config(),
						profile("gogo")))).start();
	}

	@Inject
	BundleContext ctx;

	@Test
	public void findDataSource() throws InterruptedException {

		assertThat(ctx, is(notNullValue()));
		System.out.println("BundleContext of bundle injected: "
				+ ctx.getBundle().getSymbolicName());

		ServiceTracker tracker = new ServiceTracker(ctx,
				DataSource.class.getName(), null);
		tracker.open();
		Object dataSource = tracker.waitForService(60000);

		tracker.close();
		assertNotNull(dataSource);
	}

	@Ignore("Temporary ignore the test until I can find a usable hibernate bundle")
	@Test
	public void findResumeDao() throws InterruptedException {
		assertThat(ctx, is(notNullValue()));
		System.out.println("BundleContext of bundle injected: "
				+ ctx.getBundle().getSymbolicName());

		ServiceTracker tracker = new ServiceTracker(ctx,
				ResumeDao.class.getName(), null);
		tracker.open();
		Object resumeDao = tracker.waitForService(60000);

		tracker.close();
		assertNotNull(resumeDao);
	}

}
