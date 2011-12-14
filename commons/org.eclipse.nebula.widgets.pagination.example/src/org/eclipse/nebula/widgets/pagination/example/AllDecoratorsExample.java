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
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.nebula.widgets.pagination.SortTableColumnSelectionListener;
import org.eclipse.nebula.widgets.pagination.decorators.PageComboDecorator;
import org.eclipse.nebula.widgets.pagination.decorators.PageScaleDecorator;
import org.eclipse.nebula.widgets.pagination.decorators.PageSizeComboDecorator;
import org.eclipse.nebula.widgets.pagination.decorators.ResultAndPageButtonsDecorator;
import org.eclipse.nebula.widgets.pagination.decorators.ResultAndPageLinksDecorator;
import org.eclipse.nebula.widgets.pagination.example.model.Address;
import org.eclipse.nebula.widgets.pagination.example.model.Person;
import org.eclipse.nebula.widgets.pagination.springdata.PageLoader;
import org.eclipse.nebula.widgets.pagination.springdata.PageLoaderListImpl;
import org.eclipse.nebula.widgets.pagination.springdata.PageLoaderStrategyHelper;
import org.eclipse.nebula.widgets.pagination.springdata.PageableController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * This sample display a list of model {@link Person} in a SWT Table with
 * pagination banner displayed with Page Results+Page Links on the top of the
 * SWT Table. The 2 columns which display the list of {@link Person} can be
 * clicked to sort the paginated list.
 * 
 */
public class AllDecoratorsExample {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);

		final List<Person> items = createList();

		Composite left = new Composite(shell, SWT.NONE);
		left.setLayoutData(new GridData(GridData.FILL_BOTH));
		left.setLayout(new GridLayout());

		// Left panel
		Table table = new Table(left, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		// 2) Initialize the table viewer + SWT Table
		TableViewer viewer = new TableViewer(table);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new LabelProvider());

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// 3) Create Table columns with sort of paginated list.
		int pageSize = 100;
		final PageableController controller = new PageableController(pageSize);
		final PageLoader pageLoader = new PageLoaderListImpl(items);
		controller.addPageChangedListener(PageLoaderStrategyHelper
				.createloadPageAndReplaceItemsListener(controller, viewer,
						pageLoader));

		createColumns(viewer, controller);

		// Right Panel
		Composite right = new Composite(shell, SWT.NONE);
		right.setLayoutData(new GridData(GridData.FILL_BOTH));
		right.setLayout(new GridLayout());

		PageComboDecorator pageComboDecorator = new PageComboDecorator(
				controller, right, SWT.NONE);
		pageComboDecorator
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		PageScaleDecorator pageScaleDecorator = new PageScaleDecorator(
				controller, right, SWT.NONE);
		pageScaleDecorator
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		ResultAndPageLinksDecorator resultAndPageLinksDecorator = new ResultAndPageLinksDecorator(
				controller, right, SWT.NONE);
		resultAndPageLinksDecorator.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		PageSizeComboDecorator pageSizeComboDecorator = new PageSizeComboDecorator(
				controller, right, SWT.NONE, new Integer[] { 5, 10, 50, 100,
						200 });
		pageSizeComboDecorator.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		ResultAndPageButtonsDecorator resultAndPageButtonsDecorator = new ResultAndPageButtonsDecorator(
				controller, right, SWT.NONE);
		resultAndPageButtonsDecorator.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		// 3) Set current page to 0 to refresh the table

		controller.setCurrentPage(0);

		shell.setSize(800, 250);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private static void createColumns(final TableViewer viewer,
			PageableController controller) {

		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, "Name", 150);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				String p = (String) element;
				return p;
			}
		});
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getName();
			}
		});
		col.getColumn().addSelectionListener(
				new SortTableColumnSelectionListener("name", controller));

		// Second column is for the adress
		col = createTableViewerColumn(viewer, "Adress", 150);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				Address address = p.getAddress();
				if (address == null) {
					return "";
				}
				return address.getName();
			}
		});
		col.getColumn()
				.addSelectionListener(
						new SortTableColumnSelectionListener("address.name",
								controller));
	}

	private static List<Person> createList() {
		List<Person> names = new ArrayList<Person>();
		for (int i = 1; i < 100; i++) {
			names.add(new Person("Name " + i, i < 25 ? "Adress "
					+ Math.random() : null));
		}
		return names;
	}

	private static TableViewerColumn createTableViewerColumn(
			TableViewer viewer, String title, int bound) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

}
