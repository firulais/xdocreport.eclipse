package fr.opensagres.xdocreport.eclipse.reporting.core;

import java.util.List;

public interface IReportProcessor {

	String getId();

	String getName();

	IReportEngine getEngine();

	List<IReportLoader> getReportLoaders();

	List<IReportFormat> getSupportedFormats();

	boolean canSupportFormat(IReportFormat format);

}
