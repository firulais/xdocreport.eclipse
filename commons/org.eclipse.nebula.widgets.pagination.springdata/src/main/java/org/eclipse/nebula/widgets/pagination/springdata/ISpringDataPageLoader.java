package org.eclipse.nebula.widgets.pagination.springdata;

import org.eclipse.nebula.widgets.pagination.IPageLoader;
import org.springframework.data.domain.Page;

public interface ISpringDataPageLoader<T> extends IPageLoader<Page<T>> {

}
