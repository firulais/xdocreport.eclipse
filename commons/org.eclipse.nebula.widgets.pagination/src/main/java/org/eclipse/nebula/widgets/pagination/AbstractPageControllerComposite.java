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
package org.eclipse.nebula.widgets.pagination;

import org.eclipse.swt.widgets.Composite;

public abstract class AbstractPageControllerComposite<T extends PaginationController>
		extends Composite implements PageChangedListener {

	public static final int DEFAULT_PAGE_SIZE = 5;

	private PaginationController controller;

	public AbstractPageControllerComposite(Composite parent, int style) {
		this(parent, style, null);
	}

	public AbstractPageControllerComposite(Composite parent, int style,
			T controller) {
		this(parent, style, DEFAULT_PAGE_SIZE, controller);
	}

	public AbstractPageControllerComposite(Composite parent, int style,
			int pageSize) {
		this(parent, style, pageSize, null);
	}

	public AbstractPageControllerComposite(Composite parent, int style,
			int pageSize, T controller) {
		this(parent, style, pageSize, controller, true);
	}

	protected AbstractPageControllerComposite(Composite parent, int style,
			int pageSize, T controller, boolean createUI) {
		super(parent, style);
		this.controller = controller != null ? controller
				: createController(pageSize);
		if (createUI) {
			createUI(this);
		}
		this.controller.addPageChangedListener(this);
	}

	protected T createController(int pageSize) {
		return (T) new PaginationController(pageSize);
	}

	public T getController() {
		return (T) controller;
	}

	
	public void setCurrentPage(int currentPage) {
		getController().setCurrentPage(currentPage);
	}

	public static int getDefaultPageSize() {
		return DEFAULT_PAGE_SIZE;
	}

	@Override
	public void dispose() {
		getController().removePageChangedListener(this);
		super.dispose();
	}

	protected abstract void createUI(Composite parent);
}
