package org.eclipse.nebula.widgets.pagination;

public interface PageControllerChangedListener {

	public void pageSelected(int oldPageNumber, int newPageNumber,
			PaginationController controller);

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller);

}