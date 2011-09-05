package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;

import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.wizards.NewResumeWizard;
import fr.opensagres.xdocreport.eclipse.ui.handlers.ContextHandlerEvent;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenWizardHandler;
import fr.opensagres.xdocreport.eclipse.ui.wizards.AbstractWizard;

public class NewResumeHandler extends OpenWizardHandler {

	@Override
	protected AbstractWizard createWizard(ExecutionEvent event,
			ContextHandlerEvent contextEvent) {
		return new NewResumeWizard(event, contextEvent);
	}
}
