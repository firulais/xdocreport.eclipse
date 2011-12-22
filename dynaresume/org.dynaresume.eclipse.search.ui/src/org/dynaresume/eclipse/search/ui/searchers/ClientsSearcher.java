package org.dynaresume.eclipse.search.ui.searchers;

import org.dynaresume.services.ClientService;

import fr.opensagres.eclipse.forms.widgets.ISearcher;

public class ClientsSearcher implements ISearcher {

	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public Iterable<?> search(String contents, int position) {
		return clientService.findByName(contents);
	}
}
