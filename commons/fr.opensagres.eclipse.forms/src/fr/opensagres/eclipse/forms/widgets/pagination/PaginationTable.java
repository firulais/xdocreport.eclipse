package fr.opensagres.eclipse.forms.widgets.pagination;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.eclipse.forms.widgets.BaseComposite;
import fr.opensagres.eclipse.forms.widgets.pagination.spring.PageableController;

public abstract class PaginationTable extends BaseComposite implements
		PageControllerChangedListener {

	private final PaginationController controller;
	protected final TableViewer viewer;

	public PaginationTable(Composite parent, int style, FormToolkit toolkit) {
		super(parent, style, toolkit);
		this.setLayout(new GridLayout());
		this.controller = createController(-1, 5);
		createBannerTop();
		Table table = createTable();
		viewer = new TableViewer(table);
		createBannerBottom();
		controller.addPageSelectionListener(this);
	}

	private void createBannerBottom() {
		PaginationInfoWidget totalItemsWidget = new PaginationInfoWidget(
				controller, this, SWT.NONE, getToolkit());
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalAlignment = GridData.END;
		totalItemsWidget.setLayoutData(data);
	}

	private void createBannerTop() {
		PaginationBannerWidget paginationHeader = new PaginationBannerWidget(
				controller, this, SWT.NONE, getToolkit());
		paginationHeader.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	private Table createTable() {
		Table table = super.createTable(this,
				getTableStyle());
		table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return table;
	}
	
	protected int getTableStyle() {
		return SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL;
	}

	protected PaginationController createController(int pageIndex, int size) {
		return new PageableController(pageIndex, size);
	}

	public TableViewer getViewer() {
		return viewer;
	}

	public PaginationController getController() {
		return controller;
	}

	public void refresh(long totalElements, Object paginatedList) {
		controller.setTotalElements(totalElements);
		viewer.setInput(paginatedList);
	}

	public void pageSelected(int oldPageNumber, int newPageNumber,
			PaginationController controller) {
		refreshPage();
	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {

	}

	public abstract void refreshPage();

	public void setCurrentPage(int currentPage) {
		getController().setCurrentPage(currentPage);
	}
}
