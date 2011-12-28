package org.eclipse.nebula.widgets.modelpicker.fieldassist;

import org.eclipse.jface.fieldassist.IContentProposal;

public class ModelContentProposal implements IContentProposal {

	private final Object model;
	private final ICompletionLabelProvider completionLabelProvider;

	public ModelContentProposal(Object model,
			ICompletionLabelProvider completionLabelProvider) {
		this.model = model;
		this.completionLabelProvider = completionLabelProvider;
	}

	public String getContent() {
		if (completionLabelProvider != null) {
			return completionLabelProvider.getContent(model);
		}
		return model.toString();
	}

	public int getCursorPosition() {
		int position = ICompletionLabelProvider.UNDEFINED_CURSOR_POSITION;
		if (completionLabelProvider != null) {
			position = completionLabelProvider.getCursorPosition(model);
		}
		if (position != ICompletionLabelProvider.UNDEFINED_CURSOR_POSITION) {
			return position;
		}
		return getContent().length();
	}

	public String getLabel() {
		if (completionLabelProvider != null) {
			return completionLabelProvider.getText(model);
		}
		return model.toString();
	}

	public String getDescription() {
		if (completionLabelProvider != null) {
			return completionLabelProvider.getDescription(model);
		}
		return null;
	}

	public Object getModel() {
		return model;
	}
}
