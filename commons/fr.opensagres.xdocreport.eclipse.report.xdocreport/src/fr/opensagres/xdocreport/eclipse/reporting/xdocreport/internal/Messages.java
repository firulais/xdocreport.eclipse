package fr.opensagres.xdocreport.eclipse.reporting.xdocreport.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "fr.opensagres.xdocreport.eclipse.reporting.xdocreport.internal.Messages";//$NON-NLS-1$

	public static String XDocReportLoaderDetailsPage_templateEngine_label;
	
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
