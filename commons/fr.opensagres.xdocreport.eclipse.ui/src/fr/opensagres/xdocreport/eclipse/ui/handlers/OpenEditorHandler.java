package fr.opensagres.xdocreport.eclipse.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;

public abstract class OpenEditorHandler extends AbstractContextHandler {

	@Override
	protected Object execute(ExecutionEvent event,
			ContextHandlerEvent contextEvent) throws ExecutionException {
		try {
			IReportModuleEntry entry = contextEvent.getSourceEntry();
			ContextHandlerUtils.openEditor(event, createEditorInput(entry),
					getEditorId(), true);
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	protected abstract String getEditorId();

	protected abstract IEditorInput createEditorInput(IReportModuleEntry entry);

}
