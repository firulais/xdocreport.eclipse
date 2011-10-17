package fr.opensagres.xdocreport.eclipse.internal.ui.editors.reporting;

import fr.opensagres.eclipse.forms.editor.AbstractEditorInput;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;

public class ReportingEditorInput extends AbstractEditorInput {

	private final IReportModuleEntry entry;

	public ReportingEditorInput(IReportModuleEntry entry) {
		this.entry = entry;
	}

	public String getName() {
		return "Reporting";
	}

	public String getToolTipText() {
		return "Reporting";
	}

	public IReportModuleEntry getEntry() {
		return entry;
	}

}
