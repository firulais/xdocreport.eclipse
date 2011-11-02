package fr.opensagres.xdocreport.eclipse.demo.resume.services;

import java.util.Collection;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public interface ResumeService {

	Collection<Resume> findAll();
	
	Resume findById(long id);

	Resume save(Resume user);
}
