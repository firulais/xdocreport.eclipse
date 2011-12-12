package org.eclipse.nebula.widgets.pagination.spring;

import org.eclipse.nebula.widgets.pagination.AbstractPaginationTable;
import org.eclipse.nebula.widgets.pagination.banner.PaginationBannerFactory;
import org.eclipse.swt.widgets.Composite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.collections.PageLoader;

public class PageableTable extends AbstractPaginationTable<PageableController> {

	private PageLoader pageLoader;

	public PageableTable(Composite parent, int compositeStyle, int tableStyle,
			int pageSize, PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory) {
		this(parent, compositeStyle, tableStyle, pageSize, bannerTopFactory,
				bannerBottomFactory, true);
	}

	public PageableTable(Composite parent, int compositeStyle, int tableStyle,
			PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory) {
		this(parent, compositeStyle, tableStyle, getDefaultPageSize(),
				bannerTopFactory, bannerBottomFactory, true);
	}

	protected PageableTable(Composite parent, int style, int tableStyle,
			int size, PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory, boolean createUI) {
		super(parent, style, tableStyle, size, bannerTopFactory,
				bannerBottomFactory, createUI);
	}

	@Override
	protected PageableController createController(int pageIndex, int size) {
		return new PageableController(pageIndex, size);
	}

	@Override
	public void refreshPage() {
		PageableController controller = (PageableController) getController();
		Page<?> page = loadPage(controller);
		controller.setTotalElements(page.getTotalElements());
		viewer.setInput(page.getContent());
	}

	public void setPageLoader(PageLoader pageLoader) {
		this.pageLoader = pageLoader;
	}

	public PageLoader getPageLoader() {
		return pageLoader;
	}

	protected Page<?> loadPage(PageableController controller) {
		if (pageLoader == null) {
			throw new RuntimeException("");
		}
		return pageLoader.loadPage(controller);
	}
}