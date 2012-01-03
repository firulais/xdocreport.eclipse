package org.eclipse.nebula.widgets.modelpicker.fieldassist;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.nebula.widgets.modelpicker.ISearchable;
import org.eclipse.nebula.widgets.modelpicker.ISearcher;
import org.eclipse.nebula.widgets.modelpicker.ModelHolder;

public class CompletionSearcher<T> implements ISearcher<T> {

	private ModelContentProposalAdapter adapter;

	public void init(final ModelHolder<T> modelPicker, String key) {

		char[] autoActivationCharacters = new char[] {};
		KeyStroke keyStroke = null;
		try {
			keyStroke = KeyStroke.getInstance(key);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		adapter = new ModelContentProposalAdapter(modelPicker.getText(),
				keyStroke, autoActivationCharacters) {
			protected void setCurrentModel(Object model) {
				modelPicker.setModel((T) model);
			};
		};
		adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		adapter.setPropagateKeys(true);
	}

	public void setSearchable(ISearchable searchable) {
		adapter.setSearchable(searchable);
	}

	public void setCompletionLabelProvider(
			ICompletionLabelProvider completionLabelProvider) {
		adapter.setCompletionLabelProvider(completionLabelProvider);
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void handle() {
		adapter.forceOpenProposalPopup();
	}
}
