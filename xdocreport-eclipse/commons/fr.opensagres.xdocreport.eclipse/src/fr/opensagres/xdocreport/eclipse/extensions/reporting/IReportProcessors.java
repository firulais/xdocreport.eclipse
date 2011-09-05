package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.util.List;

public interface IReportProcessors {

	String getCommandId();
	
	List<IReportProcessorType> getProcessorTypes();
	
}
