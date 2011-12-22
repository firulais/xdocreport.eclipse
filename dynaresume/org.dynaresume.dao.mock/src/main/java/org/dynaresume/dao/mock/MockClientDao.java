package org.dynaresume.dao.mock;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.dao.ClientDao;
import org.dynaresume.domain.project.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.collections.PageListHelper;
import org.springframework.stereotype.Repository;

@Repository("clientDao")
public class MockClientDao extends AbstractDaoMock<Client> implements ClientDao {

	protected Client clone(Client client) {
		Client newClient = new Client();
		newClient.setId(client.getId());
		newClient.setName(client.getName());
		return newClient;
	}

	public List<Client> findByNameLike(String name) {
		name = Utils.getCriteria(name);
		Iterable<Client> allClients = findAll();
		List<Client> filteredList = new ArrayList<Client>();
		for (Client client : allClients) {
			if (isClientOK(client, name)) {
				filteredList.add(client);
			}
		}
		return filteredList;
	}

	public Page<Client> findByNameLike(String name, Pageable pageable) {
		List<Client> clients = findByNameLike(name);
		return PageListHelper.createPage(clients, pageable);
	}

	private boolean isClientOK(Client client, String label) {
		if (label == null) {
			return true;
		}
		if (client.getName() == null) {
			return false;
		}
		return client.getName().toUpperCase().startsWith(label.toUpperCase());
	}

}
