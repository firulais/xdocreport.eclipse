package fr.opensagres.xdocreport.eclipse.internal.extensions.modules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportFormat;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportFormatRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorType;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessors;

public class ReportProcessors implements IReportProcessors {

	public static final IReportProcessors EMPTY = new ReportProcessors(null);

	private final String commandId;
	private final List<IReportProcessorType> processorTypes;
	private final Map<IReportFormat, List<IReportProcessorType>> supportedFormats;

	public ReportProcessors(String commandId) {
		this.commandId = commandId;
		this.processorTypes = new ArrayList<IReportProcessorType>();
		this.supportedFormats = new LinkedHashMap<IReportFormat, List<IReportProcessorType>>();
	}

	public String getCommandId() {
		return commandId;
	}

	public List<IReportProcessorType> getProcessorTypes() {
		return processorTypes;
	}

	public synchronized void  addProcessorType(IReportProcessorType processorType) {
		processorTypes.add(processorType);
		IReportFormatRegistry formatRegistry = PlatformXDocReport
				.getReportFormatRegistry();
		Collection<IReportFormat> formats = formatRegistry.getFormats();
		for (IReportFormat format : formats) {
			if (processorType.canSupportFormat(format)) {
				List<IReportProcessorType> processorTypesForFormat = supportedFormats.get(format);
				if (processorTypesForFormat == null) {
					processorTypesForFormat = new ArrayList<IReportProcessorType>();
					supportedFormats.put(format, processorTypesForFormat);
				}
				processorTypesForFormat.add(processorType);
			}
		}
	}

	public Map<IReportFormat, List<IReportProcessorType>> getSupportedFormats() {
		return supportedFormats;
	}

}
