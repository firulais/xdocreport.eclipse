package fr.opensagres.xdocreport.eclipse.demo.resume.services.dao;

import java.util.Collection;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public interface ResumeDao {

	 Collection<Resume> findAll();
	 
	 Resume findById(long id);
	 
	 Resume save(Resume resume);
}
