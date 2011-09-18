package fr.opensagres.xdocreport.eclipse;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngineRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportFormatRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorTypeRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.modules.ReportModuleRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.reporting.ReportEngineRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.reporting.ReportFormatRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.reporting.ReportProcessorTypeRegistry;

public final class PlatformXDocReport {

	public static IReportModuleRegistry getReportModuleRegistry() {
		return ReportModuleRegistry.getRegistry();
	}

	public static IReportProcessorTypeRegistry getReportProcessorTypeRegistry() {
		return ReportProcessorTypeRegistry.getRegistry();
	}

	public static IReportEngineRegistry getReportEngineRegistry() {
		return ReportEngineRegistry.getRegistry();
	}
	
	public static IReportFormatRegistry getReportFormatRegistry() {
		return ReportFormatRegistry.getRegistry();
	}
}
