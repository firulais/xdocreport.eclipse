package fr.opensagres.xdocreport.eclipse.extensions.modules;

import java.util.List;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportLoader;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessors;

public interface IReportModule extends IReportBaseModule {

	List<IReportModuleEntry> getEntries();	

	IReportProcessors getProcessors();
	
	List<IReportLoader> getAllReportLoaders();
}
