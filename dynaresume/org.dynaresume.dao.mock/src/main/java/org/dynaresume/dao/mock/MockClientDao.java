package org.dynaresume.dao.mock;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.dao.ClientDao;
import org.dynaresume.domain.project.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository("clientDao")
public class MockClientDao extends AbstractDaoMock<Client> implements ClientDao {

	protected Client clone(Client client) {
		Client newClient = new Client();
		newClient.setId(client.getId());
		newClient.setName(client.getName());
		return newClient;
	}

	public Page<Client> findByNameLike(String name, Pageable pageable) {
		name = Utils.getCriteria(name);
		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();
		Iterable<Client> allClients = findAll();
		List<Client> filteredList = new ArrayList<Client>();
		for (Client client : allClients) {
			if (isClientOK(client, name)) {
				filteredList.add(client);
			}
		}
		long totalSize = filteredList.size();
		List<Client> paginatedList = new ArrayList<Client>();
		for (int i = pageIndex; i < pageIndex + pageSize && i < totalSize; i++) {
			Client client = filteredList.get(i);
			paginatedList.add(client);
		}
		return new PageImpl<Client>(paginatedList, pageable, totalSize);
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
