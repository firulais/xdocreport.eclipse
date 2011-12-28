package org.eclipse.nebula.widgets.modelpicker.fieldassist;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.nebula.widgets.modelpicker.ISearcher;
import org.eclipse.nebula.widgets.modelpicker.ModelPicker;

public abstract class CompletionSearcher implements ISearcher {
	private ModelContentProposalAdapter adapter;

	public void init(final ModelPicker modelPicker) {

		char[] autoActivationCharacters = new char[] {};
		KeyStroke keyStroke = null;
		try {
			keyStroke = KeyStroke.getInstance("Ctrl+Space");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		adapter = new ModelContentProposalAdapter(modelPicker.getText(),
				keyStroke, autoActivationCharacters) {
			protected void setCurrentModel(Object model) {
				modelPicker.setModel(model);
			};
		};
		adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		adapter.setPropagateKeys(true);
		adapter.setSearcher(this);
		adapter.setCompletionLabelProvider(getCompletionLabelProvider());
	}

	protected abstract ICompletionLabelProvider getCompletionLabelProvider();

}
