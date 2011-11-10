package fr.opensagres.xdocreport.eclipse.reporting.core;

import fr.opensagres.xdocreport.eclipse.reporting.core.internal.ReportEngineRegistry;
import fr.opensagres.xdocreport.eclipse.reporting.core.internal.ReportFormatRegistry;
import fr.opensagres.xdocreport.eclipse.reporting.core.internal.ReportProcessorRegistry;

public class PlatformCoreReporting {

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
