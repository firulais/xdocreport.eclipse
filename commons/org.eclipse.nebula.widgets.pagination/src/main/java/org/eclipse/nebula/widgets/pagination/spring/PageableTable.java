package org.eclipse.nebula.widgets.pagination.spring;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.nebula.widgets.pagination.PaginationTable2;
import org.eclipse.nebula.widgets.pagination.banner.PaginationBannerFactory;
import org.eclipse.swt.widgets.Composite;
import org.springframework.data.domain.Page;

public class PageableTable extends PaginationTable2 {

	private PageLoader pageLoader;

	public PageableTable(Composite parent, int style, int size,
			PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory) {
		super(parent, style, size, bannerTopFactory, bannerBottomFactory);
	}

	public PageableTable(Composite parent, int style,
			PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory) {
		super(parent, style, bannerTopFactory, bannerBottomFactory);
	}

	@Override
	protected PaginationController createController(int pageIndex, int size) {
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