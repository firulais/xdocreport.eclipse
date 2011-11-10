package fr.opensagres.xdocreport.eclipse;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.modules.ReportModuleRegistry;

public final class PlatformXDocReport {

	private static final String FRENCH_DATE_PATTERN = "dd/MM/yyyy";

	public static IReportModuleRegistry getReportModuleRegistry() {
		return ReportModuleRegistry.getRegistry();
	}
	
	public static String getDatePattern() {
		return FRENCH_DATE_PATTERN;
	}
}
