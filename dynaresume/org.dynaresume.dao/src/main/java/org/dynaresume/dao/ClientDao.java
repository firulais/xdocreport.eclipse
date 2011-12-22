package org.dynaresume.dao;

import java.util.List;

import org.dynaresume.domain.project.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientDao extends PagingAndSortingRepository<Client, Long> {

	Page<Client> findByNameLike(String name, Pageable pageable);

	List<Client> findByNameLike(String name);
}
