package org.dynaresume.services.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.dynaresume.services.ResumeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/META-INF/spring/module-context.xml"})
public class TestResume  {

	@Autowired
	private ResumeService resumeService;
	@Test
	public void validate(){
		assertNotNull(resumeService);
	}
}
