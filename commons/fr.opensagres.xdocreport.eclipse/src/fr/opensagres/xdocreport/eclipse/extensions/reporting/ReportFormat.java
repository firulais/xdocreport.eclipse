package fr.opensagres.xdocreport.eclipse.extensions.reporting;

public class ReportFormat {

	public static final ReportFormat DOCX = new ReportFormat("docx");
	public static final ReportFormat ODT = new ReportFormat("odt");
	public static final ReportFormat PDF = new ReportFormat("pdf");
	public static final ReportFormat XHTML = new ReportFormat("xhtml");
	public static final ReportFormat RTF = new ReportFormat("rtf");
	
	private final String id;

	public ReportFormat(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
