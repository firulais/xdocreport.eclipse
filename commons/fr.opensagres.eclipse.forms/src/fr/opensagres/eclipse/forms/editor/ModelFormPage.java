package fr.opensagres.eclipse.forms.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.ui.forms.IManagedForm;

public abstract class ModelFormPage<Model> extends AbstractFormPage {

	private DataBindingContext dataBindingContext;

	public ModelFormPage(ModelFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	public ModelFormPage(String id, String title) {
		super(id, title);
	}

	@Override
	protected void onAfterUI(IManagedForm managedForm) {
		super.onAfterUI(managedForm);
		bind();
	}

	protected void bind() {
		DataBindingContext dataBindingContext = getEditor()
				.getDatabindingContext(getDatabindingContentId());
		getEditor().bind(this, dataBindingContext);
	}

	protected Model getModelObject() {
		return (Model) getEditor().getModelObject();
	}

	protected abstract void onBind(DataBindingContext dataBindingContext);

	protected String getDatabindingContentId() {
		return ModelFormEditor.SHARED_DATABINDING_CONTEXT_ID;
	}

	@Override
	public ModelFormEditor getEditor() {
		return (ModelFormEditor) super.getEditor();
	}

}
