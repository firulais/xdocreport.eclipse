package org.eclipse.nebula.widgets.pagination.decorators;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class PageComboDecoratorFactory implements PaginationDecoratorFactory {

	private static final PaginationDecoratorFactory FACTORY = new PageComboDecoratorFactory();

	public static PaginationDecoratorFactory getFactory() {
		return FACTORY;
	}

	public Composite createDecorator(PaginationController controller,
			Composite parent, int style) {
		return new PageComboDecorator(controller, parent, SWT.NONE);
	}
}
