package org.dynaresume.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dynaresume.dao.ResumeDao;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.services.ResumeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ResumeServiceImpl implements ResumeService {

	private ResumeDao resumeDao;

	public void setResumeDao(ResumeDao resumeDao) {
		this.resumeDao = resumeDao;
	}

	public Collection<Resume> findAll() {
		// TODO Auto-generated method stub
		return resumeDao.findAll();
	}

	public Resume findById(long id) {
		// TODO Auto-generated method stub
		return resumeDao.findById(id);
	}

	public Resume save(Resume resume) {
		// TODO Auto-generated method stub
		return resumeDao.save(resume);
	}

	public Page<Resume> findByFirstNameAndLastName(String firstName,
			String lastName, Pageable pageable) {
		// TODO : manage pagination with the DAO
		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();
		Collection<Resume> allResumes = findAll();		
		List<Resume> fullList = new ArrayList<Resume>(allResumes);
		List<Resume> filteredList = new ArrayList<Resume>();
		for (Resume resume : fullList) {
			if (isPersonOK(resume, firstName, lastName)) {
				filteredList.add(resume);
			}
		}
		long totalSize = filteredList.size();
		List<Resume> paginatedList = new ArrayList<Resume>();
		for (int i = pageIndex; i < pageIndex + pageSize && i < totalSize; i++) {
			Resume resume = filteredList.get(i);
			paginatedList.add(resume);
		}
		return new PageImpl<Resume>(paginatedList, pageable, totalSize);
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

	//
	// private static final Map<Long, Resume> resumes;
	// static long currentId = 0;
	// static {
	// resumes = new HashMap<Long, Resume>();
	//
	// // Angelo
	// Resume angeloResume = new AngeloResume();
	// resumes.put(angeloResume.getId(), angeloResume);
	//
	// // Pascal
	// Resume pascalResume = new PascalResume();
	// resumes.put(pascalResume.getId(), pascalResume);
	//
	// // Amine
	// Resume amineResume = new AmineResume();
	// resumes.put(amineResume.getId(), amineResume);
	// }
	//
	// public Collection<Resume> findAll() {
	// return resumes.values();
	// }
	//
	// public Resume findById(long id) {
	// Resume Resume = resumes.get(id);
	// if (Resume != null) {
	// return clone(Resume);
	// }
	// return resumes.get(id);
	// }
	//
	// public Resume save(Resume resume) {
	// if (resume.getId() == null) {
	// resume.setId(currentId++);
	// }
	// resumes.put(resume.getId(), resume);
	// return clone(resume);
	// }
	//
	// private Resume clone(Resume resume) {
	// NaturalPerson person = resume.getOwner();
	// NaturalPerson newPerson = clone(person);
	//
	// Resume newResume = new Resume();
	// newResume.setId(resume.getId());
	// newResume.setOwner(newPerson);
	// newResume.setPicture(resume.getPicture());
	// newResume.setTitle(resume.getTitle());
	//
	// // Educations
	// Set<Education> educations = resume.getEducations();
	// if (educations != null) {
	// Set<Education> newEducations = new HashSet<Education>();
	// for (Education education : educations) {
	// newEducations.add(clone(education));
	// }
	// newResume.setEducations(newEducations);
	// }
	//
	// // Experiences
	// Set<Experience> experiences = resume.getExperiences();
	// if (experiences != null) {
	// Set<Experience> newExperiences = new HashSet<Experience>();
	// for (Experience experience : experiences) {
	// newExperiences.add(clone(experience));
	// }
	// newResume.setExperiences(newExperiences);
	// }
	//
	// // Hobbies
	// Set<Hobby> hobbies = resume.getHobbies();
	// if (hobbies != null) {
	// Set<Hobby> newHobbies = new HashSet<Hobby>();
	// for (Hobby hobby : hobbies) {
	// newHobbies.add(clone(hobby));
	// }
	// newResume.setHobbies(newHobbies);
	// }
	//
	// return newResume;
	// }
	//
	// private NaturalPerson clone(NaturalPerson person) {
	// NaturalPerson newPerson = new NaturalPerson();
	// newPerson.setId(person.getId());
	// newPerson.setLastName(person.getLastName());
	// newPerson.setFirstName(person.getFirstName());
	// newPerson.setBirthDate(person.getBirthDate());
	// newPerson.setEmail(person.getEmail());
	// newPerson.setAddress(clone(person.getAddress()));
	// return newPerson;
	// }
	//
	// private Experience clone(Experience experience) {
	// Experience newExperience = new Experience();
	// Long id = experience.getId();
	// if (id == null) {
	// id = currentId++;
	// }
	// newExperience.setId(id);
	// newExperience.setTitle(experience.getTitle());
	// newExperience.setDetail(experience.getDetail());
	// newExperience.setMission(experience.getMission());
	// newExperience.setEndDate(experience.getEndDate());
	// newExperience.setStartDate(experience.getStartDate());
	// return newExperience;
	// }
	//
	// private Education clone(Education education) {
	// Education newEducation = new Education();
	// Long id = education.getId();
	// if (id == null) {
	// id = currentId++;
	// }
	// newEducation.setId(id);
	// newEducation.setDate(education.getDate());
	// newEducation.setLabel(education.getLabel());
	// newEducation.setInstitute(education.getInstitute());
	// return newEducation;
	// }
	//
	// private Hobby clone(Hobby hobby) {
	// Hobby newHobby = new Hobby();
	// Long id = hobby.getId();
	// if (id == null) {
	// id = currentId++;
	// }
	// newHobby.setId(id);
	// newHobby.setLabel(hobby.getLabel());
	// return newHobby;
	// }
	//
	// private Address clone(Address address) {
	// if (address == null) {
	// return null;
	// }
	// Address newAddress = new Address();
	// Long id = address.getId();
	// if (id == null) {
	// id = currentId++;
	// }
	// newAddress.setId(id);
	// newAddress.setCity(address.getCity());
	// newAddress.setCountry(address.getCountry());
	// newAddress.setFax(address.getFax());
	// newAddress.setStreet(address.getStreet());
	// newAddress.setTelephone(address.getTelephone());
	// newAddress.setZipCode(address.getZipCode());
	// return newAddress;
	// }
}
