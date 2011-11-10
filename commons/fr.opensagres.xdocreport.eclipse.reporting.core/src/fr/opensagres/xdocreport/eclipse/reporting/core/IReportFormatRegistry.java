package fr.opensagres.xdocreport.eclipse.reporting.core;

import java.util.Collection;

public interface IReportFormatRegistry {

	IReportFormat getFormat(String id);

	Collection<IReportFormat> getFormats();
}
