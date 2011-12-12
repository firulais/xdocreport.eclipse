package org.eclipse.nebula.widgets.pagination;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class SortTableColumnSelectionListener extends SelectionAdapter {

	private int sortDirection;
	private final String propertyName;

	public SortTableColumnSelectionListener(String propertyName) {
		this(propertyName, SWT.NONE);
	}

	public SortTableColumnSelectionListener(String propertyName,
			int sortDirection) {
		this.propertyName = propertyName;
		this.sortDirection = sortDirection;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		TableColumn tableColumn = (TableColumn) e.getSource();
		Table table = tableColumn.getParent();
		AbstractPaginationTable<?> paginationTable = PaginationHelper
				.getPaginationTable(table);
		sortDirection = sortDirection == SWT.DOWN ? SWT.UP : SWT.DOWN;
		paginationTable.getController().setSort(propertyName, sortDirection);
		table.setSortColumn(tableColumn);
		table.setSortDirection(sortDirection);

		super.widgetSelected(e);
	}

	public String getPropertyName() {
		return propertyName;
	}
	
	public int getSortDirection() {
		return sortDirection;
	}
}
