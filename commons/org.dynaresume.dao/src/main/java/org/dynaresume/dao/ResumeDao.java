package org.dynaresume.dao;

import java.util.Collection;

import org.dynaresume.domain.hr.Resume;


public interface ResumeDao {

	 Collection<Resume> findAll();
	 
	 Resume findById(long id);
	 
	 Resume save(Resume resume);
}
