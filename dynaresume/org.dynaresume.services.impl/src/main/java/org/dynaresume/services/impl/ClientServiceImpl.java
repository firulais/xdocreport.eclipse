package org.dynaresume.services.impl;

import org.dynaresume.dao.ClientDao;
import org.dynaresume.domain.project.Client;
import org.dynaresume.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("clientService")
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;

	public Iterable<Client> findAll() {
		return clientDao.findAll();
	}

	public Client findById(long id) {
		return clientDao.findOne(id);
	}

	public Client save(Client client) {
		return clientDao.save(client);
	}

	public Page<Client> findByName(String name, Pageable pageable) {
		name=name != null ? name+"%" : "%";
		return clientDao.findByNameLike(name, pageable);
	}
	
	public Iterable<Client> findByName(String name) {
		name=name != null ? name+"%" : "%";
		return clientDao.findByNameLike(name);
	}

	

}
