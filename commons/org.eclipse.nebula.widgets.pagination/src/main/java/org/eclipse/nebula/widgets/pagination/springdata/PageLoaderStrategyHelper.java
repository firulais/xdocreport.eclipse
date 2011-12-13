package org.eclipse.nebula.widgets.pagination.springdata;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.pagination.LazyTableSelectionListener;
import org.eclipse.nebula.widgets.pagination.PageChangedAdapter;
import org.eclipse.nebula.widgets.pagination.PageChangedListener;
import org.eclipse.swt.widgets.TableItem;
import org.springframework.data.domain.Page;

public class PageLoaderStrategyHelper {

	public static void loadPageAndReplaceItems(
			final PageableController controller, final TableViewer viewer,
			final PageLoader pageLoader) {
		Page<?> page = loadPage(pageLoader, controller);
		controller.setTotalElements(page.getTotalElements());
		viewer.setInput(page.getContent());
	}

	public static void loadPageAndAddItems(final PageableController controller,
			final TableViewer viewer, final PageLoader pageLoader) {
		Page<?> page = loadPage(pageLoader, controller);
		controller.setTotalElements(page.getTotalElements());
		if (!page.getContent().isEmpty()) {
			viewer.add(page.getContent().toArray());
			int count = viewer.getTable().getItemCount();
			if (count > 0) {
				TableItem item = viewer.getTable().getItem(count - 1);
				item.setData(LazyTableSelectionListener.LAST_ITEM_LOADED, true);
			}
		}
	}

	public static PageChangedListener<PageableController> createloadPageAndAddItemsListener(
			final PageableController controller, final TableViewer viewer,
			final PageLoader pageLoader) {
		return new PageChangedAdapter<PageableController>() {
			@Override
			public void pageIndexChanged(int oldPageIndex, int newPageIndex,
					PageableController controller) {
				PageLoaderStrategyHelper.loadPageAndAddItems(controller,
						viewer, pageLoader);
			}

			@Override
			public void pageSizeChanged(int oldPageSize, int newPageSize,
					PageableController paginationController) {
				controller.reset();
			}

			@Override
			public void sortChanged(String oldPopertyName, String propertyName,
					int oldSortDirection, int sortDirection,
					PageableController controller) {
				controller.reset();
			}
		};
	}
	
	public static PageChangedListener<PageableController> createloadPageAndReplaceItemsListener(
			final PageableController controller, final TableViewer viewer,
			final PageLoader pageLoader) {
		return new PageChangedAdapter<PageableController>() {
			@Override
			public void pageIndexChanged(int oldPageIndex, int newPageIndex,
					PageableController controller) {
				PageLoaderStrategyHelper.loadPageAndReplaceItems(controller,
						viewer, pageLoader);
			}

			@Override
			public void pageSizeChanged(int oldPageSize, int newPageSize,
					PageableController paginationController) {
				controller.reset();
			}

			@Override
			public void sortChanged(String oldPopertyName, String propertyName,
					int oldSortDirection, int sortDirection,
					PageableController controller) {
				controller.reset();
			}
		};
	}

	public static Page<?> loadPage(PageLoader pageLoader,
			PageableController controller) {
		if (pageLoader == null) {
			throw new RuntimeException("");
		}
		return pageLoader.loadPage(controller);
	}
}
