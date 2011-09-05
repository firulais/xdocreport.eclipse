package fr.opensagres.xdocreport.eclipse.internal.extensions.modules;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;

public class ReportModuleEntry extends ReportBaseModule implements
		IReportModuleEntry {

	private final IReportModule parent;
	private String commandId;

	public ReportModuleEntry(IReportModule parent) {
		super(null);
		this.parent = parent;
	}

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public IReportModule getModule() {
		return parent;
	}
}
