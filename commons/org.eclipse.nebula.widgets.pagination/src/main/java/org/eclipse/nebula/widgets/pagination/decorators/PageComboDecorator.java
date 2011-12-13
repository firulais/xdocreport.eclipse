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

public class PageComboDecorator extends
		AbstractPageControllerComposite<PaginationController> implements
		SelectionListener {

	private Combo pageCombo;

	public PageComboDecorator(PaginationController controller,
			Composite parent, int style) {
		super(parent, style, controller);
	}

	public void pageIndexChanged(int oldPageIndex, int newPageIndex,
			PaginationController controller) {

	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {
		int totalPages = controller.getTotalPages();
		String[] items = new String[totalPages];
		for (int i = 0; i < items.length; i++) {
			items[i] = "Page " + i + "/" + (totalPages - 1);
		}
		pageCombo.setItems(items);
		pageCombo.select(controller.getCurrentPage());
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

		pageCombo = new Combo(parent, SWT.READ_ONLY);
		pageCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		pageCombo.addSelectionListener(this);
	}

	@Override
	public void dispose() {
		pageCombo.removeSelectionListener(this);
		super.dispose();
	}

	public void widgetDefaultSelected(SelectionEvent e) {

	}

	public void widgetSelected(SelectionEvent e) {
		int newCurrentPage = pageCombo.getSelectionIndex();
		getController().setCurrentPage(newCurrentPage);
	}
}
