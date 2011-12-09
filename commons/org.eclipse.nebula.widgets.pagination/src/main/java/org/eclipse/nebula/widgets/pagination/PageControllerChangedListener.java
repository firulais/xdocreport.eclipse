package org.eclipse.nebula.widgets.pagination;

public interface PageControllerChangedListener {

	public void pageSelected(int oldPageNumber, int newPageNumber,
			PaginationController controller);

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller);

	public void sortChanged(String oldPopertyName, String propertyName,
			int oldSortDirection, int sortDirection,
			PaginationController paginationController);

}