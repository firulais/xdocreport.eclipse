package org.dynaresume.dao;

import org.dynaresume.domain.hr.Resume;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResumeDao extends PagingAndSortingRepository<Resume, Long> {

}
