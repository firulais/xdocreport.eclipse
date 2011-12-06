package org.dynaresume.project.eclipse.ui.editors.client;

import org.dynaresume.domain.project.Client;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;

public class ClientEditorInput extends ModelAndEntryEditorInput<Client> {

	public ClientEditorInput(IReportModuleEntry entry, Client group) {
		super(entry, group);
	}

	public String getName() {
		return "Clients";
	}

	public String getToolTipText() {
		return getName();
	}

}
