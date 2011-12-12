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
package org.eclipse.nebula.widgets.pagination.example;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.pagination.decorators.ResultAndPageLinksDecoratorFactory;
import org.eclipse.nebula.widgets.pagination.springdata.PageLoaderListImpl;
import org.eclipse.nebula.widgets.pagination.springdata.PageableTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.springframework.data.domain.collections.PageListHelper;

/**
 * Basic Picture control example.
 * 
 */
public class PageableTableExample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		GridLayout layout = new GridLayout(1, false);
		shell.setLayout(layout);

		final List<String> items = createList();

		int pageSize = 10;
		PageableTable pageableTable = new PageableTable(shell, SWT.BORDER,
				pageSize, ResultAndPageLinksDecoratorFactory.getFactory(), null);
		pageableTable.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		// Initialize PageLoader
		// pageableTable.setPageLoader(new PageLoader() {
		//
		// public Page<?> loadPage(Pageable pageable) {
		// return PageListHelper.createPage(names, pageable);
		// }
		// });

		// 2) Initialize the table viewer
		TableViewer viewer = pageableTable.getViewer();
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new LabelProvider());

		// 3) Set current page to 0 to refresh the table
		pageableTable.setPageLoader(new PageLoaderListImpl(items));
		pageableTable.setCurrentPage(0);

		shell.setSize(350, 250);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private static List<String> createList() {
		List<String> names = new ArrayList<String>();
		for (int i = 1; i < 2012; i++) {
			names.add("Name " + i);
		}
		return names;
	}
}
