package org.dynaresume.services.dosgi;

import static org.junit.Assert.assertEquals;
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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.osgi.framework.BundleContext;

public abstract class AbstractJAXRSOSGiUnitTest {

	protected Option[] cfxdosgi() {

		Option[] options = {
				mavenBundle("org.apache.geronimo.specs","geronimo-annotation_1.0_spec", "1.1.1"),

				mavenBundle("javax.activation","com.springsource.javax.activation", "1.1.1"),

				mavenBundle("javax.mail", "com.springsource.javax.mail","1.4.0"),
				mavenBundle("org.apache.geronimo.specs","geronimo-ws-metadata_2.0_spec", "1.1.2"),
				mavenBundle("org.apache.commons","com.springsource.org.apache.commons.logging", "1.1.1"),
				mavenBundle("org.jdom", "com.springsource.org.jdom").versionAsInProject(),

				mavenBundle("org.aopalliance","com.springsource.org.aopalliance", "1.0.0"),
				mavenBundle("org.springframework", "org.springframework.aop","3.0.6.RELEASE"),
				mavenBundle("org.springframework", "org.springframework.beans","3.0.6.RELEASE"),
				mavenBundle("org.springframework","org.springframework.context", "3.0.6.RELEASE"),
				mavenBundle("org.springframework", "org.springframework.core","3.0.6.RELEASE"),
				mavenBundle("org.springframework","org.springframework.transaction", "3.0.6.RELEASE"),
				mavenBundle("org.springframework", "org.springframework.orm","3.0.6.RELEASE"),
				mavenBundle("org.springframework", "org.springframework.jdbc","3.0.6.RELEASE"),
				mavenBundle("org.springframework", "org.springframework.asm","3.0.6.RELEASE"),
				mavenBundle("org.springframework","org.springframework.expression", "3.0.6.RELEASE"),
				mavenBundle("org.slf4j", "slf4j-api", "1.6.1"),

				//mavenBundle("org.slf4j", "com.springsource.slf4j.jcl", "1.5.10").noStart(),

				mavenBundle("org.ops4j.pax.web", "pax-web-jetty-bundle", "1.1.3"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.wsdl4j", "1.6.1_1"),

				mavenBundle("org.apache.cxf", "cxf-bundle-minimal").version("2.5.2"),

				mavenBundle("org.apache.cxf.dosgi","cxf-dosgi-ri-discovery-local").versionAsInProject(),

				mavenBundle("org.apache.cxf.dosgi", "cxf-dosgi-ri-dsw-cxf").versionAsInProject(),

				mavenBundle("org.apache.cxf.dosgi","cxf-dosgi-ri-topology-manager").versionAsInProject(),
				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.xmlsec").versionAsInProject(),

				mavenBundle("org.apache.ws.xmlschema","xmlschema-core", "2.0.2"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.opensaml", "2.5.1_2"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.asm", "2.2.3_1"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.xmlresolver", "1.2_1"),

				mavenBundle("org.apache.neethi","neethi", "3.0.2"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.woodstox", "3.2.7_1"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.commons-pool", "1.5.4_1"),

				mavenBundle("org.apache.servicemix.specs","org.apache.servicemix.specs.saaj-api-1.3", "1.3.0"),

				mavenBundle("org.apache.servicemix.specs","org.apache.servicemix.specs.stax-api-1.0", "1.3.0"),

				mavenBundle("org.apache.servicemix.specs","org.apache.servicemix.specs.jaxb-api-2.1", "1.3.0"),

				mavenBundle("org.apache.servicemix.specs","org.apache.servicemix.specs.jaxws-api-2.1", "1.3.0"),

				mavenBundle("org.apache.servicemix.specs","org.apache.servicemix.specs.jsr311-api-1.1.1", "1.9.0"),

				mavenBundle("org.apache.servicemix.bundles","org.apache.servicemix.bundles.jaxb-impl", "2.1.6_1"),



		};

		return options;
	}



	protected Option[] infra() {
		Option[] options = {
				systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("DEBUG"),
				CoreOptions.cleanCaches(),
				mavenBundle("org.osgi","org.osgi.compendium","4.2.0"),
				mavenBundle("org.osgi","org.osgi.enterprise","4.2.0"),
				CoreOptions.junitBundles(),
				//CoreOptions.compendiumProfile(),
				// ***************** Gemini dependencies ********************
				mavenBundle("org.codehaus.jackson", "jackson-jaxrs", "1.9.0"),
				mavenBundle("org.codehaus.jackson", "jackson-core-asl", "1.9.0"),
				mavenBundle("org.codehaus.jackson", "jackson-mapper-asl","1.9.0"),

				// ***************** Infra dependencies ********************
				mavenBundle("com.h2database", "h2", "1.3.162"),
				mavenBundle("org.apache.commons","com.springsource.org.apache.commons.dbcp","1.2.2.osgi"),
				mavenBundle("org.apache.commons","com.springsource.org.apache.commons.pool", "1.3.0"),
				// ***************** EclipseLink dependencies ********************
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.antlr").versionAsInProject(),
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.asm").versionAsInProject(),
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.core").versionAsInProject(),
				mavenBundle("org.eclipse.persistence","org.eclipse.persistence.jpa").versionAsInProject(),

		};
		return options;
	}

	protected Option[] brails() {
		Option[] options = {
				// ***************** Gemini blueprint ********************
				mavenBundle("org.springframework.osgi", "spring-osgi-core","1.2.1"),
				mavenBundle("org.springframework.osgi", "spring-osgi-io","1.2.1"),
				mavenBundle("org.springframework.osgi", "spring-osgi-extender","1.2.1").startLevel(5),

				// ***************** Spring data ********************
				mavenBundle("org.springframework.data", "spring-data-jpa")
						.versionAsInProject(),
				mavenBundle("org.springframework.data","spring-data-commons-core").versionAsInProject(),

				// ***************** Required APIs ********************
				mavenBundle("javax.validation","com.springsource.javax.validation").versionAsInProject(),
				mavenBundle("org.eclipse.persistence", "javax.persistence").versionAsInProject(),
				// ***************** DataBase ********************
				mavenBundle("org.apache.derby", "derby").versionAsInProject(),
				mavenBundle("org.apache.commons","com.springsource.org.apache.commons.dbcp").versionAsInProject(),
				mavenBundle("org.apache.commons","com.springsource.org.apache.commons.pool").versionAsInProject(),

				// ***************** XDocReport    ********************
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.datasource").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.dao").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.dao.jpa").versionAsInProject(),
				//eclipselink fragment
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.dao.jpa.eclipselink").versionAsInProject().noStart(),
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.services").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.domain.core").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.domain.project").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.domain.hr").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.services").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.services.impl").versionAsInProject(),
				mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.services.dosgi", "1.0.0-SNAPSHOT"),
		};
		return options;
	}

	protected static final int PORT = 10202;

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
	protected BundleContext ctx;

	@Test
	public void saveAndRetreiveResume() throws Exception {

		assertNotNull(ctx);
		System.out.println("ctx "+ctx);
		WebClient webClient = createWebClient();
		System.out.println("webClient "+webClient);
		assertNotNull(webClient);


		long count= webClient.accept(MediaType.APPLICATION_JSON).path("countAllResume").get(Long.class);
		System.out.println(count);

		Resume resume = new Resume();
		resume.setTitle("Jedi Master");
		webClient = createWebClient();
		Resume resp=webClient.accept(MediaType.APPLICATION_XML).path("saveResume").post(resume, Resume.class);

		 webClient = createWebClient();
		long count2= webClient.accept(MediaType.APPLICATION_JSON).path("countAllResume").get(Long.class);
		assertEquals(count+1, count2);
		System.err.println("mmmmmmmmmmmm "+resp.getId());

		webClient = createWebClient();
		Resume resume2 = webClient.accept(MediaType.APPLICATION_JSON)
				.path("findById/"+resp.getId()).get(Resume.class);
		Assert.assertNotNull(resume2);
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