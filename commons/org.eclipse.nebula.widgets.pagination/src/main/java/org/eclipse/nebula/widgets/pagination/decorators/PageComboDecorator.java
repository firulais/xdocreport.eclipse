package org.eclipse.nebula.widgets.pagination.decorators;

import org.eclipse.nebula.widgets.pagination.AbstractPageControllerComposite;
import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.widgets.Composite;

public class PageComboDecorator extends AbstractPageControllerComposite<PaginationController> {

	public PageComboDecorator(PaginationController controller,
			Composite parent, int style) {
		super(parent, style, controller);
	}

	public void pageIndexChanged(int oldPageIndex, int newPageIndex,
			PaginationController controller) {
		
	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {
		
	}

	public void sortChanged(String oldPopertyName, String propertyName,
			int oldSortDirection, int sortDirection,
			PaginationController paginationController) {
		
	}

	public void pageSizeChanged(int oldPageSize, int newPageSize,
			PaginationController paginationController) {
		
	}

	@Override
	protected void createUI(Composite parent) {
		
	}
}
