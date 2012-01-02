package org.eclipse.nebula.widgets.modelpicker;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Text;

public class ModelHolder<T> {

	private T model;
	private Text text;
	private List<SearcherDescriptor> searchers;

	public ModelHolder(Text text) {
		this.text = text;
		this.model = null;
		this.searchers = new ArrayList<SearcherDescriptor>();
	}

	public void setModel(T model) {
		this.model = model;
	}

	public T getModel() {
		return model;
	}

	public SearcherDescriptor addSearcher(ISearcher<T> initializer,
			String keyStroke, String description, Image icon) {
		SearcherDescriptor searcher = new SearcherDescriptor(initializer,
				keyStroke, description, icon);
		return addSearcher(searcher);
	}

	public SearcherDescriptor addSearcher(SearcherDescriptor searcher) {
		searcher.init(this, searcher.getKeyStroke());
		searchers.add(searcher);
		return searcher;
	}

	public void removeSearcher(SearcherDescriptor searcher) {
		searchers.remove(searcher);
	}

	public Text getText() {
		return text;
	}

	public void dispose() {
		for (SearcherDescriptor descriptor : searchers) {
			descriptor.dispose();
		}

	}
}
