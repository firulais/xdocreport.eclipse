package org.springframework.data.domain.collections;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.collections.internal.BeanComparator;

public class PageListHelper {

	public static Page createPage(List<?> list, Pageable pageable) {
		Sort sort = pageable.getSort();
		if (sort != null) {
			// Sort the list
			Collections.sort(list, new BeanComparator(sort));
		}

		int totalSize = list.size();
		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();

		int fromIndex = pageIndex;
		int toIndex = pageIndex + pageSize;
		if (toIndex > totalSize) {
			toIndex = totalSize;
		}
		List content = list.subList(fromIndex, toIndex);

		// s.subList(fromIndex, toIndex)
		return new PageImpl(content, pageable, totalSize);

	}
}
