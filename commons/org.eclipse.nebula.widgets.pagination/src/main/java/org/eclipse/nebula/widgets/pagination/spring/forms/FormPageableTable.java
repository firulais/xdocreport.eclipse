package org.eclipse.nebula.widgets.pagination.spring.forms;

import org.eclipse.nebula.widgets.pagination.banner.PaginationBannerFactory;
import org.eclipse.nebula.widgets.pagination.spring.PageableTable;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class FormPageableTable extends PageableTable {

	private final FormToolkit toolkit;

	public FormPageableTable(Composite parent, int style, int tableStyle,
			FormToolkit toolkit, PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory) {
		this(parent, style, tableStyle, toolkit, getDefaultPageSize(),
				bannerTopFactory, bannerBottomFactory);
	}

	public FormPageableTable(Composite parent, int style, int tableStyle,
			FormToolkit toolkit) {
		this(parent, style, tableStyle, toolkit, getDefaultPageSize(),
				getDefaultBannerTopFactory(), getDefaultBannerBottomFactory());
	}

	public FormPageableTable(Composite parent, int style, int tableStyle,
			FormToolkit toolkit, int pageSize,
			PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory) {
		super(parent, style, tableStyle, pageSize, bannerTopFactory,
				bannerBottomFactory, false);
		this.toolkit = toolkit;
		super.createUI(this);
	}

	@Override
	protected Table createTable(Composite parent, int style) {
		return toolkit.createTable(parent, style);
	}
}
