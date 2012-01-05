package org.dynaresume.data;

import java.util.HashMap;
import java.util.Map;

import org.dynaresume.domain.project.Client;

public class ClientsInjector extends AbstractInjector {

	private Map<String, Client> clientsCache = new HashMap<String, Client>();

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
		Client newClient = getDataInjector().getClientService().save(client);
		clientsCache.put(newClient.getName(), newClient);
	}

	private Client createClient(String name) {
		Client client = new Client();
		client.setName(name);
		return client;
	}

	public Client getClient(String name) {
		return clientsCache.get(name);
	}

}
