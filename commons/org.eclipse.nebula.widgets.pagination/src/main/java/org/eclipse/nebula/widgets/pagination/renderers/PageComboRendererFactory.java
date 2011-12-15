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
package org.eclipse.nebula.widgets.pagination.renderers;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class PageComboRendererFactory implements PageRendererFactory {

	private static final PageRendererFactory FACTORY = new PageComboRendererFactory();

	public static PageRendererFactory getFactory() {
		return FACTORY;
	}

	public Composite createRenderer(PaginationController controller,
			Composite parent, int style) {
		return new PageComboRenderer(controller, parent, SWT.NONE);
	}
}
