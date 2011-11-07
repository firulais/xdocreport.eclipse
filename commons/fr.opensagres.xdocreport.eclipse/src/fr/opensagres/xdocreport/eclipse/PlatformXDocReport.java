package fr.opensagres.xdocreport.eclipse;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngineRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportFormatRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.ui.IUIFragmentRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.modules.ReportModuleRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.reporting.ReportEngineRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.reporting.ReportFormatRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.reporting.ReportProcessorRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.ui.UIFragmentRegistry;

public final class PlatformXDocReport {

	private static final String FRENCH_DATE_PATTERN = "dd/MM/yyyy";

	public static IReportModuleRegistry getReportModuleRegistry() {
		return ReportModuleRegistry.getRegistry();
	}

	public static IReportProcessorRegistry getReportProcessorRegistry() {
		return ReportProcessorRegistry.getRegistry();
	}

	public static IReportEngineRegistry getReportEngineRegistry() {
		return ReportEngineRegistry.getRegistry();
	}
	
	public static IReportFormatRegistry getReportFormatRegistry() {
		return ReportFormatRegistry.getRegistry();
	}
	
	public static IUIFragmentRegistry getUIFragmentRegistry() {
		return UIFragmentRegistry.getRegistry();
	}
	
	public static String getDatePattern() {
		return FRENCH_DATE_PATTERN;
	}
}
