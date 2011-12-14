package org.eclipse.nebula.widgets.pagination.renderers;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.widgets.Composite;

public interface PageRendererFactory {

	Composite createRenderer(PaginationController controller, Composite parent,
			int style);
}
