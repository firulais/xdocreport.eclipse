package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.util.List;

public interface IReportProcessor {

	String getId();

	String getName();

	IReportEngine getEngine();

	List<IReportLoader> getReportLoaders();

	List<IReportFormat> getSupportedFormats();

	boolean canSupportFormat(IReportFormat format);

}
