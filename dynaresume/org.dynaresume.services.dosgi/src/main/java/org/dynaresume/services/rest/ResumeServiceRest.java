package org.dynaresume.services.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;

import org.dynaresume.domain.hr.Resume;

@Path("/resume/")
public class ResumeServiceRest {
	
	@GET
	@Path("findAll")
	@Produces("application/json")
	Iterable<Resume> findAll(){
		
		List<Resume> resumes=new ArrayList<Resume>();
		resumes.add(new Resume());
		return resumes;
	}

	Resume findById(long id){
		return new Resume();
	}

	Resume save(Resume resume){
		return resume;
	}
}
