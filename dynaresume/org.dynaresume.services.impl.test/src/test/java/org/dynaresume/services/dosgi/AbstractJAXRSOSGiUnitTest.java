package org.dynaresume.services.dosgi;

import static org.junit.Assert.assertNotNull;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;

import java.io.IOException;
import java.net.Socket;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.services.rest.ResumeServiceRest;
import org.dynaresume.services.rest.Resumes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.options.ProvisionOption;
import org.osgi.framework.BundleContext;

public abstract class AbstractJAXRSOSGiUnitTest {

	protected Option[] cfxdosgi() {
		@SuppressWarnings("rawtypes")
		ProvisionOption[] options = {
				mavenBundle("org.apache.geronimo.specs",
						"geronimo-annotation_1.0_spec", "1.1.1"),
				// mavenBundle("org.apache.geronimo.specs","geronimo-activation_1.0.2_spec","1.1"),
				// mavenBundle("org.apache.geronimo.javamail","geronimo-javamail-1.4","1.3"),

				mavenBundle("javax.activation",
						"com.springsource.javax.activation", "1.1.1"),

				mavenBundle("javax.mail", "com.springsource.javax.mail",
						"1.4.0"),
				mavenBundle("org.apache.geronimo.specs",
						"geronimo-ws-metadata_2.0_spec", "1.1.2"),
				mavenBundle("org.apache.commons",
						"com.springsource.org.apache.commons.logging", "1.1.1"),
				mavenBundle("org.jdom", "com.springsource.org.jdom", "1.0.0"),

				mavenBundle("org.aopalliance",
						"com.springsource.org.aopalliance", "1.0.0"),
				mavenBundle("org.springframework", "org.springframework.aop",
						"3.0.6.RELEASE"),
				mavenBundle("org.springframework", "org.springframework.beans",
						"3.0.6.RELEASE"),
				mavenBundle("org.springframework",
						"org.springframework.context", "3.0.6.RELEASE"),
				mavenBundle("org.springframework", "org.springframework.core",
						"3.0.6.RELEASE"),
				mavenBundle("org.springframework",
						"org.springframework.transaction", "3.0.6.RELEASE"),
				mavenBundle("org.springframework", "org.springframework.orm",
						"3.0.6.RELEASE"),
				mavenBundle("org.springframework", "org.springframework.jdbc",
						"3.0.6.RELEASE"),
				mavenBundle("org.springframework", "org.springframework.asm",
						"3.0.6.RELEASE"),
				mavenBundle("org.springframework",
						"org.springframework.expression", "3.0.6.RELEASE"),
				mavenBundle("org.slf4j", "com.springsource.slf4j.api", "1.5.10"),
				// bundle("file:multibundle/apache-cxf-dosgi-ri-1.2/dosgi_bundles/com.springsource.slf4j.api-1.5.10.jar"),
				mavenBundle("org.slf4j", "com.springsource.slf4j.jcl", "1.5.10")
						.noStart(),
				//mavenBundle("javax.xml.bind","com.springsource.javax.xml.bind","2.2.0"),
				//mavenBundle("javax.xml.stream","com.springsource.javax.xml.stream","1.0.1"),
				// bundle("file:multibundle/apache-cxf-dosgi-ri-1.2/dosgi_bundles/com.springsource.slf4j.jcl-1.5.10.jar").noStart(),

				// mavenBundle("ch.qos.logback","logback-classic","1.0.0"),
				// bundle("file:multibundle/apache-cxf-dosgi-ri-1.2/dosgi_bundles/spring-osgi-io-1.2.0.jar"),
				// bundle("file:multibundle/apache-cxf-dosgi-ri-1.2/dosgi_bundles/spring-osgi-core-1.2.0.jar"),

				// TODO...
				// bundle("file:multibundle/apache-cxf-dosgi-ri-1.2/dosgi_bundles/pax-web-service-0.5.1.jar"),

				mavenBundle("org.ops4j.pax.web", "pax-web-service", "0.5.1"),

				
				mavenBundle("org.apache.servicemix.bundles",
						"org.apache.servicemix.bundles.wsdl4j", "1.6.1_1"),
				// bundle("file:multibundle/apache-cxf-dosgi-ri-1.2/dosgi_bundles/org.apache.servicemix.bundles.wsdl4j-1.6.1_1.jar"),
				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.xmlsec", "1.3.0_1"),
				
				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.xmlschema", "1.4.3_1"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.asm", "2.2.3_1"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.xmlresolver", "1.2_1"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.neethi", "2.0.4_4"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.woodstox", "3.2.7_1"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.commons-pool", "1.5.4_1"),

				mavenBundle("org.apache.servicemix.specs","org.apache.servicemix.specs.saaj-api-1.3", "1.3.0"),

				mavenBundle("org.apache.servicemix.specs","org.apache.servicemix.specs.stax-api-1.0", "1.3.0"),

				mavenBundle("org.apache.servicemix.specs","org.apache.servicemix.specs.jaxb-api-2.1", "1.3.0"),

				mavenBundle("org.apache.servicemix.specs","org.apache.servicemix.specs.jaxws-api-2.1", "1.3.0"),

				mavenBundle("org.apache.servicemix.specs","org.apache.servicemix.specs.jsr311-api-1.0", "1.3.0"),
		
				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.jaxb-impl", "2.1.6_1"),
		
				mavenBundle("org.apache.cxf", "cxf-bundle-minimal", "2.2.10"),
		
				mavenBundle("org.apache.cxf.dosgi","cxf-dosgi-ri-discovery-local", "1.2"),
		
				mavenBundle("org.apache.cxf.dosgi", "cxf-dosgi-ri-dsw-cxf","1.2"),
		
				mavenBundle("org.apache.cxf.dosgi","cxf-dosgi-ri-topology-manager", "1.2"),
				
		};
		// configureStartupLevel( options);
		return options;
	}

	// protected abstract void configureStartupLevel(ProvisionOption[] options)
	// ;

	protected Option[] infra() {
		Option[] options = {
				systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level")
						.value("WARN"),
				CoreOptions.cleanCaches(),
				
				mavenBundle("org.osgi","org.osgi.compendium","4.2.0"),
				
				
				CoreOptions.junitBundles(),
				CoreOptions.compendiumProfile(),
				// ***************** Gemini dependencies ********************
				mavenBundle("org.codehaus.jackson", "jackson-jaxrs", "1.9.0"),
				mavenBundle("org.codehaus.jackson", "jackson-core-asl", "1.9.0"),
				mavenBundle("org.codehaus.jackson", "jackson-mapper-asl",
						"1.9.0"),

				// ***************** Infra dependencies ********************
				mavenBundle("com.h2database", "h2", "1.3.162"),
				mavenBundle("org.apache.commons",
						"com.springsource.org.apache.commons.dbcp",
						"1.2.2.osgi"),
				mavenBundle("org.apache.commons",
						"com.springsource.org.apache.commons.pool", "1.3.0"),
				// ***************** EclipseLink dependencies
				// ********************
				mavenBundle("org.eclipse.persistence",
						"org.eclipse.persistence.antlr").versionAsInProject(),
				mavenBundle("org.eclipse.persistence",
						"org.eclipse.persistence.asm").versionAsInProject(),
				mavenBundle("org.eclipse.persistence",
						"org.eclipse.persistence.core").versionAsInProject(),
				mavenBundle("org.eclipse.persistence",
						"org.eclipse.persistence.jpa").versionAsInProject(), 
					
		};
		return options;
	}

	protected Option[] brails() {
		Option[] options = {
				// ***************** Gemini blueprint ********************
				mavenBundle("org.springframework.osgi", "spring-osgi-core",
						"1.2.1"),
				mavenBundle("org.springframework.osgi", "spring-osgi-io",
						"1.2.1"),
				mavenBundle("org.springframework.osgi", "spring-osgi-extender",
						"1.2.1").startLevel(5),

				// ***************** Spring data ********************
				mavenBundle("org.springframework.data", "spring-data-jpa")
						.versionAsInProject(),
				mavenBundle("org.springframework.data",
						"spring-data-commons-core").versionAsInProject(),

				// ***************** Required APIs ********************
				mavenBundle("javax.validation",
						"com.springsource.javax.validation")
						.versionAsInProject(),
				mavenBundle("org.eclipse.persistence", "javax.persistence")
						.versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.services").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.domain.core").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.domain.project").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.domain.hr").versionAsInProject(),

				mavenBundle("fr.opensagres.xdocreport-eclipse",
						"org.dynaresume.services.dosgi", "1.0.0-SNAPSHOT"),

		};
		return options;
	}

	protected static final int PORT = 10200;

	private static String createURL(String relativePath) {
		return "http://localhost:" + PORT + relativePath;
	}

	private void waitForPortToBeAvailable(int port) throws Exception {
		waitForFullInitialization();

		for (int i = 0; i < 20; i++) {
			Socket s = null;
			try {
				s = new Socket((String) null, port);
				// yep, its available
				return;
			} catch (IOException e) {
				// wait
			} finally {
				if (s != null) {
					try {
						s.close();
					} catch (IOException e) {
					}
				}
			}
			System.out.println("Waiting for server to appear on port: " + port);
			Thread.sleep(1000);
		}
		throw new java.util.concurrent.TimeoutException();
	}

	protected static boolean initialized = false;

	public static boolean isInitialized() {
		return initialized;
	}

	public static void setInitialized(boolean initialized) {
		AbstractJAXRSOSGiUnitTest.initialized = initialized;
	}

	private void waitForFullInitialization() throws InterruptedException {
		if (!isInitialized()) {
			// I have to "wait" until the OSGi platform is fully initialized...
			// I'm still looking for a clever way of doing this (possibly
			// through listeners).
			Thread.sleep(15000);
			setInitialized(true);
		}
	}

	@Before
	public void setUp() throws Exception {

		waitForPortToBeAvailable(PORT);

		// Make an actual invocation on the remote service.
		cl = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(
				JAXRSClientFactoryBean.class.getClassLoader());
	}

	private ClassLoader cl;

	@After
	public void tearDown() {
		Thread.currentThread().setContextClassLoader(cl);
	}

	@Inject
	private BundleContext ctx;

	@Test
	public void findById() throws Exception {

		assertNotNull(ctx);
		System.out.println("ctx "+ctx);
		WebClient webClient = createWebClient();
		System.out.println("webClient "+webClient);
		assertNotNull(webClient);
		Resume resume = webClient.accept(MediaType.APPLICATION_JSON)
				.path("findById/1").get(Resume.class);
		Assert.assertNotNull(resume);
		
		Assert.assertEquals(1L, resume.getId().longValue());
		// assertResultContainsListOfSize(5,result.data);

	}

	@Test
	public void findAll() throws Exception {

		assertNotNull(ctx);
		System.out.println("ctx "+ctx);
		WebClient webClient = createWebClient();
		System.out.println("webClient "+webClient);
		assertNotNull(webClient);
		
		
		Resumes resumes= webClient.accept(MediaType.APPLICATION_JSON).path("findAll").get(Resumes.class);
		
		Assert.assertNotNull(resumes);
		Assert.assertEquals(1L, resumes.getResumes().size());
		
		//Assert.assertEquals(1L, resume.getId().longValue());
		// assertResultContainsListOfSize(5,result.data);

	}

	private WebClient createWebClient() {

		JAXRSClientFactoryBean factory = new JAXRSClientFactoryBean();
		factory.setAddress(createURL("/resume/resume"));
		factory.setResourceClass(ResumeServiceRest.class);
		JacksonJsonProvider provider = new JacksonJsonProvider();
		factory.setProvider(provider);
		WebClient webClient = factory.createWebClient();
		return webClient;
	}

}