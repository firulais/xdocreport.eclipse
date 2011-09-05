/*******************************************************************************
 * Copyright (c) 2008, 2009 Innoopract Informationssysteme GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Innoopract Informationssysteme GmbH - initial API and implementation
 *     EclipseSource - ongoing development
 ******************************************************************************/

package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.wizards;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.User;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors.ResumeFormEditor;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors.UserEditorInput;
import fr.opensagres.xdocreport.eclipse.demo.resume.services.ServicesProvider;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.ContextHandlerEvent;
import fr.opensagres.xdocreport.eclipse.ui.wizards.AbstractWizard;

public class NewResumeWizard extends AbstractWizard {

	private final User collaborateur;
	private final CollaborateurWizardPage collaborateurWizardPage;

	public NewResumeWizard(ExecutionEvent event,
			ContextHandlerEvent contextEvent) {
		super(event, contextEvent);
		collaborateur = new User();
		// Add the pages
		// addPage( new ComplaintsPage() );
		// addPage( new MoreInformationPage() );
		this.collaborateurWizardPage = new CollaborateurWizardPage(
				collaborateur);
		addPage(collaborateurWizardPage);
		setWindowTitle("Create Resume Wizard");
	}

	public boolean canFinish() {
		return getContainer() != null
				&& getContainer().getCurrentPage() instanceof CollaborateurWizardPage;
	}

	@Override
	protected Object getModel() {
		return collaborateur;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model) {
		return new UserEditorInput(entry, (User) model);
	}

	@Override
	protected String getEditorId() {
		return ResumeFormEditor.ID;
	}

	/**
	 * Called when user clicks Finish
	 * 
	 * @return boolean
	 */
	public boolean performFinish() {
		ServicesProvider.getUserService().createUser(collaborateur);
		collaborateurWizardPage.updateData();
		super.performFinish();
		return true;
	}
}