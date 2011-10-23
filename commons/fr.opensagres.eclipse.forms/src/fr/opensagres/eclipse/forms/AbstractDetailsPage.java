package fr.opensagres.eclipse.forms;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;

public class AbstractDetailsPage implements IDetailsPage {

	private IManagedForm form;

	public void initialize(IManagedForm form) {
		this.form = form;
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void commit(boolean onSave) {
		// TODO Auto-generated method stub

	}

	public boolean setFormInput(Object input) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public boolean isStale() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub

	}

	public void selectionChanged(IFormPart part, ISelection selection) {
		// TODO Auto-generated method stub

	}

	public void createContents(Composite parent) {
		// TODO Auto-generated method stub

	}

	protected FormPage getFormPage() {
		return (FormPage) form.getContainer();
	}

}
