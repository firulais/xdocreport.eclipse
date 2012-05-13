package org.dynaresume.services.dosgi;


import static org.ops4j.pax.exam.CoreOptions.composite;
import static org.ops4j.pax.exam.CoreOptions.profile;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.exam.CoreOptions.workingDirectory;
import static org.ops4j.pax.exam.OptionUtils.combine;

import java.io.IOException;

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

//starts this class  as an "application" (with a "main").
@ExamReactorStrategy(EagerSingleStagedReactorFactory.class)
@RunWith(JUnit4TestRunner.class)
public class JAXRSOSGiUnitTest extends AbstractJAXRSOSGiUnitTest {

	@Configuration
	public  Option[] config() {
		return CoreOptions.options(
//				CoreOptions.localRepository(System.getProperty("maven.repo.local")),
//				CoreOptions.repositories("http://repo1.maven.org/maven2/","http://repository.springsource.com/maven/bundles/external","http://www.eclipse.org/downloads/download.php?r=1&nf=1&file=/rt/eclipselink/maven.repo","http://repository-opensagres.forge.cloudbees.com/snapshot/","http://repository.springsource.com/maven/bundles/release"),
//				
				systemProperty("org.osgi.service.http.port").value(Integer.toString(PORT)),
				systemProperty("org.ops4j.pax.web.session.timeout").value(Integer.toString(300)),
				CoreOptions.cleanCaches(),
				// Run this test under Felix.
				CoreOptions.frameworks(CoreOptions.felix()),
		        composite(infra()),

				composite(brails()),
				composite(cfxdosgi()),
				//TODO:
				//bundle("file:multibundle/apache-cxf-dosgi-ri-1.2/dosgi_bundles/spring-osgi-extender-1.2.0.jar").startLevel(6),
				systemProperty("org.osgi.framework.startlevel.beginning").value("" + 100)


				// For debugging...
				// opts.add(PaxRunnerOptions.vmOption(
				// "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=6006" ));
				// opts.add(CoreOptions.waitForFrameworkStartup());
				// end debugging section.
				 // end debugging section.
);
	}

	public static void main(String[] args) throws TimeoutException, IOException {
		PaxExamRuntime.createContainer(
				PaxExamRuntime.createTestSystem(combine(new JAXRSOSGiUnitTest().config(),
						profile("web")))).start();
	}


	@Test
	public void test(){
		System.out.println("coucou");
	}



}
