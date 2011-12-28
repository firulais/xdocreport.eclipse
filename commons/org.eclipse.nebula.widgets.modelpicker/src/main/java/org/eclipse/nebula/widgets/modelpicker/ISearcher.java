package org.eclipse.nebula.widgets.modelpicker;

public interface ISearcher {

	void init(ModelPicker modelPicker);

	Iterable<?> search(String contents, int position);

}
