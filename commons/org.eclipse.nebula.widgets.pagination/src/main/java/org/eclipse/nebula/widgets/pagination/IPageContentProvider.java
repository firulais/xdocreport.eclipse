package org.eclipse.nebula.widgets.pagination;

import java.util.List;

public interface IPageContentProvider<T extends PageableController, E> {

	T createController(int pageSize);
	
	//E loadPage(T controller);
	
	long getTotalElements(E page);
	
	List<?> getPaginatedList(E page);
	
}
