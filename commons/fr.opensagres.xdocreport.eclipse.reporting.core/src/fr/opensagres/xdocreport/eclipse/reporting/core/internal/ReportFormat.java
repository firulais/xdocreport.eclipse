package fr.opensagres.xdocreport.eclipse.reporting.core.internal;

import org.eclipse.jface.resource.ImageDescriptor;

import fr.opensagres.xdocreport.eclipse.reporting.core.IReportFormat;

public class ReportFormat implements IReportFormat {

	private final String id;
	private final String name;
	private ImageDescriptor imageDescriptor;
	private String description;

	public ReportFormat(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setImageDescriptor(ImageDescriptor imageDescriptor) {
		this.imageDescriptor = imageDescriptor;
	}

	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
