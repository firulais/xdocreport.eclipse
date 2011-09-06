package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.util.Collection;

public interface IReportFormatRegistry {

	IReportFormat getFormat(String id);

	Collection<IReportFormat> getFormats();
}
