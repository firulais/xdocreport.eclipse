package org.eclipse.nebula.widgets.pagination.collections;

import java.util.List;

import org.eclipse.nebula.widgets.pagination.IPageLoader;
import org.eclipse.nebula.widgets.pagination.PageableController;

public class PageLoaderList implements
		IPageLoader<PageableController, PageResult<?>> {

	private List<?> items;

	public PageLoaderList(List<?> items) {
		this.items = items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

	public List<?> getItems() {
		return items;
	}

	public PageResult<?> loadPage(PageableController controller) {
		return PageListHelper.createPage(items, controller);
	}

}
