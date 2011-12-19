package org.eclipse.nebula.widgets.pagination.springdata;

import java.util.List;

import org.eclipse.nebula.widgets.pagination.IPageContentProvider;
import org.springframework.data.domain.Page;

public class SpringDataPageContentProvider implements
		IPageContentProvider<SpringDataPageableController, Page<?>> {

	private static final IPageContentProvider<SpringDataPageableController, Page<?>> INSTANCE = new SpringDataPageContentProvider();

	public static IPageContentProvider<SpringDataPageableController, Page<?>> getInstance() {
		return INSTANCE;
	}

	public SpringDataPageableController createController(int pageSize) {
		return new SpringDataPageableController(pageSize);
	}

	public long getTotalElements(Page<?> page) {
		return page.getTotalElements();
	}

	public List<?> getPaginatedList(Page<?> page) {
		return page.getContent();
	}

}
