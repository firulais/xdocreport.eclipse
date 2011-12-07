package org.eclipse.nebula.widgets.pagination.banner;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.widgets.Composite;

public interface PaginationBannerFactory {

	Composite createBanner(PaginationController controller, Composite parent,
			int style);
}
