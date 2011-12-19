package org.eclipse.nebula.widgets.pagination;

import java.util.List;

import org.eclipse.nebula.widgets.pagination.collections.Page;

public class PageContentProvider implements
		IPageContentProvider<PageableController, Page<?>> {

	private static final IPageContentProvider<PageableController, Page<?>> INSTANCE = new PageContentProvider();

	public static IPageContentProvider<PageableController, Page<?>> getInstance() {
		return INSTANCE;
	}

	public PageableController createController(int pageSize) {
		return new PageableController(pageSize);
	}

	public long getTotalElements(Page<?> page) {
		return page.getTotalElements();
	}

	public List<?> getPaginatedList(Page<?> page) {
		return page.getContent();
	}

}
