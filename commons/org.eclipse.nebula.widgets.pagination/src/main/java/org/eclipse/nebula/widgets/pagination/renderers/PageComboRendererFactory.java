package org.eclipse.nebula.widgets.pagination.renderers;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class PageComboRendererFactory implements PageRendererFactory {

	private static final PageRendererFactory FACTORY = new PageComboRendererFactory();

	public static PageRendererFactory getFactory() {
		return FACTORY;
	}

	public Composite createRenderer(PaginationController controller,
			Composite parent, int style) {
		return new PageComboRenderer(controller, parent, SWT.NONE);
	}
}
