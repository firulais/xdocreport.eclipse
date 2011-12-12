package org.dynaresume.services.osgi;

import static org.ops4j.pax.exam.CoreOptions.cleanCaches;
import static org.ops4j.pax.exam.CoreOptions.equinox;
import static org.ops4j.pax.exam.CoreOptions.felix;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;

import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;

public class AbstractOSGiUnitTest {
	static final String GEMINI_BLUEPRINT_VERSION = "1.0.0.RELEASE";
	static final String STRING_DATA_VERSION = "1.0.1.RELEASE";
	static final String SPRING_VERSION = "3.1.0.RC2";

	
	public Option[] commonOptions() {
		
	
		return CoreOptions.options(
			systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("ERROR"),

			cleanCaches(),
			junitBundles(),
			felix(),
			equinox(),

			mavenBundle("org.apache.commons","com.springsource.org.apache.commons.logging", "1.1.1"),
			// ***************** Spring dependencies ********************
			mavenBundle("org.aopalliance", "com.springsource.org.aopalliance","1.0.0"),
			mavenBundle("org.springframework", "spring-aop",SPRING_VERSION),
			mavenBundle("org.springframework", "spring-beans").versionAsInProject(),
			mavenBundle("org.springframework", "spring-context",SPRING_VERSION),
			mavenBundle("org.springframework", "spring-core",SPRING_VERSION),
			mavenBundle("org.springframework", "spring-tx",SPRING_VERSION),
			mavenBundle("org.springframework", "spring-orm",SPRING_VERSION),
			mavenBundle("org.springframework", "spring-jdbc",SPRING_VERSION),
			mavenBundle("org.springframework", "spring-asm",SPRING_VERSION),
			mavenBundle("org.springframework", "spring-expression",SPRING_VERSION),
			// ***************** Spring data ********************
			mavenBundle("org.springframework.data", "spring-data-jpa",STRING_DATA_VERSION),
			mavenBundle("org.springframework.data", "spring-data-commons-core","1.1.0.RELEASE"),
			
			// ***************** Required APIs ********************
			mavenBundle("javax.validation", "com.springsource.javax.validation","1.0.0.GA"),
			mavenBundle("org.eclipse.persistence", "javax.persistence","2.0.3.v201010191057"),
			// ***************** DataBase ********************
			mavenBundle("org.apache.derby", "derby","10.8.2.2"),
			mavenBundle("org.apache.commons","com.springsource.org.apache.commons.dbcp", "1.2.2.osgi"),
			mavenBundle("org.apache.commons","com.springsource.org.apache.commons.pool", "1.5.3"),
			// ***************** Gemini blueprint ********************
			mavenBundle("org.eclipse.gemini.blueprint", "gemini-blueprint-core",GEMINI_BLUEPRINT_VERSION),
			mavenBundle("org.eclipse.gemini.blueprint", "gemini-blueprint-io",GEMINI_BLUEPRINT_VERSION),
			mavenBundle("org.eclipse.gemini.blueprint","gemini-blueprint-extender",GEMINI_BLUEPRINT_VERSION).startLevel(5) );
	}
	
			
			public Option[] xdocreportCommonBundles() {
				
			
				return CoreOptions.options(
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.domain.core", "1.0.0-SNAPSHOT"),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.domain.project", "1.0.0-SNAPSHOT"),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.domain.hr", "1.0.0-SNAPSHOT"),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.datasource", "1.0.0-SNAPSHOT"),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.dao", "1.0.0-SNAPSHOT"),
			mavenBundle("fr.opensagres.xdocreport-eclipse","org.dynaresume.dao.jpa", "1.0.0-SNAPSHOT") );
			}
			
}