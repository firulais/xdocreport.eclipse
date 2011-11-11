package org.dynaresume.eclipse.reporting;

import java.io.IOException;

import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core.XDocReportProcessor;
import fr.opensagres.xdocreport.template.IContext;

public class ResumeReportProcessor extends XDocReportProcessor {

	@Override
	public void populateContext(IContext context, Object model) {
		Resume resume = (Resume) model;
		context.put("resume", resume);
		context.put("person", resume.getOwner());
		context.put("experiences", resume.getExperiences());
		context.put("educations", resume.getEducations());
		context.put("hobbies", resume.getHobbies());
		if (resume.getPicture() != null) {
			context.put("photo",
					new ByteArrayImageProvider(resume.getPicture()));
		} else {
			try {
				context.put(
						"photo",
						new ByteArrayImageProvider(ResumeReportProcessor.class
								.getResourceAsStream("EmptyPhoto.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
