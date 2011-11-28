package org.dynaresume.services.impl;

import org.dynaresume.dao.ResumeDao;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeDao resumeDao;

	public Iterable<Resume> findAll() {
		return resumeDao.findAll();
	}

	public Resume findById(long id) {
		return resumeDao.findOne(id);
	}

	public Resume save(Resume resume) {
		return resumeDao.save(resume);
	}

	public Page<Resume> findByFirstNameAndLastName(String firstName,
			String lastName, Pageable pageable) {
		return resumeDao.findByOwnerFirstNameLikeAndOwnerLastNameLike(
				firstName, lastName, pageable);
	}

}
