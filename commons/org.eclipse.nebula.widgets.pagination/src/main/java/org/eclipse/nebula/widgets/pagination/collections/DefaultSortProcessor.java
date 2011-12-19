package org.eclipse.nebula.widgets.pagination.collections;

import java.util.Collections;
import java.util.List;

/**
 * Default implementation of sort.
 * 
 */
public class DefaultSortProcessor implements SortProcessor {

	private static final SortProcessor INSTANCE = new DefaultSortProcessor();

	public static SortProcessor getInstance() {
		return INSTANCE;
	}

	protected DefaultSortProcessor() {

	}

	public void sort(List<?> list, String sortPropertyName, int sortDirection) {
		Collections.sort(list, new BeanComparator(sortPropertyName,
				sortDirection));
	}
}
