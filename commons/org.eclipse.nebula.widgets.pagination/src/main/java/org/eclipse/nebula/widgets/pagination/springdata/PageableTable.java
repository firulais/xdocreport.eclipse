package org.eclipse.nebula.widgets.pagination.springdata;

import org.eclipse.nebula.widgets.pagination.AbstractPaginationTable;
import org.eclipse.nebula.widgets.pagination.renderers.PageRendererFactory;
import org.eclipse.swt.widgets.Composite;

public class PageableTable extends AbstractPaginationTable<PageableController> {

	private PageLoader pageLoader;

	public PageableTable(Composite parent, int compositeStyle, int tableStyle,
			int pageSize) {
		this(parent, compositeStyle, tableStyle, pageSize,
				getDefaultBannerTopFactory(), getDefaultBannerBottomFactory(),
				true);
	}

	public PageableTable(Composite parent, int compositeStyle, int tableStyle,
			int pageSize, PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory) {
		this(parent, compositeStyle, tableStyle, pageSize, bannerTopFactory,
				bannerBottomFactory, true);
	}

	public PageableTable(Composite parent, int compositeStyle, int tableStyle,
			PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory) {
		this(parent, compositeStyle, tableStyle, getDefaultPageSize(),
				bannerTopFactory, bannerBottomFactory, true);
	}

	protected PageableTable(Composite parent, int style, int tableStyle,
			int pageSize, PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory, boolean createUI) {
		super(parent, style, tableStyle, pageSize, bannerTopFactory,
				bannerBottomFactory, createUI);
	}

	@Override
	protected PageableController createController(int size) {
		return new PageableController(size);
	}

	@Override
	public void refreshPage() {
		PageLoaderStrategyHelper.loadPageAndReplaceItems(getController(),
				viewer, pageLoader);
	}

	public void setPageLoader(PageLoader pageLoader) {
		this.pageLoader = pageLoader;
	}

	public PageLoader getPageLoader() {
		return pageLoader;
	}

}
