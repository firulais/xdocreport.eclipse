package org.eclipse.nebula.widgets.modelpicker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Text;

public class ModelHolder<T> implements PropertyChangeListener {

	public static final String MODEL = "model";

	private T model;
	private Text text;
	private List<SearcherDescriptor> searchers;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	private IModelLabelProvider modelLabelProvider;

	private boolean updating;

	public ModelHolder(Text text) {
		this.text = text;
		this.model = null;
		this.searchers = new ArrayList<SearcherDescriptor>();
	}

	public void setModel(T model) {
		if (updating) {
			return;
		}
		try {
			updating = true;
			T oldModel = this.model;
			this.model = model;
			if (model != null && modelLabelProvider != null) {
				String newLabel = modelLabelProvider.getContent(model);
				if (newLabel == null) {
					newLabel = "";
				}
				String oldLabel = text.getText();
				if (!newLabel.equals(oldLabel)) {
					text.setText(newLabel);
				}

			}
			propertyChangeSupport.firePropertyChange(MODEL, oldModel, model);
		} finally {
			updating = false;
		}
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

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void propertyChange(PropertyChangeEvent arg0) {

	}

	public void setModelLabelProvider(IModelLabelProvider modelLabelProvider) {
		this.modelLabelProvider = modelLabelProvider;
	}

	public IModelLabelProvider getModelLabelProvider() {
		return modelLabelProvider;
	}
}
