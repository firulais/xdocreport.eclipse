package org.eclipse.nebula.widgets.pagination.collections;

import java.util.List;

public class Page<T> {

	private final List<T> content;
	private final long totalElements;

	public Page(List<T> content, long totalElements) {
		this.totalElements = totalElements;
		this.content = content;
	}

	/**
	 * Returns the total amount of elements.
	 * 
	 * @return the total amount of elements
	 */
	public long getTotalElements() {
		return totalElements;
	}

	/**
	 * Returns the page content as {@link List}.
	 * 
	 * @return
	 */
	public List<T> getContent() {
		return content;
	}
}
