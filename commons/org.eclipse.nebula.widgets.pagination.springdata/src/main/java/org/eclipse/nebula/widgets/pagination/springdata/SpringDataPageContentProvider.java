package org.eclipse.nebula.widgets.pagination.springdata;

import java.util.List;

import org.eclipse.nebula.widgets.pagination.IPageContentProvider;
import org.springframework.data.domain.Page;

public class SpringDataPageContentProvider implements IPageContentProvider {

	private static final IPageContentProvider INSTANCE = new SpringDataPageContentProvider();

	public static IPageContentProvider getInstance() {
		return INSTANCE;
	}

	public SpringDataPageableController createController(int pageSize) {
		return new SpringDataPageableController(pageSize);
	}

	public long getTotalElements(Object page) {
		return ((Page<?>) page).getTotalElements();
	}

	public List<?> getPaginatedList(Object page) {
		return ((Page<?>) page).getContent();
	}

}
