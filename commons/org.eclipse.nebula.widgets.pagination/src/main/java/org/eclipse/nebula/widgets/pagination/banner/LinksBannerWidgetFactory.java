package org.eclipse.nebula.widgets.pagination.banner;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class LinksBannerWidgetFactory implements PaginationBannerFactory {

	private static final PaginationBannerFactory FACTORY = new LinksBannerWidgetFactory();
	
	
	public static PaginationBannerFactory getFactory() {
		return FACTORY;
	}
	
	public Composite createBanner(PaginationController controller,
			Composite parent, int style) {
		LinksBannerWidget paginationHeader = new LinksBannerWidget(
				controller, parent, SWT.NONE);
		paginationHeader.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return paginationHeader;
	}
	
	
}
