package org.dynaresume.data;

import org.dynaresume.domain.project.Client;

public class ClientsInjector extends AbstractInjector {

	public ClientsInjector(DataInjector dataInjector) {
		super(dataInjector);
	}

	public void inject() {
		for (int i = 0; i < 1000; i++) {
			addClient("Client" + i);
		}
	}

	private void addClient(String name) {
		Client client = createClient(name);
		save(client);
	}

	private void save(Client client) {
		getDataInjector().getClientService().save(client);
	}

	private Client createClient(String name) {
		Client client = new Client();
		client.setName(name);
		return client;
	}

}
