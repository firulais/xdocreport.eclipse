package org.dynaresume.project.eclipse.ui.handlers;

import org.dynaresume.project.eclipse.ui.wizards.NewProjectWizard;
import org.eclipse.core.commands.ExecutionEvent;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.ui.handlers.ContextHandlerEvent;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenWizardHandler;
import fr.opensagres.xdocreport.eclipse.ui.wizards.AbstractWizard;

public class NewProjectHandler extends OpenWizardHandler {

	@Override
	protected AbstractWizard createWizard(ExecutionEvent event,
			ContextHandlerEvent contextEvent) {
		try {
			return PlatformXDocReport.getWizardFactory().createWizard(
					NewProjectWizard.ID, NewProjectWizard.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
