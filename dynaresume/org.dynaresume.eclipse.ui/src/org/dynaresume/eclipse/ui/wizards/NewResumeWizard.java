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

package org.dynaresume.eclipse.ui.wizards;

import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.eclipse.ui.editors.ResumeEditorInput;
import org.dynaresume.eclipse.ui.editors.ResumeFormEditor;
import org.dynaresume.services.ResumeService;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.ContextHandlerEvent;
import fr.opensagres.xdocreport.eclipse.ui.wizards.AbstractWizard;

public class NewResumeWizard extends AbstractWizard {

	private final Resume resume;
	private final CollaborateurWizardPage collaborateurWizardPage;

	public NewResumeWizard(ExecutionEvent event,
			ContextHandlerEvent contextEvent) {
		super(event, contextEvent);
		resume = new Resume();
		resume.setOwner(new NaturalPerson());
		// Add the pages
		// addPage( new ComplaintsPage() );
		// addPage( new MoreInformationPage() );
		this.collaborateurWizardPage = new CollaborateurWizardPage(
				resume);
		addPage(collaborateurWizardPage);
		setWindowTitle("Create Resume Wizard");
	}

	public boolean canFinish() {
		return getContainer() != null
				&& getContainer().getCurrentPage() instanceof CollaborateurWizardPage;
	}

	@Override
	protected Object getModel() {
		return resume;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model) {
		return new ResumeEditorInput(entry, (Resume) model);
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
		ResumeService resumeService=(ResumeService)PlatformUI.getWorkbench().getService(ResumeService.class);
		resumeService.save(resume);
		collaborateurWizardPage.updateData();
		super.performFinish();
		return true;
	}
}