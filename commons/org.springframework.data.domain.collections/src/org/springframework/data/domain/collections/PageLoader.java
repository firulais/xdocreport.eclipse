package org.springframework.data.domain.collections;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageLoader {

	Page<?> loadPage(Pageable pageable);
}
