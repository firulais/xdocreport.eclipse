package org.eclipse.nebula.widgets.pagination;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Table;

public class AbstractPageControllerSelectionListener<T extends PaginationController>
		extends SelectionAdapter {

	private final T controller;

	public AbstractPageControllerSelectionListener(T controller) {
		this.controller = controller;
	}

	public T getController(Table table) {
		if (controller != null) {
			return controller;
		}
		// 3) Get the pagination controller attached to this table
		AbstractPaginationTable<?> paginationTable = PaginationHelper
				.getPaginationTable(table);
		return (T) paginationTable.getController();
	}

}
