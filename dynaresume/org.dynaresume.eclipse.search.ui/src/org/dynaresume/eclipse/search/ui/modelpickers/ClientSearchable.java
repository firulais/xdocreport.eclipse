package org.dynaresume.eclipse.search.ui.modelpickers;

import org.dynaresume.services.ClientService;
import org.eclipse.nebula.widgets.modelpicker.ISearchable;

public class ClientSearchable implements ISearchable {

	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public Iterable<?> search(String contents, int position) {
		return clientService.findByName(contents);
	}

}
