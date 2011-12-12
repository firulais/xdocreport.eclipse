/*******************************************************************************
 * Copyright (c) 2008 Innoopract Informationssysteme GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Innoopract Informationssysteme GmbH - initial API and implementation
 ******************************************************************************/

package org.dynaresume.eclipse.ui.wizards;

import org.dynaresume.domain.hr.Resume;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * This page thanks the user for taking the survey
 */
class CollaborateurWizardPage extends WizardPage {

	private final Resume collaborateur;
	private Text lastNameText;
	private Text firstNameText;

	/**
	 * ThanksPage constructor
	 * 
	 * @param collaborateur
	 */
	public CollaborateurWizardPage(Resume collaborateur) {
		super("Thanks");
		this.collaborateur = collaborateur;
		setTitle("Last page");
	}

	/**
	 * Creates the controls for this page
	 */
	public void createControl(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		new Label(composite, SWT.LEFT).setText("First name:");
		firstNameText = new Text(composite, SWT.BORDER);
		firstNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.LEFT).setText("Last name:");
		lastNameText = new Text(composite, SWT.BORDER);
		lastNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		super.setControl(composite);
	}

	public void updateData() {
		collaborateur.getOwner().setFirstName(firstNameText.getText());
		collaborateur.getOwner().setLastName(lastNameText.getText());

	}
}