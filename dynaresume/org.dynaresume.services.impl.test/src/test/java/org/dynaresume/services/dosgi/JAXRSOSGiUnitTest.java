package org.dynaresume.services.dosgi;


import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.OptionUtils.combine;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.TimeoutException;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.options.ProvisionOption;
import org.ops4j.pax.exam.spi.PaxExamRuntime;

//starts this class  as an "application" (with a "main").
@RunWith(JUnit4TestRunner.class)
public class JAXRSOSGiUnitTest extends AbstractJAXRSOSGiUnitTest {

	@Configuration
	public  Option[] config() {
		return CoreOptions.options(CoreOptions.cleanCaches(),
				CoreOptions.cleanCaches(),
				// Run this test under Felix.
				CoreOptions.frameworks(CoreOptions.felix()),
		        composite(infra()),
				
				composite(brails()),
				composite(cfxdosgi()),
				//TODO:
				//bundle("file:multibundle/apache-cxf-dosgi-ri-1.2/dosgi_bundles/spring-osgi-extender-1.2.0.jar").startLevel(6),
				systemProperty("org.osgi.framework.startlevel.beginning").value("" + 20),
				

				// For debugging...
				// opts.add(PaxRunnerOptions.vmOption(
				// "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=6006" ));
				// opts.add(CoreOptions.waitForFrameworkStartup());
				// end debugging section.
				 // end debugging section.
				
		        workingDirectory("tmp/felix"));
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
//
//	@Override
//	protected void configureStartupLevel(ProvisionOption[] options) {
//		
//		
//	}

}
