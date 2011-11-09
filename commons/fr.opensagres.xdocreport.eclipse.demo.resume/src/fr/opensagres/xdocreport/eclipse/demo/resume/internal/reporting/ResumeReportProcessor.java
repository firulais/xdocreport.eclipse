package fr.opensagres.xdocreport.eclipse.demo.resume.internal.reporting;

import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.XDocReportProcessor;
import fr.opensagres.xdocreport.template.IContext;

public class ResumeReportProcessor extends XDocReportProcessor {

	@Override
	public void populateContext(IContext context, Object model) {
		Resume resume = (Resume)model;
		context.put("resume", resume);
		context.put("person", resume.getOwner());
		context.put("experiences", resume.getExperiences());
		context.put("educations", resume.getEducations());
		context.put("hobbies", resume.getHobbies());
		context.put("photo", new ByteArrayImageProvider(resume.getPicture()));
	}
}
