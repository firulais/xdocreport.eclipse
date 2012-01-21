package org.dynaresume.services.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;

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
	public Result testOne(){
		Result result= new Result();
		result.setMsg("coucou");
		result.setTest(true);
		return result;
	}
	
	Resume findById(long id){
		return new Resume();
	}

	Resume save(Resume resume){
		return resume;
	}
}
