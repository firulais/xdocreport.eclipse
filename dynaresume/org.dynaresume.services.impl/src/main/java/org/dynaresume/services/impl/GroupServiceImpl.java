package org.dynaresume.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.dao.GroupDao;
import org.dynaresume.domain.core.Group;
import org.dynaresume.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;

	public Iterable<Group> findAll() {
		return groupDao.findAll();
	}

	public Group findById(long id) {
		return groupDao.findOne(id);
	}

	public Group save(Group group) {
		return groupDao.save(group);
	}

	public Page<Group> findByName(String name, Pageable pageable) {
		// TODO : manage pagination with the DAO
		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();
		Iterable<Group> allGroups = findAll();
		List<Group> filteredList = new ArrayList<Group>();
		for (Group group : allGroups) {
			if (isGroupOK(group, name)) {
				filteredList.add(group);
			}
		}
		long totalSize = filteredList.size();
		List<Group> paginatedList = new ArrayList<Group>();
		for (int i = pageIndex; i < pageIndex + pageSize && i < totalSize; i++) {
			Group group = filteredList.get(i);
			paginatedList.add(group);
		}
		return new PageImpl<Group>(paginatedList, pageable, totalSize);
	}

	private boolean isGroupOK(Group group, String label) {
		if (label == null) {
			return true;
		}
		if (group.getName() == null) {
			return false;
		}
		return group.getName().toUpperCase().startsWith(label.toUpperCase());
	}

}
