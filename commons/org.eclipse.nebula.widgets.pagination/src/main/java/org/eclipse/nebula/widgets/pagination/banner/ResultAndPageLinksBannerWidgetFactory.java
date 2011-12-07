package org.eclipse.nebula.widgets.pagination.banner;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class ResultAndPageLinksBannerWidgetFactory implements PaginationBannerFactory {

	private static final PaginationBannerFactory FACTORY = new ResultAndPageLinksBannerWidgetFactory();
	
	
	public static PaginationBannerFactory getFactory() {
		return FACTORY;
	}
	
	public Composite createBanner(PaginationController controller,
			Composite parent, int style) {
		ResultAndPageLinksBannerWidget banner = new ResultAndPageLinksBannerWidget(
				controller, parent, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return banner;
	}
	
	
}
