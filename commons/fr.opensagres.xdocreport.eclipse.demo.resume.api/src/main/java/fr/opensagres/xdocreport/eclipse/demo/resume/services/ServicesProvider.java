package fr.opensagres.xdocreport.eclipse.demo.resume.services;


//import fr.opensagres.xdocreport.eclipse.demo.resume.services.impl.ResumeServiceImpl;

public class ServicesProvider  {

	private static ResumeService resumeService;

	// = new ResumeServiceImpl();

	public static ResumeService getResumeService() {
		
		return resumeService;
	}

	public  void setResumeService(ResumeService resumeService) {
		ServicesProvider.resumeService = resumeService;
	}

}
