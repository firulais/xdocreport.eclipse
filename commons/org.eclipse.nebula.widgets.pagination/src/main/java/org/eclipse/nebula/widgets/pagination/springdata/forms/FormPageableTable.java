package org.eclipse.nebula.widgets.pagination.springdata.forms;

import org.eclipse.nebula.widgets.pagination.renderers.PageRendererFactory;
import org.eclipse.nebula.widgets.pagination.springdata.PageableTable;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class FormPageableTable extends PageableTable {

	private final FormToolkit toolkit;

	public FormPageableTable(Composite parent, int style, int tableStyle,
			FormToolkit toolkit, PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory) {
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
			PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory) {
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
