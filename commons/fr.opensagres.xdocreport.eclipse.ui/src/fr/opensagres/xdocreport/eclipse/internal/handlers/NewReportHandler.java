package fr.opensagres.xdocreport.eclipse.internal.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.opensagres.xdocreport.eclipse.internal.ui.wizards.NewReportWizard;

public class NewReportHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		NewReportWizard wizard = new NewReportWizard();
		WizardDialog dlg = new WizardDialog(window.getShell(), wizard);
		dlg.open();
		return null;
	}

}
