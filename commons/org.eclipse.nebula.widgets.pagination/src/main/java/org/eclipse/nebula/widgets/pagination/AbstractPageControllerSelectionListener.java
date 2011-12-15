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

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Table;

public class AbstractPageControllerSelectionListener<T extends PaginationController>
		extends SelectionAdapter {

	private final T controller;

	public AbstractPageControllerSelectionListener(T controller) {
		this.controller = controller;
	}

	public T getController(Table table) {
		if (controller != null) {
			return controller;
		}
		// 3) Get the pagination controller attached to this table
		AbstractPaginationTable<?> paginationTable = PaginationHelper
				.getPaginationTable(table);
		return (T) paginationTable.getController();
	}

}
