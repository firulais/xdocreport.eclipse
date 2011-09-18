package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.util.List;
import java.util.Map;

public interface IReportProcessors {

	String getCommandId();

	List<IReportProcessor> getProcessors();

	Map<IReportFormat, List<IReportProcessor>> getSupportedFormats();

}
