package fr.opensagres.xdocreport.eclipse.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.opensagres.xdocreport.eclipse.ui.wizards.AbstractWizard;

public abstract class OpenWizardHandler extends AbstractContextHandler {

	@Override
	protected Object execute(ExecutionEvent event,
			ContextHandlerEvent contextEvent) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		AbstractWizard wizard = createWizard(event, contextEvent);
		wizard.setEvent(event);
		wizard.setContextEvent(contextEvent);
		WizardDialog dlg = new WizardDialog(window.getShell(), wizard);
		dlg.open();
		return null;
	}

	protected abstract AbstractWizard createWizard(ExecutionEvent event,
			ContextHandlerEvent contextEvent);
}
