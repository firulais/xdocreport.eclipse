package org.eclipse.nebula.widgets.pagination.collections;

import java.util.List;

import org.eclipse.nebula.widgets.pagination.PageableController;
import org.eclipse.swt.SWT;

public class PageListHelper {

	public static Page<?> createPage(List<?> list,
			PageableController controller) {
		return createPage(list, controller, DefaultSortProcessor.getInstance());
	}

	public static Page<?> createPage(List<?> list,
			PageableController controller, SortProcessor processor) {
		int sortDirection = controller.getSortDirection();
		if (sortDirection != SWT.NONE) {
			// Sort the list
			processor.sort(list, controller.getSortPropertyName(),
					sortDirection);
		}
		int totalSize = list.size();
		int pageSize = controller.getPageSize();
		int pageIndex = controller.getPageOffset();

		int fromIndex = pageIndex;
		int toIndex = pageIndex + pageSize;
		if (toIndex > totalSize) {
			toIndex = totalSize;
		}
		List content = list.subList(fromIndex, toIndex);
		return new Page(content, totalSize);
	}

}
