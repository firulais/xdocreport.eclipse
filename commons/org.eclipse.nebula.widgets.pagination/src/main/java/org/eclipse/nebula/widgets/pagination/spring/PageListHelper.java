package org.eclipse.nebula.widgets.pagination.spring;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PageListHelper {

	public static PageLoader createPageLoader(List<?> items) {
		return new PageLoaderListImpl(items);
	}
	
	public static Page createPage(List<?> list, Pageable pageable) {
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
