package org.dynaresume.project.eclipse.ui.editors.client;

import org.dynaresume.domain.project.Client;
import org.dynaresume.services.ClientService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;

public class ClientFormEditor extends
		ReportingFormEditor<Client> {

	public static final String ID = "org.dynaresume.project.eclipse.ui.editors.project.client.ClientFormEditor";

	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	protected void doAddPages() {
		try {
			super.addPage(new OverviewPage(this));
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected Client onLoad(ModelAndEntryEditorInput<Client> input) {
		return clientService.findById(
				input.getModel().getId());
	}

	@Override
	protected Client onSave(Client modelObject, IProgressMonitor monitor) {
		return clientService.save(modelObject);
	}
	
	@Override
	protected String getEditorId() {
		return ID;
	}

	
}
