package org.eclipse.nebula.widgets.pagination.spring;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PageLoaderListImpl implements PageLoader {

	private List<?> items;

	public PageLoaderListImpl(List<?> items) {
		this.items = items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

	public List<?> getItems() {
		return items;
	}

	public Page<?> loadPage(Pageable pageable) {
		return PageListHelper.createPage(items, pageable);
	}

}
