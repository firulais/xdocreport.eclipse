package org.eclipse.nebula.widgets.pagination;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public abstract class AbstractPageControllerComposite extends Composite
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

	protected PaginationController createController(int pageIndex, int pageSize) {
		return new PaginationController(pageIndex, pageSize);
	}

	public PaginationController getController() {
		return controller;
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

	public void setCurrentPage(int currentPage) {
		getController().setCurrentPage(currentPage);
	}

	protected Table createTable(Composite parent, int style) {
		return new Table(parent, style);
	}

	public static int getDefaultPageSize() {
		return DEFAULT_PAGE_SIZE;
	}
	
	public abstract void refreshPage();

	protected abstract void createUI(Composite parent);
}
