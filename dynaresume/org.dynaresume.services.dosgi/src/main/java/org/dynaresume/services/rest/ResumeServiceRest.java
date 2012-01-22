package org.dynaresume.services.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.dynaresume.domain.hr.Resume;

@Path("/resume/")
public class ResumeServiceRest {
	
	@GET
	@Path("findAll")
	@Produces("application/json")
	public Iterable<Resume> findAll(){
		
		List<Resume> resumes=new ArrayList<Resume>();
		resumes.add(new Resume());
		return resumes;
	}
	
	
	@GET
	@Path("findById/{resumeId}")
	@Produces("application/json")
	public Resume findById(@PathParam("resumeId") long resumeId){
		Resume result= new Resume();
		result.setId(resumeId);
		result.setTitle("test");
		return result;
	}

	@POST
	@Consumes("application/json")
	public Resume save(Resume resume){
		return resume;
	}
}
