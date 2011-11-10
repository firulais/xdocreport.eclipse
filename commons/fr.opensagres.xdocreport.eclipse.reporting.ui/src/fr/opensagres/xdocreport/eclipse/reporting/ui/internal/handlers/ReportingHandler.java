package fr.opensagres.xdocreport.eclipse.reporting.ui.internal.handlers;

import org.eclipse.ui.IEditorInput;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.reporting.ui.internal.editors.ReportingEditor;
import fr.opensagres.xdocreport.eclipse.reporting.ui.internal.editors.ReportingEditorInput;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenEditorHandler;

public class ReportingHandler extends OpenEditorHandler {

	@Override
	protected String getEditorId() {
		return ReportingEditor.EDITOR_ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry) {
		return new ReportingEditorInput(entry);
	}

}
