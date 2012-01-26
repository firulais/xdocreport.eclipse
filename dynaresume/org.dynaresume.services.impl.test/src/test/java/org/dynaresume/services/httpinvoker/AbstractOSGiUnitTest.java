package org.dynaresume.services.httpinvoker;

import static org.ops4j.pax.exam.CoreOptions.cleanCaches;
import static org.ops4j.pax.exam.CoreOptions.equinox;
import static org.ops4j.pax.exam.CoreOptions.felix;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;

import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;

public class AbstractOSGiUnitTest {

	public Option[] commonOptions() {
		
	
		return CoreOptions.options(
			systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("ERROR"),

			cleanCaches(),
			junitBundles(),
			felix(),
			
			mavenBundle("org.apache.geronimo.specs", "geronimo-servlet_3.0_spec").versionAsInProject(),
			
			mavenBundle("org.apache.commons","com.springsource.org.apache.commons.logging").versionAsInProject(),
			// ***************** Spring dependencies ********************
			mavenBundle("org.aopalliance", "com.springsource.org.aopalliance").versionAsInProject(),
			mavenBundle("org.springframework", "spring-aop").versionAsInProject(),
			mavenBundle("org.springframework", "spring-beans").versionAsInProject(),
			mavenBundle("org.springframework", "spring-context").versionAsInProject(),
			mavenBundle("org.springframework", "spring-core").versionAsInProject(),
			mavenBundle("org.springframework", "spring-tx").versionAsInProject(),
			mavenBundle("org.springframework", "spring-orm").versionAsInProject(),
			mavenBundle("org.springframework", "spring-jdbc").versionAsInProject(),
			mavenBundle("org.springframework", "spring-asm").versionAsInProject(),
			mavenBundle("org.springframework", "spring-web").versionAsInProject(),
			mavenBundle("org.springframework", "spring-expression").versionAsInProject(),
			// ***************** Spring data ********************
			mavenBundle("org.springframework.data", "spring-data-jpa").versionAsInProject(),
			mavenBundle("org.springframework.data", "spring-data-commons-core").versionAsInProject(),
			
			// ***************** Required APIs ********************
			mavenBundle("javax.validation", "com.springsource.javax.validation").versionAsInProject(),
			mavenBundle("org.eclipse.persistence", "javax.persistence").versionAsInProject(),
			// ***************** DataBase ********************
			mavenBundle("org.apache.derby", "derby").versionAsInProject(),
			mavenBundle("org.apache.commons","com.springsource.org.apache.commons.dbcp").versionAsInProject(),
			mavenBundle("org.apache.commons","com.springsource.org.apache.commons.pool").versionAsInProject(),
			// ***************** Gemini blueprint ********************
			mavenBundle("org.springframework.osgi", "org.springframework.osgi.core").version("1.2.1"),
			mavenBundle("org.springframework.osgi", "org.springframework.osgi.io").version("1.2.1"),
			mavenBundle("org.springframework.osgi", "org.springframework.osgi.extender").version("1.2.1").startLevel(5)
			 
		);
	}
	public Option[] geminiWebOptions() {
		return CoreOptions.options(
				//shared whith DOSGi
				mavenBundle("org.apache.geronimo.specs","geronimo-annotation_1.0_spec", "1.1.1"),
				mavenBundle("javax.activation","com.springsource.javax.activation", "1.1.1"),
				mavenBundle("javax.mail", "com.springsource.javax.mail","1.4.0"),
				

				//end
				
				mavenBundle("javax.ejb", "com.springsource.javax.ejb").version("3.0.0"),
				
				mavenBundle("javax.xml.rpc", "com.springsource.javax.xml.rpc").version("1.1.0"),
			
				mavenBundle("javax.xml.soap", "com.springsource.javax.xml.soap").version("1.3.0"),
				mavenBundle("org.apache.catalina", "com.springsource.org.apache.catalina").version("7.0.21"),
				mavenBundle("org.apache.catalina", "com.springsource.org.apache.catalina.ha").version("7.0.21"),
				mavenBundle("org.apache.catalina", "com.springsource.org.apache.catalina.tribes").version("7.0.21"),
				mavenBundle("javax.persistence", "com.springsource.javax.persistence").version("1.99.0"),
				
				mavenBundle("javax.xml.ws", "com.springsource.javax.xml.ws").version("2.1.1"),
				
				mavenBundle("javax.xml.stream", "com.springsource.javax.xml.stream").version("1.0.1"),
				mavenBundle("org.apache.coyote", "com.springsource.org.apache.coyote").version("7.0.21").noStart(),
				
				mavenBundle("org.apache.juli", "com.springsource.org.apache.juli.extras").version("7.0.21"),
				mavenBundle("org.apache.tomcat", "com.springsource.org.apache.tomcat.util").version("7.0.21").noStart(),
				
				mavenBundle("org.apache.tomcat", "com.springsource.org.apache.tomcat.api").version("7.0.21"),
				mavenBundle("javax.xml.bind", "com.springsource.javax.xml.bind").version("2.2.0"),
				mavenBundle("org.eclipse.virgo.util", "org.eclipse.virgo.util.parser.manifest").versionAsInProject(),
				mavenBundle("org.eclipse.virgo.util", "org.eclipse.virgo.util.math").versionAsInProject(),
				mavenBundle("org.eclipse.virgo.util", "org.eclipse.virgo.util.common").versionAsInProject(),
			    mavenBundle("org.eclipse.virgo.util", "org.eclipse.virgo.util.osgi").versionAsInProject(),
			    mavenBundle("org.eclipse.virgo.util", "org.eclipse.virgo.util.io").versionAsInProject(),
				mavenBundle("org.eclipse.gemini.web", "org.eclipse.gemini.web.core").version("2.0.1.RELEASE"),
				mavenBundle("org.eclipse.gemini.web", "org.eclipse.gemini.web.extender").version("2.0.1.RELEASE"),
				mavenBundle("org.eclipse.gemini.web", "org.eclipse.gemini.web.tomcat").version("2.0.1.RELEASE")
				
				
				);
	}
			
	public Option[] xdocreportCommonBundles() {
				
			
		return CoreOptions.options (
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.domain.core").versionAsInProject(),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.domain.project").versionAsInProject(),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.domain.hr").versionAsInProject(),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.datasource").versionAsInProject(),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.dao").versionAsInProject(),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.dao.jpa").versionAsInProject()
		);
	}
	
	public Option[] xdocreportServiceBundles() {
		
		
		return CoreOptions.options (
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.services").versionAsInProject(),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.services.impl").versionAsInProject()
			//mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.services.httpexporter").versionAsInProject()
		);
	}
}