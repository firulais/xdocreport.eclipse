package fr.opensagres.xdocreport.eclipse.ui.wizards;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.internal.Activator;
import fr.opensagres.xdocreport.eclipse.ui.handlers.ContextHandlerEvent;

public abstract class AbstractWizard extends Wizard {

	protected final ExecutionEvent event;
	protected final ContextHandlerEvent contextEvent;

	public AbstractWizard(ExecutionEvent event, ContextHandlerEvent contextEvent) {
		this.contextEvent = contextEvent;
		this.event = event;
	}

	public boolean performFinish() {

		try {
			IReportModuleEntry entry = contextEvent.getSourceEntry();
			Activator
					.getActiveWorkbenchWindow()
					.getActivePage()
					.openEditor(createEditorInput(entry, getModel()),
							getEditorId(), true);
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}

		return true;
	}

	protected abstract Object getModel();

	protected abstract String getEditorId();

	protected abstract IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model);

}
