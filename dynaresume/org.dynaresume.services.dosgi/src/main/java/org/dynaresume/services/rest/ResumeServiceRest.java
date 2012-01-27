package org.dynaresume.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.services.ResumeService;

@Path("/resume/")
public class ResumeServiceRest {
	
	
	ResumeService resumeService;
	
	@GET
	@Path("findAll")
	@Produces("application/json")
	public Resumes findAll(){
		Iterable<Resume> res= resumeService.findAll();
		Resumes result= new Resumes();
		for (Resume resume : res) {
			result.add(resume);
		}
		
	return result;
	}
	
	@GET
	@Path("countAllResume")
	@Produces("application/json")
	public long countAllResume(){
		System.out.println(resumeService);
		return resumeService.count();
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
	@Path("saveResume")
	@Consumes({"application/json", "application/xml"})
	@Produces({"application/json", "application/xml"})
	public Resume save( Resume resume){
		System.out.println(resumeService);
		System.out.println(resume);
		return resumeService.save(resume);
	}
	public void setResumeService(ResumeService resumeService) {
		this.resumeService = resumeService;
	}
}
