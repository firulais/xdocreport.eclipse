package org.eclipse.nebula.widgets.pagination.spring;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.nebula.widgets.pagination.PageControllerChangedAdapter;
import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.springframework.data.domain.Page;


public abstract class PageableStructuredViewerLoader extends
		PageControllerChangedAdapter {

	private final StructuredViewer viewer;

	public PageableStructuredViewerLoader(StructuredViewer viewer,
			PaginationController controller) {
		this.viewer = viewer;
		controller.addPageSelectionListener(this);
	}

	@Override
	public void pageSelected(int oldPageNumber, int newPageNumber,
			PaginationController controller) {
		Page<?> page = loadPage((PageableController) controller);
		controller.setTotalElements(page.getTotalElements());
		viewer.setInput(page.getContent());
	}

	protected abstract Page<?> loadPage(PageableController controller);

}
