package fr.opensagres.xdocreport.eclipse.demo.resume.services.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.opensagres.xdocreport.eclipse.demo.resume.services.ResumeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/toto-module-context.xml"})
public class TestResume  {

	@Autowired
	private ResumeService resumeService;
	@Test
	public void validate(){
		assertNotNull(resumeService);
	}
}
