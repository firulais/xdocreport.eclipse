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
import org.eclipse.nebula.widgets.pagination.renderers.GraphicsPageRendererFactory;
import org.eclipse.nebula.widgets.pagination.renderers.ResultAndPageButtonsRenderer;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.BlackGraphicsPageConfigurator;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.BlueGraphicsPageConfigurator;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.GreenGraphicsPageConfigurator;
import org.eclipse.nebula.widgets.pagination.springdata.PageLoaderListImpl;
import org.eclipse.nebula.widgets.pagination.springdata.PageableTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This sample display a list of String in a SWT Table with pagination banner
 * displayed with Page Results+Page Links on the top of the SWT Table.
 * 
 */
public class GraphicsPagePageableTableExample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		GridLayout layout = new GridLayout(1, false);
		shell.setLayout(layout);

		final List<String> items = createList();

		// 1) Create pageable table with 10 items per page
		// This SWT Component create internally a SWT Table+JFace TreeViewer
		int pageSize = 10;
		final PageableTable pageableTable = new PageableTable(shell,
				SWT.BORDER, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL
						| SWT.V_SCROLL, pageSize,
				GraphicsPageRendererFactory.getBlueFactory(), null);
		pageableTable.setLayoutData(new GridData(GridData.FILL_BOTH));

		// 2) Initialize the table viewer
		TableViewer viewer = pageableTable.getViewer();
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new LabelProvider());

		// 3) Set the page loader used to load a page (sublist of String)
		// according the page index selected, the page size etc.
		pageableTable.setPageLoader(new PageLoaderListImpl(items));

		// 4) Set current page to 0 to display the first page
		pageableTable.setCurrentPage(0);

		final Combo styleCombo = new Combo(shell, SWT.READ_ONLY);
		styleCombo.setItems(new String[] { "Blue", "Green", "Black" });
		styleCombo.select(0);
		styleCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (styleCombo.getText().equals("Blue")) {
					((ResultAndPageButtonsRenderer) pageableTable
							.getBannerTop()).getGraphicsPage().setConfigurator(
							BlueGraphicsPageConfigurator.getInstance());
				} else if (styleCombo.getText().equals("Green")) {
					((ResultAndPageButtonsRenderer) pageableTable
							.getBannerTop()).getGraphicsPage().setConfigurator(
							GreenGraphicsPageConfigurator.getInstance());
				} else {
					((ResultAndPageButtonsRenderer) pageableTable
							.getBannerTop()).getGraphicsPage().setConfigurator(
							BlackGraphicsPageConfigurator.getInstance());
				}

			}
		});

		shell.setSize(350, 250);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * Create a static list.
	 * 
	 * @return
	 */
	private static List<String> createList() {
		List<String> names = new ArrayList<String>();
		for (int i = 1; i < 2012; i++) {
			names.add("Name " + i);
		}
		return names;
	}
}
