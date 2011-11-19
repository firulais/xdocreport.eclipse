package org.dynaresume.eclipse.ui.handlers;

import org.dynaresume.eclipse.ui.wizards.NewResumeWizard;
import org.eclipse.core.commands.ExecutionEvent;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.ui.handlers.ContextHandlerEvent;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenWizardHandler;
import fr.opensagres.xdocreport.eclipse.ui.wizards.AbstractWizard;

public class NewResumeHandler extends OpenWizardHandler {

	@Override
	protected AbstractWizard createWizard(ExecutionEvent event,
			ContextHandlerEvent contextEvent) {
		try {
			return PlatformXDocReport.getWizardFactory().createWizard(
					NewResumeWizard.ID, NewResumeWizard.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// return new NewResumeWizard(event, contextEvent);
	}
}
