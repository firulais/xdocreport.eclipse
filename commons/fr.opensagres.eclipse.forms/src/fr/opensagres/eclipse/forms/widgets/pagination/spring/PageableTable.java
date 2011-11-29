package fr.opensagres.eclipse.forms.widgets.pagination.spring;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.springframework.data.domain.Page;

import fr.opensagres.eclipse.forms.widgets.pagination.PaginationController;
import fr.opensagres.eclipse.forms.widgets.pagination.PaginationTable;

public abstract class PageableTable extends PaginationTable {

	public PageableTable(Composite parent, int style, int size,
			FormToolkit toolkit) {
		super(parent, style, size, toolkit);
	}

	public PageableTable(Composite parent, int style, FormToolkit toolkit) {
		super(parent, style, toolkit);
	}

	@Override
	protected PaginationController createController(int pageIndex, int size) {
		return new PageableController(pageIndex, size);
	}

	@Override
	public void refreshPage() {
		PageableController controller = (PageableController) getController();
		Page<?> page = loadPage(controller);
		controller.setTotalElements(page.getTotalElements());
		viewer.setInput(page.getContent());
	}

	protected abstract Page<?> loadPage(PageableController controller);

}
