package org.dynaresume.services.rest;

import java.util.Collection;

import org.dynaresume.domain.hr.Resume;

public class Resumes {

	Collection<Resume> resumes;
	
	public Collection<Resume> getResumes() {
		return resumes;
	}
	public void setResumes(Collection<Resume> resumes) {
		this.resumes = resumes;
	}
}
