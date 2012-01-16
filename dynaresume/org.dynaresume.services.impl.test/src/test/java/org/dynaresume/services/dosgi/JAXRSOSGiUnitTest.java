package org.dynaresume.services.dosgi;


import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.profile;
import static org.ops4j.pax.exam.OptionUtils.combine;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.TimeoutException;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.PaxExamRuntime;

//starts this class  as an "application" (with a "main").
@RunWith(JUnit4TestRunner.class)
public class JAXRSOSGiUnitTest {

	@Configuration
	public static Option[] config() {
		return CoreOptions.options(CoreOptions.cleanCaches(),
				CoreOptions.profile("compendium"),
				//CoreOptions.profile("compendium"),
				CoreOptions.mavenBundle().groupId("org.apache.cxf.dosgi")
						.artifactId("cxf-dosgi-ri-singlebundle-distribution")
						.version("1.2"),
						// ***************** Spring dependencies ********************
						mavenBundle().groupId("org.aopalliance")
								.artifactId("com.springsource.org.aopalliance")
								.version("1.0.0"),
						mavenBundle().groupId("org.springframework")
								.artifactId("org.springframework.aop")
								.version("3.0.6.RELEASE"),
						mavenBundle().groupId("org.springframework")
								.artifactId("org.springframework.beans")
								.version("3.0.6.RELEASE"),
						mavenBundle().groupId("org.springframework")
								.artifactId("org.springframework.context")
								.version("3.0.6.RELEASE"),

						mavenBundle().groupId("org.springframework")
								.artifactId("org.springframework.core")
								.version("3.0.6.RELEASE"),
						mavenBundle().groupId("org.springframework")
								.artifactId("org.springframework.transaction")
								.version("3.0.6.RELEASE"),
						mavenBundle().groupId("org.springframework")
								.artifactId("org.springframework.orm")
								.version("3.0.6.RELEASE"),
						mavenBundle().groupId("org.springframework")
								.artifactId("org.springframework.jdbc")
								.version("3.0.6.RELEASE"),

						//
						mavenBundle().groupId("org.springframework")
								.artifactId("org.springframework.asm")
								.version("3.0.6.RELEASE"),
						mavenBundle().groupId("org.springframework")
								.artifactId("org.springframework.expression")
								.version("3.0.6.RELEASE"),

						// ***************** Gemini dependencies ********************
						mavenBundle().groupId("org.eclipse.gemini.blueprint")
								.artifactId("gemini-blueprint-core")
								.version("1.0.0.RELEASE"),

						mavenBundle().groupId("org.eclipse.gemini.blueprint")
								.artifactId("gemini-blueprint-io")
								.version("1.0.0.RELEASE"),

						mavenBundle().groupId("org.eclipse.gemini.blueprint")
								.artifactId("gemini-blueprint-extender")
								.version("1.0.0.RELEASE").startLevel(5),
						
				// The following two bundles start the greeter demo which
				// registers a remote service

				CoreOptions.mavenBundle("org.codehaus.jackson",
						"jackson-jaxrs", "1.9.0"), CoreOptions.mavenBundle(
						"org.codehaus.jackson", "jackson-core-asl", "1.9.0"),
				CoreOptions.mavenBundle("org.codehaus.jackson",
						"jackson-mapper-asl", "1.9.0"));
	}

	public static void main(String[] args) throws TimeoutException, IOException {
		PaxExamRuntime.createContainer(
				PaxExamRuntime.createTestSystem(combine(JAXRSOSGiUnitTest.config(),
						profile("web")))).start();
	}
	
	@Test
	public void test(){
		System.out.println("coucou");
	}

}
