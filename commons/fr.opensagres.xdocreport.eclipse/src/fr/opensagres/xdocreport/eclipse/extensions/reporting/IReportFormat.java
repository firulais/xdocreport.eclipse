package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import org.eclipse.jface.resource.ImageDescriptor;

public interface IReportFormat {

	public final String DOCX = "DOCX";
	public final String ODT = "ODT";
	public final String PDF = "PDF";
	public final String XHTML = "XHTML";
	public final String RTF = "RTF";

	String getId();

	ImageDescriptor getImageDescriptor();

}
