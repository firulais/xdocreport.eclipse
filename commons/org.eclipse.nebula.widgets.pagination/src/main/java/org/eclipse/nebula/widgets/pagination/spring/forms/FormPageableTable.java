package org.eclipse.nebula.widgets.pagination.spring.forms;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.nebula.widgets.pagination.PaginationTable;
import org.eclipse.nebula.widgets.pagination.spring.PageableController;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.springframework.data.domain.Page;


public abstract class FormPageableTable extends PaginationTable {

	public FormPageableTable(Composite parent, int style, int size,
			FormToolkit toolkit) {
		super(parent, style, size, toolkit);
	}

	public FormPageableTable(Composite parent, int style, FormToolkit toolkit) {
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
