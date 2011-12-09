package org.eclipse.nebula.widgets.pagination.spring;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageableController extends PaginationController implements
		Pageable {

	private final Map<String, Sort> sortCache;
	private Sort sort;

	public PageableController(int currentPage, int itemsPerPage) {
		super(currentPage, itemsPerPage);
		this.sortCache = new HashMap<String, Sort>();
		this.sort = null;
	}

	public int getOffset() {
		return getPageOffset();
	}

	public int getPageNumber() {
		return getCurrentPage();
	}

	public Sort getSort() {
		return sort;
	}

	@Override
	public void setSort(String propertyName, int sortDirection) {
		this.sort = getSort(propertyName, sortDirection);
		super.setSort(propertyName, sortDirection);
	}

	protected Sort getSort(String propertyName, int sortDirection) {
		if (propertyName == null) {
			return null;
		}
		String key = getKey(propertyName, sortDirection);
		Sort sort = sortCache.get(key);
		if (sort == null) {
			Direction direction = getDirection(sortDirection);
			sort = new Sort(direction, propertyName);
			sortCache.put(key, sort);
		}
		return sort;
	}

	protected Direction getDirection(int sortDirection) {
		switch (sortDirection) {
		case SWT.UP:
			return Direction.ASC;
		case SWT.DOWN:
			return Direction.DESC;
		}
		return null;
	}

	private String getKey(String propertyName, int sortDirection) {
		StringBuilder key = new StringBuilder();
		key.append(propertyName);
		key.append('_');
		key.append(sortDirection);
		return key.toString();
	}
}
