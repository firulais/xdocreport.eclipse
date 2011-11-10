package fr.opensagres.xdocreport.eclipse.reporting.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ReportProcessors implements IReportProcessors {

	public static final IReportProcessors EMPTY = new ReportProcessors(null);

	private final String commandId;
	private final List<IReportProcessor> processors;
	private final Map<IReportFormat, List<IReportProcessor>> supportedFormats;

	public ReportProcessors(String commandId) {
		this.commandId = commandId;
		this.processors = new ArrayList<IReportProcessor>();
		this.supportedFormats = new LinkedHashMap<IReportFormat, List<IReportProcessor>>();
	}

	public String getCommandId() {
		return commandId;
	}

	public List<IReportProcessor> getProcessors() {
		return processors;
	}

	public synchronized void  addProcessor(IReportProcessor processor) {
		processors.add(processor);
		IReportFormatRegistry formatRegistry = PlatformCoreReporting
				.getReportFormatRegistry();
		Collection<IReportFormat> formats = formatRegistry.getFormats();
		for (IReportFormat format : formats) {
			if (processor.canSupportFormat(format)) {
				List<IReportProcessor> processorsForFormat = supportedFormats.get(format);
				if (processorsForFormat == null) {
					processorsForFormat = new ArrayList<IReportProcessor>();
					supportedFormats.put(format, processorsForFormat);
				}
				processorsForFormat.add(processor);
			}
		}
	}

	public Map<IReportFormat, List<IReportProcessor>> getSupportedFormats() {
		return supportedFormats;
	}

}
