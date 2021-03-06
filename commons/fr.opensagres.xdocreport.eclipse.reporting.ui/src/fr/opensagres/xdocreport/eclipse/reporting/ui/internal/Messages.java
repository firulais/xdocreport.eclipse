package fr.opensagres.xdocreport.eclipse.reporting.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "fr.opensagres.xdocreport.eclipse.reporting.ui.internal.Messages";//$NON-NLS-1$

	public static String WorkbenchWindowConfigurer_title;

	// Report Processors Editor
	public static String ReportProcessorsPage_title;
	
	// GenericReportLoaderDetailsPage
	public static String GenericReportLoaderDetailsPage_title;
	public static String GenericReportLoaderDetailsPage_desc;
	public static String GenericReportLoaderDetails_reportEngine_label;
	public static String GenericReportLoaderDetails_reportId_label;
	public static String GenericReportLoaderDetails_reportName_label;
	public static String GenericReportLoaderDetails_reportDesc_label;
	public static String GenericReportLoaderDetails_reportSource_label;
	
	// ReportLoaderMasterDetailsBlock
	public static String ReportLoaderMasterDetailsBlock_title;
	public static String ReportLoaderMasterDetailsBlock_desc;
	
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
