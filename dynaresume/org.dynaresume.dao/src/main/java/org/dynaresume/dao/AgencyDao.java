package org.dynaresume.dao;

import org.dynaresume.domain.core.Agency;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AgencyDao extends PagingAndSortingRepository<Agency, Long> {

}
