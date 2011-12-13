package org.eclipse.nebula.widgets.pagination.decorators;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class PageScaleDecoratorFactory implements PaginationDecoratorFactory {

	private static final PaginationDecoratorFactory FACTORY = new PageScaleDecoratorFactory();

	public static PaginationDecoratorFactory getFactory() {
		return FACTORY;
	}

	public Composite createDecorator(PaginationController controller,
			Composite parent, int style) {
		return new PageScaleDecorator(controller, parent, SWT.NONE);
	}
}
