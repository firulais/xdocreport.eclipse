package org.eclipse.nebula.widgets.pagination.renderers;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ResultAndPageLinksRendererFactory implements PageRendererFactory {

	private static final PageRendererFactory FACTORY = new ResultAndPageLinksRendererFactory();
	
	public static PageRendererFactory getFactory() {
		return FACTORY;
	}
	
	public Composite createRenderer(PaginationController controller,
			Composite parent, int style) {
		return new ResultAndPageLinksRenderer(
				controller, parent, SWT.NONE);		
	}
	
	
}
