package org.eclipse.nebula.widgets.pagination.decorators;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.widgets.Composite;

public interface PaginationDecoratorFactory {

	Composite createDecorator(PaginationController controller, Composite parent,
			int style);
}
