package org.eclipse.nebula.widgets.modelpicker;

public interface ISearchable {

	Iterable<?> search(String contents, int position);

}
