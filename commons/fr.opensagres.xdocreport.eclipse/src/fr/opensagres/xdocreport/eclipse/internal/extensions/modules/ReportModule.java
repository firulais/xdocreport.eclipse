package fr.opensagres.xdocreport.eclipse.internal.extensions.modules;

import java.util.ArrayList;
import java.util.List;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportLoader;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessors;

public class ReportModule extends ReportBaseModule implements IReportModule {

	private final List<IReportModuleEntry> entries;
	private IReportProcessors processors;

	public ReportModule(String id) {
		super(id);
		this.entries = new ArrayList<IReportModuleEntry>();
		this.processors = ReportProcessors.EMPTY;
	}

	public void addEntry(IReportModuleEntry entry) {
		entries.add(entry);
	}

	public List<IReportModuleEntry> getEntries() {
		return entries;
	}

	public IReportProcessors getProcessors() {
		return processors;
	}

	public void setProcessors(IReportProcessors processors) {
		this.processors = processors;
	}
	
	public List<IReportLoader> getAllReportLoaders() {
		List<IReportLoader> reportLoaders =new ArrayList<IReportLoader>();
		List<IReportProcessor> processorTypes = processors.getProcessors();
		for (IReportProcessor  processorType : processorTypes) {
			reportLoaders.addAll(processorType.getReportLoaders());
		}
		return reportLoaders;
	}

}
