package fr.opensagres.eclipse.forms.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class SearchContentProposalAdapter extends ContentProposalAdapter
		implements IContentProposalProvider {

	public static final IControlContentAdapter TextContentAdapter = new TextContentAdapter();

	private ISearcher searcher;
	private ICompletionLabelProvider completionLabelProvider;
	private Listener keyDownListener;
	private ListenerList searchListeners = new ListenerList();
	private Object currentModel;

	public SearchContentProposalAdapter(Text control, KeyStroke keyStroke,
			char[] autoActivationCharacters) {
		this(control, TextContentAdapter, keyStroke, autoActivationCharacters);
	}

	public SearchContentProposalAdapter(Control control,
			IControlContentAdapter controlContentAdapter, KeyStroke keyStroke,
			char[] autoActivationCharacters) {
		super(control, controlContentAdapter, null, keyStroke,
				autoActivationCharacters);
		this.currentModel = null;
		if ("rap".equals(SWT.getPlatform())) {
			keyDownListener = control.getListeners(SWT.KeyDown)[0];
			Display.getCurrent().addFilter(SWT.KeyDown, keyDownListener);
			control.addDisposeListener(new DisposeListener() {

				public void widgetDisposed(DisposeEvent e) {
					Display.getCurrent().removeFilter(SWT.KeyDown,
							keyDownListener);
				}
			});
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
		return searcher.search(contents, position);
	}

	public void setSearcher(ISearcher searcher) {
		this.searcher = searcher;
	}

	public ISearcher getSearcher() {
		return searcher;
	}

	public void setCompletionLabelProvider(
			ICompletionLabelProvider completionLabelProvider) {
		this.completionLabelProvider = completionLabelProvider;
		super.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				ModelContentProposal proposal = (ModelContentProposal) element;
				return SearchContentProposalAdapter.this.completionLabelProvider
						.getText(proposal.getModel());
			}

			@Override
			public Image getImage(Object element) {
				ModelContentProposal proposal = (ModelContentProposal) element;
				return SearchContentProposalAdapter.this.completionLabelProvider
						.getImage(proposal.getModel());
			}
		});
	}

	public ICompletionLabelProvider getCompletionLabelProvider() {
		return completionLabelProvider;
	}

	public Object getCurrentModel() {
		return currentModel;
	}

	public void setCurrentModel(Object model) {
		Object oldModel = currentModel;
		this.currentModel = model;
//		if (oldModel == null) {
//			if (currentModel != null) {
//				notifyListeners(oldModel, currentModel);
//			}
//		} else {
//			if (!oldModel.equals(currentModel)) {
//				notifyListeners(oldModel, currentModel);
//			}
//		}

		notifyListeners(oldModel, currentModel);
	}

	protected void forceOpenProposalPopup() {
		super.openProposalPopup();
	}

	public void addSearchListener(ISearchListener listener) {
		searchListeners.add(listener);
	}

	public void removeSearchListener(ISearchListener listener) {
		searchListeners.remove(listener);
	}

	private void notifyListeners(Object oldModel, Object newModel) {
		Object[] listeners = searchListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			((ISearchListener) listeners[i]).modelChanged(oldModel, newModel);
		}
	}
}
