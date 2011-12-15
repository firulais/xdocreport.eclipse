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

import java.util.Locale;

import org.eclipse.swt.widgets.Composite;

/**
 * 
 * Classes which implement this interface are SWT {@link Composite} which must
 * observe changed of a pagination controller to react to update the UI
 * according the change of the pagination controller. Ex:control to display page
 * navigation, paginated data in a table, etc...
 * 
 * @param <T>
 *            pagination controller.
 */
public abstract class AbstractPageControllerComposite<T extends PaginationController>
		extends Composite implements PageChangedListener<T> {

	public static final int DEFAULT_PAGE_SIZE = 5;

	private PaginationController controller;
	private Locale locale;

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
		PaginationHelper.setController(this, controller);
		if (createUI) {
			createUI(this);
		}
		// add listener from the pagination controller.
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

	@Override
	public void dispose() {
		// remove listener from the pagination controller.
		getController().removePageChangedListener(this);
		super.dispose();
	}

	/**
	 * Set the locale to use for resources.
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * returns the locale to use for resources.
	 * 
	 * @return
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Create the UI content.
	 * 
	 * @param parent
	 */
	protected abstract void createUI(Composite parent);
}
