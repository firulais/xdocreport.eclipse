package fr.opensagres.eclipse.forms.widgets.pagination.spring;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import fr.opensagres.eclipse.forms.widgets.pagination.PaginationController;

public class PageableController extends PaginationController implements
		Pageable {

	public PageableController(int currentPage, int itemsPerPage) {
		super(currentPage, itemsPerPage);
	}

	public int getOffset() {
		return getPageOffset();
	}

	public int getPageNumber() {
		return getCurrentPage();
	}

	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

}
