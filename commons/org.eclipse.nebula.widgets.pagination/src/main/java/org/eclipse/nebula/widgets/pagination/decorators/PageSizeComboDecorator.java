package org.eclipse.nebula.widgets.pagination.decorators;

import org.eclipse.nebula.widgets.pagination.AbstractPageControllerComposite;
import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Scale;

public class PageSizeComboDecorator extends
		AbstractPageControllerComposite<PaginationController> implements
		SelectionListener {

	private Combo pageScale;

	public PageSizeComboDecorator(PaginationController controller,
			Composite parent, int style) {
		super(parent, style, controller);
	}

	public void pageIndexChanged(int oldPageIndex, int newPageIndex,
			PaginationController controller) {
		
	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {
		int totalPages = controller.getTotalPages();
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
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		this.setLayout(layout);

		pageScale = new Combo(parent, SWT.NONE);
		pageScale.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		pageScale.setItems(new String[] {"10", "50", "100"});
		
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
		int pageSize = Integer.parseInt(pageScale.getItem(pageScale.getSelectionIndex()));
		getController().setPageSize(pageSize);
	}

}
