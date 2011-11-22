package org.dynaresume.dao;

import org.dynaresume.domain.hr.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeDao extends CrudRepository<Resume, Long> {

}
