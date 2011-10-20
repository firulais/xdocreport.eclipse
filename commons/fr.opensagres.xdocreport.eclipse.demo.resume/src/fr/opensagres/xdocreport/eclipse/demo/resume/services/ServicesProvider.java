package fr.opensagres.xdocreport.eclipse.demo.resume.services;

public class ServicesProvider {

	private static final ResumeService RESUME_SERVICE = new ResumeServiceImpl();

	public static ResumeService getResumeService() {
		return RESUME_SERVICE;
	}
}
