package org.dynaresume.dao.mock;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.dao.GroupDao;
import org.dynaresume.domain.core.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository("groupDao")
public class MockGroupDao extends AbstractDaoMock<Group> implements GroupDao {

	protected Group clone(Group group) {
		Group newGroup = new Group();
		newGroup.setId(group.getId());
		newGroup.setName(group.getName());
		return newGroup;
	}

	public Page<Group> findByNameLike(String name, Pageable pageable) {
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
