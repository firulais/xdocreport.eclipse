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
package org.eclipse.nebula.widgets.pagination.renderers.pagesize;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.nebula.widgets.pagination.renderers.CompositeRendererFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * Renderer factory to create instance of {@link PageSizeComboRenderer}.
 * 
 */
public class PageSizeComboRendererFactory implements CompositeRendererFactory {

	private static final CompositeRendererFactory FACTORY = new PageSizeComboRendererFactory(
			new Integer[] { 5, 10, 100 });

	public static CompositeRendererFactory getFactory() {
		return FACTORY;
	}

	private final Integer[] pageSizeList;

	public PageSizeComboRendererFactory(Integer[] pageSizeList) {
		this.pageSizeList = pageSizeList;
	}

	public Composite createComposite(Composite parent, int style,
			PaginationController controller) {
		return new PageSizeComboRenderer(parent, SWT.NONE, controller,
				pageSizeList);
	}

}
