package org.eclipse.nebula.widgets.pagination;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

public class LazyTableSelectionListener extends
		AbstractPageControllerSelectionListener<PaginationController> {

	public static final String LAST_ITEM_LOADED = "___LAST_ITEM_LOADED";

	public LazyTableSelectionListener() {
		super(null);
	}

	public LazyTableSelectionListener(PaginationController controller) {
		super(controller);
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		TableItem item = (TableItem) e.item;
		if (item.getData(LAST_ITEM_LOADED) != null) {
			PaginationController controller = super.getController(item
					.getParent());
			if (controller.hasNextPage()) {
				controller.setCurrentPage(controller.getCurrentPage() + 1);
			}
		}
	}
}
