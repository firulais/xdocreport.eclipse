package fr.opensagres.xdocreport.eclipse.internal.extensions.modules;

import java.util.ArrayList;
import java.util.List;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorType;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessors;

public class ReportProcessors implements IReportProcessors {

	public static final IReportProcessors EMPTY = new ReportProcessors(null);

	private final String commandId;
	private final List<IReportProcessorType> processorTypes;

	public ReportProcessors(String commandId) {
		this.commandId = commandId;
		this.processorTypes = new ArrayList<IReportProcessorType>();
	}

	public String getCommandId() {
		return commandId;
	}

	public List<IReportProcessorType> getProcessorTypes() {
		return processorTypes;
	}

	public void addProcessorType(IReportProcessorType processorType) {
		processorTypes.add(processorType);
	}

}
