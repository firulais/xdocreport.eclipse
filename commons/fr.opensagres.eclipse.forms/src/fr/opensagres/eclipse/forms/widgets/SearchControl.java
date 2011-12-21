package fr.opensagres.eclipse.forms.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SearchControl extends Composite implements
		IContentProposalProvider {

	private Text searchText;
	private int textStyle;
	private ISearcher searcher;

	public SearchControl(Composite parent, int style, int textStyle) {
		this(parent, style, textStyle, true);
	}

	protected SearchControl(Composite parent, int style, int textStyle,
			boolean createUI) {
		super(parent, style);
		this.textStyle = textStyle;
		if (createUI) {
			createUI(this);
		}
	}

	protected void createUI(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 1;
		// layout.marginHeight = 1;
		layout.horizontalSpacing = 0;

		this.setLayout(layout);

		searchText = createText(parent, getTextStyle());
		searchText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		//createContentAssist();
	}

	private void createContentAssist() {

		char[] autoActivationCharacters = new char[] { ' ' };
		KeyStroke keyStroke = null;
		IContentProposalProvider proposalProvider = null;
		ContentProposalAdapter adapter = new ContentProposalAdapter(searchText,
				new TextContentAdapter(), this, keyStroke,
				autoActivationCharacters);
		adapter.addContentProposalListener(new IContentProposalListener() {
			
			public void proposalAccepted(IContentProposal proposal) {
				System.err.println(((ModelContentProposal)proposal).getModel() );
				
			}
		});
	}

	protected Text createText(Composite parent, int textStyle) {
		return new Text(parent, textStyle);
	}

	private int getTextStyle() {
		return textStyle;
	}

	public IContentProposal[] getProposals(String contents, int position) {
		Iterable<?> models = search(contents, position);
		List<IContentProposal> proposals = new ArrayList<IContentProposal>();
		for (Object model : models) {
			proposals.add(new ModelContentProposal(model));
		}
		return (IContentProposal[]) proposals.toArray(new IContentProposal[0]);
	}

	private Iterable<?> search(String contents, int position) {
		return searcher.search(contents, position);
	}

	public void setSearcher(ISearcher searcher) {
		this.searcher = searcher;
	}
}
