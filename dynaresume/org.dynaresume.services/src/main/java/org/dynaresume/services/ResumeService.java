package org.dynaresume.services;

import org.dynaresume.domain.hr.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResumeService {
	Iterable<Resume> findAll();

	Resume findById(long id);

	Resume save(Resume resume);
	
	long count();

	Page<Resume> findByFirstNameAndLastName(String firstName,
			String lastName, Pageable pageable);
}
