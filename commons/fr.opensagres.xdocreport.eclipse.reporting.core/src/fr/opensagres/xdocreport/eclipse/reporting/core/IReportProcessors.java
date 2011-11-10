package fr.opensagres.xdocreport.eclipse.reporting.core;

import java.util.List;
import java.util.Map;

public interface IReportProcessors {

	String getCommandId();

	List<IReportProcessor> getProcessors();

	Map<IReportFormat, List<IReportProcessor>> getSupportedFormats();

}
