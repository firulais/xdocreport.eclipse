/*******************************************************************************
 * Copyright (C) 2011 Angelo Zerr <angelo.zerr@gmail.com>, Pascal Leclercq <pascal.leclercq@gmail.com>
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR - initial API and implementation
 *     Pascal Leclercq - initial API and implementation
 *******************************************************************************/
package org.eclipse.nebula.widgets.pagination.springdata.forms;

import org.eclipse.nebula.widgets.pagination.renderers.PageRendererFactory;
import org.eclipse.nebula.widgets.pagination.springdata.PageableTable;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @see http://www.springsource.org/spring-data
 * 
 */
public class FormPageableTable extends PageableTable {

	private final FormToolkit toolkit;

	public FormPageableTable(Composite parent, int style, int tableStyle,
			FormToolkit toolkit, PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory) {
		this(parent, style, tableStyle, toolkit, DEFAULT_PAGE_SIZE,
				bannerTopFactory, bannerBottomFactory);
	}

	public FormPageableTable(Composite parent, int style, int tableStyle,
			FormToolkit toolkit) {
		this(parent, style, tableStyle, toolkit, DEFAULT_PAGE_SIZE,
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
