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

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.nebula.widgets.pagination.AbstractPageControllerComposite;
import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PageSizeComboRenderer extends
		AbstractPageControllerComposite<PaginationController> implements
		SelectionListener {

	private static class InternalLabelProvider extends LabelProvider {
		private static final ILabelProvider INSTANCE = new InternalLabelProvider();

		public static ILabelProvider getInstance() {
			return INSTANCE;
		}
	}

	private ComboViewer comboViewer;

	public PageSizeComboRenderer(PaginationController controller,
			Composite parent, int style, Integer[] pageSizeList) {
		super(parent, style, controller);
		comboViewer.setInput(pageSizeList);
	}

	public void pageIndexChanged(int oldPageIndex, int newPageIndex,
			PaginationController controller) {
		Integer selected = getSelectedPageSize();
		if (selected == null) {
			selectPageSize(controller.getPageSize());
		}
	}

	private void selectPageSize(int pageSize) {
		comboViewer.setSelection(new StructuredSelection(pageSize));
	}

	private Integer getSelectedPageSize() {
		if (comboViewer.getSelection().isEmpty()) {
			return null;
		}
		return (Integer) ((IStructuredSelection) comboViewer.getSelection())
				.getFirstElement();

	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {
		int totalPages = controller.getTotalPages();
	}

	public void sortChanged(String oldPopertyName, String propertyName,
			int oldSortDirection, int sortDirection,
			PaginationController paginationController) {

	}

	public void pageSizeChanged(int oldPageSize, int newPageSize,
			PaginationController paginationController) {
		selectPageSize(newPageSize);
	}

	@Override
	protected void createUI(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		this.setLayout(layout);

		Label label = new Label(parent, SWT.NONE);
		label.setText("Items per page:");
		label.setLayoutData(new GridData());

		comboViewer = new ComboViewer(parent, SWT.READ_ONLY);
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(InternalLabelProvider.getInstance());
		comboViewer.getCombo().setLayoutData(
				new GridData(GridData.FILL_HORIZONTAL));
		comboViewer.getCombo().addSelectionListener(this);

	}

	@Override
	public void dispose() {
		comboViewer.getCombo().removeSelectionListener(this);
		super.dispose();
	}

	public void widgetDefaultSelected(SelectionEvent e) {

	}

	public void widgetSelected(SelectionEvent e) {
		int pageSize = Integer.parseInt(comboViewer.getCombo().getItem(
				comboViewer.getCombo().getSelectionIndex()));
		getController().setPageSize(pageSize);
	}

}
