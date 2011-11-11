package org.dynaresume.services;

import java.util.Collection;

import org.dynaresume.domain.hr.Resume;


public interface ResumeService {
	 Collection<Resume> findAll();
	 Resume findById(long id);
	 Resume save(Resume resume);
}
