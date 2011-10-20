package fr.opensagres.xdocreport.eclipse.ui.editors;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import fr.opensagres.eclipse.forms.editor.ModelFormPage;

public abstract class ReportingFormPage<Model> extends ModelFormPage<Model> {

	public ReportingFormPage(ReportingFormEditor<Model> editor, String id,
			String title) {
		super(editor, id, title);
	}

	@Override
	public ReportingFormEditor<Model> getEditor() {
		return (ReportingFormEditor<Model>) super.getEditor();
	}
	
	@Override
	protected void onCreateUI(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		toolkit.decorateFormHeading(form.getForm());

		IToolBarManager manager = form.getToolBarManager();
		getEditor().contributeToToolbar(manager);
		form.updateToolBar();		
		form.setText(getFormTitleText());
		toolkit.decorateFormHeading(form.getForm());
		fillBody(managedForm, toolkit);
	}
	
	protected String getFormTitleText() {
		return getTitle();
	}
	
	protected abstract void fillBody(IManagedForm managedForm, FormToolkit toolkit);

}
