package org.eclipse.nebula.widgets.pagination;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class PaginationInfoWidget extends BaseComposite implements
		PageControllerChangedListener {

	private final PaginationController controller;
	private Label totalLabel;

	public PaginationInfoWidget(PaginationController controller,
			Composite parent, int style, FormToolkit toolkit) {
		super(parent, style, toolkit);
		this.controller = controller;
		createUI();
		controller.addPageSelectionListener(this);
	}

	protected void createUI() {
		RowLayout layout = new RowLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		this.setLayout(layout);

		totalLabel = super.createLabel(this, SWT.NONE);
		totalLabel.setLayoutData(new RowData());
		displaysTotal(controller);
	}

	public void pageSelected(int oldPageNumber, int newPageNumber,
			PaginationController controller) {
		displaysTotal(controller);
	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {
		displaysTotal(controller);
	}

	protected void displaysTotal(PaginationController controller) {
		int offset = controller.getPageOffset();
		long totalElements = controller.getTotalElements();
		totalLabel.setText("Total: " + totalElements);
	}

}
