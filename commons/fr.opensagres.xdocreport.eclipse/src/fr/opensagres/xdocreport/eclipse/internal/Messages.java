package fr.opensagres.xdocreport.eclipse.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "fr.opensagres.xdocreport.eclipse.internal.Messages";//$NON-NLS-1$

	public static String WorkbenchWindowConfigurer_title;

	// Report Processors Editor
	public static String ReportProcessorsPage_title;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
