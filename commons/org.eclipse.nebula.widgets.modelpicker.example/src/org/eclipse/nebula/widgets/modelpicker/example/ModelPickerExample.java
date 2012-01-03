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
package org.eclipse.nebula.widgets.modelpicker.example;

import org.eclipse.nebula.widgets.modelpicker.ModelPicker;
import org.eclipse.nebula.widgets.modelpicker.ModelPropertyChangeListener;
import org.eclipse.nebula.widgets.modelpicker.example.model.Person;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;

/**
 * ModelPicker control example.
 * 
 */
public class ModelPickerExample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);

		final Link link = new Link(shell, SWT.NONE);
		link.setText("<a href=\"#\">Person</a>:");
		GridData gridData = new GridData();
		link.setLayoutData(gridData);

		final ModelPicker<Person> personPicker = new ModelPicker<Person>(shell,
				SWT.NONE, SWT.BORDER);
		personPicker.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		personPicker.addSearcher(new PersonCompletionSearcher(), "Ctrl+Space",
				"Autocomplete", null);
		personPicker.addSearcher(new PersonSearchDialogSearcher(), "F5",
				"Search dialog", null);

		new ModelPropertyChangeListener<Person>(personPicker) {
			@Override
			protected void setModelEnabled(boolean enabled) {
				link.setEnabled(enabled);
			}
		};

		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.err.println(personPicker.getModel());
			}
		});

		shell.setSize(200, 150);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
