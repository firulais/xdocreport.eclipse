package fr.opensagres.eclipse.forms.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.forms.IFormPart;

import fr.opensagres.eclipse.forms.AbstractDetailsPage;
import fr.opensagres.eclipse.forms.IBindableAware;

public abstract class ModelDetailsPage<Model> extends AbstractDetailsPage
		implements IBindableAware {

	private DataBindingContext dataBindingContext;
	private Model modelObject;

	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection ssel = (IStructuredSelection) selection;
		if (ssel.size() == 1) {
			modelObject = (Model) ssel.getFirstElement();
		} else
			modelObject = null;
		bind();
	}

	public void bind() {
		ModelFormPage<Model> page = getModelFormPage();
		if (page != null) {
			if (dataBindingContext != null) {
				dataBindingContext.dispose();
			} else {
				dataBindingContext = page.getEditor().getDatabindingContext(
						getDatabindingContentId());
			}
			page.getEditor().bind(this, dataBindingContext);
		}
	}

	private String getDatabindingContentId() {
		// TODO Auto-generated method stub
		return "AAAAAAAAAAAAAAAAAAAAAAA";
	}

	public Model getModelObject() {
		return modelObject;
	}

	protected ModelFormPage<Model> getModelFormPage() {
		return (ModelFormPage<Model>) super.getManagedForm().getContainer();
	}

}
