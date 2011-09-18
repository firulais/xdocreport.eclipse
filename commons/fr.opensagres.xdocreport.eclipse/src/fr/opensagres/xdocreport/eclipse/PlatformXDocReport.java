package fr.opensagres.xdocreport.eclipse;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngineRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportFormatRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.modules.ReportModuleRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.reporting.ReportEngineRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.reporting.ReportFormatRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.reporting.ReportProcessorRegistry;

public final class PlatformXDocReport {

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
}
