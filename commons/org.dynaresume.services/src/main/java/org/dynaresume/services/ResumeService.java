package org.dynaresume.services;

import java.util.Collection;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public interface ResumeService {
	 Collection<Resume> findAll();
	 Resume findById(long id);
	 Resume save(Resume resume);
}
