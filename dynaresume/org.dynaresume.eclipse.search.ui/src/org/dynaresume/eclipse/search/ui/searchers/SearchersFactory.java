package org.dynaresume.eclipse.search.ui.searchers;

import org.dynaresume.services.ClientService;

public class SearchersFactory {

	public static ClientsSearcher createClientsSearcher(
			ClientService clientService) {
		ClientsSearcher searcher = new ClientsSearcher();
		searcher.setClientService(clientService);
		return searcher;
	}
}
