package org.springframework.data.domain.collections;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.collections.internal.BeanComparator;

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

	public void sort(List<?> list, Sort sort) {
		Collections.sort(list, new BeanComparator(sort));
	}

}
