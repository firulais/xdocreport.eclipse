package org.eclipse.nebula.widgets.modelpicker.fieldassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.nebula.widgets.modelpicker.ISearchable;
import org.eclipse.nebula.widgets.modelpicker.internal.Utils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public abstract class ModelContentProposalAdapter extends
		ContentProposalAdapter implements IContentProposalProvider {

	public static final IControlContentAdapter TextContentAdapter = new TextContentAdapter();

	private ISearchable searchable;
	private ICompletionLabelProvider completionLabelProvider;
	private Listener keyDownListener;

	public ModelContentProposalAdapter(Text control, KeyStroke keyStroke,
			char[] autoActivationCharacters) {
		this(control, TextContentAdapter, keyStroke, autoActivationCharacters);
	}

	public ModelContentProposalAdapter(Control control,
			IControlContentAdapter controlContentAdapter, KeyStroke keyStroke,
			char[] autoActivationCharacters) {
		super(control, controlContentAdapter, null, keyStroke,
				autoActivationCharacters);
		if (Utils.isRap()) {
			keyDownListener = control.getListeners(SWT.KeyDown)[0];
			Utils.addFilterIfNeeded(keyDownListener, control);
		}
		super.setContentProposalProvider(this);

		control.addListener(SWT.Modify, new Listener() {

			public void handleEvent(Event event) {
				setCurrentModel(null);
			}
		});
		this.addContentProposalListener(new IContentProposalListener() {

			public void proposalAccepted(IContentProposal proposal) {
				setCurrentModel(((ModelContentProposal) proposal).getModel());

			}

		});
	}

	public IContentProposal[] getProposals(String contents, int position) {
		Iterable<?> models = search(contents, position);
		List<IContentProposal> proposals = new ArrayList<IContentProposal>();
		for (Object model : models) {
			proposals.add(new ModelContentProposal(model,
					getCompletionLabelProvider()));
		}
		return (IContentProposal[]) proposals.toArray(new IContentProposal[0]);
	}

	private Iterable<?> search(String contents, int position) {
		return searchable.search(contents, position);
	}

	public void setSearchable(ISearchable searcher) {
		this.searchable = searcher;
	}

	public ISearchable getSearchable() {
		return searchable;
	}

	public void setCompletionLabelProvider(
			ICompletionLabelProvider completionLabelProvider) {
		this.completionLabelProvider = completionLabelProvider;
		super.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				ModelContentProposal proposal = (ModelContentProposal) element;
				return ModelContentProposalAdapter.this.completionLabelProvider
						.getText(proposal.getModel());
			}

			@Override
			public Image getImage(Object element) {
				ModelContentProposal proposal = (ModelContentProposal) element;
				return ModelContentProposalAdapter.this.completionLabelProvider
						.getImage(proposal.getModel());
			}
		});
	}

	public ICompletionLabelProvider getCompletionLabelProvider() {
		return completionLabelProvider;
	}

	protected void forceOpenProposalPopup() {
		super.openProposalPopup();
	}

	protected abstract void setCurrentModel(Object model);

}
