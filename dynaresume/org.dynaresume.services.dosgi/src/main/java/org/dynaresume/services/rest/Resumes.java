package org.dynaresume.services.rest;

import java.util.Collection;
import java.util.HashSet;

import org.dynaresume.domain.hr.Resume;

public class Resumes {

	Collection<Resume> resumes=new HashSet<Resume>();
	
	public Collection<Resume> getResumes() {
		return resumes;
	}
	public void setResumes(Collection<Resume> resumes) {
		this.resumes = resumes;
	}
	public boolean add(Resume o) {
		return resumes.add(o);
	}
	public boolean remove(Resume o) {
		return resumes.remove(o);
	}
	
	
}
