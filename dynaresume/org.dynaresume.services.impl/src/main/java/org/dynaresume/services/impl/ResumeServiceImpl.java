package org.dynaresume.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.dao.ResumeDao;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
//		// TODO : manage pagination with the DAO
//		int pageSize = pageable.getPageSize();
//		int pageIndex = pageable.getOffset();
//		Iterable<Resume> allResumes = findAll();		
//		//List<Resume> fullList = new ArrayList<Resume>(allResumes);
//		List<Resume> filteredList = new ArrayList<Resume>();
//		for (Resume resume : allResumes) {
//			if (isPersonOK(resume, firstName, lastName)) {
//				filteredList.add(resume);
//			}
//		}
//		long totalSize = filteredList.size();
//		List<Resume> paginatedList = new ArrayList<Resume>();
//		for (int i = pageIndex; i < pageIndex + pageSize && i < totalSize; i++) {
//			Resume resume = filteredList.get(i);
//			paginatedList.add(resume);
//		}
//		
//		
//		return new PageImpl<Resume>(paginatedList, pageable, totalSize);
//		
		return resumeDao.findByOwnerFirstNameLikeAndOwnerLastNameLike(firstName, lastName, pageable);
	}

	private boolean isPersonOK(Resume resume, String firstNameCriteria,
			String lastNameCriteria) {
		NaturalPerson person = resume.getOwner();
		if (person == null) {
			return false;
		}
		String firstName = person.getFirstName();
		if (firstNameCriteria != null) {
			if (!(firstName != null && firstName.toUpperCase().startsWith(
					firstNameCriteria.toUpperCase()))) {
				return false;
			}
		}
		String lastName = person.getLastName();
		if (lastNameCriteria != null) {
			if (!(lastName != null && lastName.toUpperCase().startsWith(
					lastNameCriteria.toUpperCase()))) {
				return false;
			}
		}
		return true;

	}

}
