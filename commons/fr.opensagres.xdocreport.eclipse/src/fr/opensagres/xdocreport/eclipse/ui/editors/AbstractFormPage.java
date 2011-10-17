package fr.opensagres.xdocreport.eclipse.ui.editors;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import fr.opensagres.eclipse.forms.editor.ModelFormPage;

public abstract class AbstractFormPage<Model> extends ModelFormPage<Model> {

	public AbstractFormPage(AbstractFormEditor<Model> editor, String id,
			String title) {
		super(editor, id, title);
	}

//	@SuppressWarnings("unchecked")
//	public Model getModel() {
//		return ((AbstractFormEditor<Model>) super.getEditor()).getModel();
//	}

	@Override
	public AbstractFormEditor<Model> getEditor() {
		return (AbstractFormEditor<Model>) super.getEditor();
	}
	
	@Override
	protected void onCreateUI(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		toolkit.decorateFormHeading(form.getForm());

		IToolBarManager manager = form.getToolBarManager();
		getEditor().contributeToToolbar(manager);
		form.updateToolBar();		
	}

//	@Override
//	protected void createFormContent(IManagedForm managedForm) {
//		final ScrolledForm form = managedForm.getForm();
//		FormToolkit toolkit = managedForm.getToolkit();
//		toolkit.decorateFormHeading(form.getForm());
//
//		IToolBarManager manager = form.getToolBarManager();
//		getEditor().contributeToToolbar(manager);
//		form.updateToolBar();
//	}

}
