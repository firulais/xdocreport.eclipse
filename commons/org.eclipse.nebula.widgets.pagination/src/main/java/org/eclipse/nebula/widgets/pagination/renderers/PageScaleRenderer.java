package org.eclipse.nebula.widgets.pagination.renderers;

import org.eclipse.nebula.widgets.pagination.AbstractPageControllerComposite;
import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Scale;

public class PageScaleRenderer extends
		AbstractPageControllerComposite<PaginationController> implements
		SelectionListener {

	private Scale pageScale;

	public PageScaleRenderer(PaginationController controller,
			Composite parent, int style) {
		super(parent, style, controller);
	}

	public void pageIndexChanged(int oldPageIndex, int newPageIndex,
			PaginationController controller) {
		int totalPages = controller.getTotalPages();
		pageScale.setMinimum(0);
		if (totalPages > 1) {
			pageScale.setMaximum(totalPages - 1);
		}		
		else {
			pageScale.setMaximum(1);
		}
		pageScale.setPageIncrement(1);
		pageScale.setSelection(newPageIndex);
	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {

		// pageScale.setSelection(controller.getCurrentPage());
	}

	public void sortChanged(String oldPopertyName, String propertyName,
			int oldSortDirection, int sortDirection,
			PaginationController paginationController) {

	}

	public void pageSizeChanged(int oldPageSize, int newPageSize,
			PaginationController controller) {
		int totalPages = controller.getTotalPages();
		pageScale.setMinimum(0);
		if (totalPages > 1) {
			pageScale.setMaximum(totalPages - 1);
		}		
		else {
			pageScale.setMaximum(1);
		}
		pageScale.setPageIncrement(1);
	}

	@Override
	protected void createUI(Composite parent) {
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		this.setLayout(layout);

		pageScale = new Scale(parent, SWT.READ_ONLY);
		pageScale.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		pageScale.addSelectionListener(this);
	}

	@Override
	public void dispose() {
		pageScale.removeSelectionListener(this);
		super.dispose();
	}

	public void widgetDefaultSelected(SelectionEvent e) {

	}

	public void widgetSelected(SelectionEvent e) {
		int newCurrentPage = pageScale.getSelection();
		getController().setCurrentPage(newCurrentPage);
	}
}
