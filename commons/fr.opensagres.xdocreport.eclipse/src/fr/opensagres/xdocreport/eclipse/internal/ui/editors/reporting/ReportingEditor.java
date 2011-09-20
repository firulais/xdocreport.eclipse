package fr.opensagres.xdocreport.eclipse.internal.ui.editors.reporting;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

public class ReportingEditor extends FormEditor {

	public static final String EDITOR_ID = "fr.opensagres.xdocreport.eclipse.ui.editors.ReportingEditor";

	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	protected void addPages() {
		try {
			addPage(new ReportProcessorsPage(this));
		} catch (PartInitException e) {
			//
		}
	}

}
