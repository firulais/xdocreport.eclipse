package org.eclipse.nebula.widgets.modelpicker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class ModelPropertyChangeListener<T> implements
		PropertyChangeListener {

	private final ModelPicker<T> modelPicker;
	private final ModelHolder<T> modelHolder;

	public ModelPropertyChangeListener(ModelHolder<T> modelHolder) {
		modelHolder.addPropertyChangeListener(ModelHolder.MODEL, this);
		this.modelHolder = modelHolder;
		this.modelPicker = null;
		setModelEnabled(getModel() != null);
	}

	public ModelPropertyChangeListener(ModelPicker<T> modelPicker) {
		modelPicker.addPropertyChangeListener(ModelPicker.MODEL, this);
		this.modelHolder = null;
		this.modelPicker = modelPicker;
		setModelEnabled(getModel() != null);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		setModelEnabled(evt.getNewValue() != null);
	}

	public T getModel() {
		if (modelHolder != null) {
			return modelHolder.getModel();
		}
		return modelPicker.getModel();
	}

	protected abstract void setModelEnabled(boolean enabled);
}
