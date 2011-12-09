package org.eclipse.nebula.widgets.pagination;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public abstract class AbstractPageControllerComposite<T extends PaginationController> extends Composite
		implements PageControllerChangedListener {

	public static final int DEFAULT_PAGE_SIZE = 5;

	private final PaginationController controller;

	public AbstractPageControllerComposite(Composite parent, int style) {
		this(parent, style, DEFAULT_PAGE_SIZE);
	}

	public AbstractPageControllerComposite(Composite parent, int style,
			int pageSize) {
		this(parent, style, pageSize, true);
	}

	protected AbstractPageControllerComposite(Composite parent, int style,
			int pageSize, boolean createUI) {
		super(parent, style);
		this.controller = createController(-1, pageSize);
		if (createUI) {
			createUI(this);
		}
		controller.addPageSelectionListener(this);
	}

	protected T createController(int pageIndex, int pageSize) {
		return (T)new PaginationController(pageIndex, pageSize);
	}

	public T getController() {
		return (T)controller;
	}

	public void refresh(long totalElements, Object paginatedList) {
		controller.setTotalElements(totalElements);
	}

	public void pageSelected(int oldPageNumber, int newPageNumber,
			PaginationController controller) {
		refreshPage();
	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {

	}

	public void sortChanged(String oldPopertyName, String propertyName,
			int oldSortDirection, int sortDirection,
			PaginationController paginationController) {		
		refreshPage();		
	}
	public void setCurrentPage(int currentPage) {
		getController().setCurrentPage(currentPage);
	}

	public static int getDefaultPageSize() {
		return DEFAULT_PAGE_SIZE;
	}
	
	public abstract void refreshPage();

	protected abstract void createUI(Composite parent);
}
