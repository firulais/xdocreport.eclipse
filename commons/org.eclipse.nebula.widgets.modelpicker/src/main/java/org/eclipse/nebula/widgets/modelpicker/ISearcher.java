package org.eclipse.nebula.widgets.modelpicker;

public interface ISearcher<T> {

	void init(ModelHolder<T> modelPicker, String keyStroke);

	void dispose();

	void handle();
}
