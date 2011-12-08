package org.eclipse.nebula.widgets.pagination;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.pagination.banner.PaginationBannerFactory;
import org.eclipse.nebula.widgets.pagination.banner.ResultAndPageLinksBannerWidgetFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public abstract class PaginationTable extends AbstractPageControllerComposite {

	protected TableViewer viewer;
	private PaginationBannerFactory bannerTopFactory;
	private PaginationBannerFactory bannerBottomFactory;
	private int tableStyle = SWT.BORDER | SWT.MULTI | SWT.H_SCROLL
			| SWT.V_SCROLL;

	public PaginationTable(Composite parent, int style, int tableStyle,
			PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory) {
		this(parent, tableStyle, style, getDefaultPageSize(), bannerTopFactory,
				bannerBottomFactory, true);
	}

	public PaginationTable(Composite parent, int style, int tableStyle,
			int pageSize, PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory) {
		this(parent, tableStyle, style, pageSize, bannerTopFactory,
				bannerBottomFactory, true);
	}

	protected PaginationTable(Composite parent, int style, int tableStyle,
			int pageSize, PaginationBannerFactory bannerTopFactory,
			PaginationBannerFactory bannerBottomFactory, boolean createUI) {
		super(parent, style, pageSize, false);
		this.tableStyle = tableStyle;
		this.bannerTopFactory = bannerTopFactory;
		this.bannerBottomFactory = bannerBottomFactory;
		if (createUI) {
			createUI(parent);
		}
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
		return tableStyle;
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

	public static PaginationBannerFactory getDefaultBannerTopFactory() {
		return ResultAndPageLinksBannerWidgetFactory.getFactory();
	}

	public static PaginationBannerFactory getDefaultBannerBottomFactory() {
		return null;
	}
}
