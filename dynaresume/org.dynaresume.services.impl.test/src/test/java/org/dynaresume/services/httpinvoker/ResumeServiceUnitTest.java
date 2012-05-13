package org.dynaresume.services.httpinvoker;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.composite;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.profile;
import static org.ops4j.pax.exam.OptionUtils.combine;

import java.io.IOException;

import javax.inject.Inject;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.services.ResumeService;
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
public class ResumeServiceUnitTest extends AbstractOSGiUnitTest {

	private static final int timeout = 30000;


	@Configuration()
	public Option[] config() {


		return CoreOptions.options(
				//mavenBundle("org.osgi","org.osgi.core").version("4.2.0"),
				//CoreOptions.composite(xdocreportCommonBundles()),
				CoreOptions.localRepository(System.getProperty("maven.repo.local")),
				CoreOptions.repositories("http://repository.springsource.com/maven/bundles/external","http://repo1.maven.org/maven2/","http://download.eclipse.org/rt/eclipselink/maven.repo","http://repository-opensagres.forge.cloudbees.com/snapshot/","http://repository.springsource.com/maven/bundles/release"),
				
				// ***************** EclipseLink dependencies
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.antlr").versionAsInProject(),
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.asm").versionAsInProject(),
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.core").versionAsInProject(),
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.jpa").versionAsInProject(),
				//eclipselink fragment
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.dao.jpa.eclipselink").versionAsInProject().noStart(),
				composite(commonOptions()),
				composite(xdocreportCommonBundles()),
				composite(xdocreportServiceBundles())
				//sniff : gemini Web does not seems to work properly..
				//composite(geminiWebOptions())
				)
;
	}

	public static void main(String[] args) throws TimeoutException, IOException {
		PaxExamRuntime
				.createContainer(
						PaxExamRuntime.createTestSystem(combine(
								new ResumeServiceUnitTest().config(),profile("gogo")))).start();
	}

	@Inject
	BundleContext ctx;

	@Test
	public void findResumeService() throws InterruptedException {
		Thread.sleep(10);
		assertThat(ctx, is(notNullValue()));
		System.out.println("BundleContext of bundle injected: "
				+ ctx.getBundle().getSymbolicName());

		ServiceTracker tracker = new ServiceTracker(ctx,
				ResumeService.class.getName(), null);
		tracker.open();
		ResumeService resumeService =(ResumeService) tracker.waitForService(timeout);

		tracker.close();
		assertNotNull(resumeService);
		assertEquals(0, resumeService.count());
		//assertThat(resumeService.count(), is(equalTo(0)));
		Resume resume = new Resume();
		resume.setTitle("Jedi Master");
		resumeService.save(resume);
		assertEquals(1, resumeService.count());
	}

}
