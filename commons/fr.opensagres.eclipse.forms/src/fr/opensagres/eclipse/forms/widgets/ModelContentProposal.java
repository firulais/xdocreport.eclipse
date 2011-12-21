package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.jface.fieldassist.IContentProposal;

public class ModelContentProposal implements IContentProposal {

	private final Object model;

	public ModelContentProposal(Object model) {
		this.model = model;
	}

	public String getContent() {
		return model.toString();
	}

	public int getCursorPosition() {
		return getContent().length();
	}

	public String getLabel() {
		return model.toString();
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object getModel() {
		return model;
	}
}
