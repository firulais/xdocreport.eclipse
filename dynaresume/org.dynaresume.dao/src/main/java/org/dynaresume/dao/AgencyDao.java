package org.dynaresume.dao;

import org.dynaresume.domain.core.Agency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AgencyDao extends CrudRepository<Agency, Long>,
		PagingAndSortingRepository<Agency, Long> {

}
