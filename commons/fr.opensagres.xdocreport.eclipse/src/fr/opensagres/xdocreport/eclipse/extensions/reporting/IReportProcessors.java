package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.util.List;
import java.util.Map;

public interface IReportProcessors {

	String getCommandId();

	List<IReportProcessorType> getProcessorTypes();

	Map<IReportFormat, List<IReportProcessorType>> getSupportedFormats();

}
