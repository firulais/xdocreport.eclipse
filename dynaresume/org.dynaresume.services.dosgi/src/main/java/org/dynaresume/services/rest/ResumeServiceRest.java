package org.dynaresume.services.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.dynaresume.domain.hr.Resume;

@Path("/resume/")
public class ResumeServiceRest {
	
	
	@GET
	@Path("test")
	@Produces("text/plain")
	public String hello(){
		return "Hello !!!";
	}
	
	
	@GET
	@Path("findAll")
	@Produces("application/json")
	public Iterable<Resume> findAll(){
		
		List<Resume> resumes=new ArrayList<Resume>();
		resumes.add(new Resume());
		return resumes;
	}
	@GET
	@Path("testOne")
	@Produces("application/json")
	public Resume testOne(){
		Resume result= new Resume();
		result.setId(1);
		result.setTitle("test");
		return result;
	}
	
	Resume findById(long id){
		return new Resume();
	}

	Resume save(Resume resume){
		return resume;
	}
}
