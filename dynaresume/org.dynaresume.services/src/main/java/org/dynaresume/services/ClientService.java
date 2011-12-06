package org.dynaresume.services;

import org.dynaresume.domain.project.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

	Iterable<Client> findAll();

	Client findById(long id);

	Client save(Client group);
	
	Page<Client> findByName(String name, Pageable pageable);
}
