package org.dynaresume.dao;

import org.dynaresume.domain.hr.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeDao extends CrudRepository<Resume, Long> {

//	Collection<Resume> findAll();
//
//	Resume findById(long id);
//
//	Resume save(Resume resume);
}
