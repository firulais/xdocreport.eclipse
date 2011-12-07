package org.eclipse.nebula.widgets.pagination;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.pagination.banner.PaginationBannerFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public abstract class PaginationTable2 extends AbstractPageControllerComposite {

	protected TableViewer viewer;
	private PaginationBannerFactory bannerTopFactory;
	private PaginationBannerFactory bannerBottomFactory;

	public PaginationTable2(Composite parent, int style,
			PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory) {
		super(parent, style);
	}

	public PaginationTable2(Composite parent, int style, int pageSize,
			PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory) {
		super(parent, style, pageSize, false);
		this.bannerTopFactory = bannerTopFactory;
		this.bannerBottomFactory = bannerBottomFactory;
		createUI(parent);
	}

	@Override
	protected void createUI(Composite parent) {
		this.setLayout(new GridLayout());
		createBannerTop();
		Table table = createTable();
		viewer = new TableViewer(table);
		createBannerBottom();
	}

	private void createBannerBottom() {
		PaginationBannerFactory bannerBottomFactory = getBannerBottomFactory();
		if (bannerBottomFactory != null) {
			bannerBottomFactory.createBanner(getController(), this, SWT.NONE);
		}
		// PaginationInfoWidget totalItemsWidget = new PaginationInfoWidget(
		// controller, this, SWT.NONE, getToolkit());
		// GridData data = new GridData(GridData.FILL_HORIZONTAL);
		// data.horizontalAlignment = GridData.END;
		// totalItemsWidget.setLayoutData(data);
	}

	private void createBannerTop() {
		PaginationBannerFactory bannerTopFactory = getBannerTopFactory();
		if (bannerTopFactory != null) {
			bannerTopFactory.createBanner(getController(), this, SWT.NONE);
		}
	}

	protected Table createTable() {
		Table table = super.createTable(this, getTableStyle());
		table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return table;
	}

	protected int getTableStyle() {
		return SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL;
	}

	public TableViewer getViewer() {
		return viewer;
	}

	@Override
	public void refresh(long totalElements, Object paginatedList) {
		super.refresh(totalElements, paginatedList);
		viewer.setInput(paginatedList);
	}
	
	public PaginationBannerFactory getBannerBottomFactory() {
		return bannerBottomFactory;
	}
	
	public PaginationBannerFactory getBannerTopFactory() {
		return bannerTopFactory;
	}
}
