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

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

public class LazyTableSelectionListener extends
		AbstractPageControllerSelectionListener<PaginationController> {

	public static final String LAST_ITEM_LOADED = "___LAST_ITEM_LOADED";

	public LazyTableSelectionListener() {
		super(null);
	}

	public LazyTableSelectionListener(PaginationController controller) {
		super(controller);
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		TableItem item = (TableItem) e.item;
		if (item.getData(LAST_ITEM_LOADED) != null) {
			PaginationController controller = super.getController(item
					.getParent());
			if (controller.hasNextPage()) {
				controller.setCurrentPage(controller.getCurrentPage() + 1);
			}
			item.setData(LAST_ITEM_LOADED, null);
		}
	}
}
