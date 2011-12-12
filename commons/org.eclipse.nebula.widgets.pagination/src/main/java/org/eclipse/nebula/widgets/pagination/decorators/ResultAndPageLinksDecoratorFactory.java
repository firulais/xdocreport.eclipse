package org.eclipse.nebula.widgets.pagination.decorators;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class ResultAndPageLinksDecoratorFactory implements PaginationDecoratorFactory {

	private static final PaginationDecoratorFactory FACTORY = new ResultAndPageLinksDecoratorFactory();
	
	
	public static PaginationDecoratorFactory getFactory() {
		return FACTORY;
	}
	
	public Composite createDecorator(PaginationController controller,
			Composite parent, int style) {
		ResultAndPageLinksDecorator banner = new ResultAndPageLinksDecorator(
				controller, parent, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return banner;
	}
	
	
}
