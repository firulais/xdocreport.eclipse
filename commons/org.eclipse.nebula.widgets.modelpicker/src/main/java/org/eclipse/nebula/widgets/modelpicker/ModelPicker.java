package org.eclipse.nebula.widgets.modelpicker;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.ModelContentProposalAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class ModelPicker<T> {

	private T model;
	private Text text;
	private List<ISearcher> searchers;
	private Composite container;
	private ModelContentProposalAdapter adapter;

	public ModelPicker(Composite parent, int style, int textStyle) {
		this(parent, style, textStyle, true);
	}

	protected ModelPicker(Composite parent, int style, int textStyle,
			boolean createUI) {
		this.container = createUI(parent, style, textStyle);
		init();
	}

	protected Composite createUI(Composite parent, int style, int textStyle) {
		Composite composite = new Composite(parent, style);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);

		text = createText(composite, textStyle);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		return composite;
	}

	public ModelPicker(Text text) {
		this.text = text;
		init();
	}

	private void init() {
		this.searchers = new ArrayList<ISearcher>();
	}

	public void setModel(T model) {
		this.model = model;
	}

	public T getModel() {
		return model;
	}

	public void addSearcher(ISearcher searcher) {
		searcher.init(this);
		searchers.add(searcher);
	}

	public void removeSearcher(ISearcher searcher) {
		searchers.remove(searcher);
	}

	public Composite getContainer() {
		return container;
	}

	protected Text createText(Composite parent, int textStyle) {
		return new Text(parent, textStyle);
	}

	public void setLayoutData(Object layoutData) {
		getContainer().setLayoutData(layoutData);
	}

	public Text getText() {
		return text;
	}
}
