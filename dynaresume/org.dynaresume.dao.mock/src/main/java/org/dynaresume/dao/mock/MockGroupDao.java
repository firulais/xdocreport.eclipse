package org.dynaresume.dao.mock;

import java.util.Map;

import org.dynaresume.dao.GroupDao;
import org.dynaresume.domain.core.Group;

@org.springframework.stereotype.Repository("groupDao")
public class MockGroupDao extends AbstractDaoMock<Group> implements GroupDao {

	private final Map<Long, Group> groups = GroupsAndAgenciesData.groups;

	public Iterable<Group> findAll() {
		return groups.values();
	}

	public Group findOne(Long id) {
		Group group = groups.get(id);
		if (group != null) {
			return clone(group);
		}
		return groups.get(id);
	}

	public Group save(Group group) {
		if (group.getId() == null) {
			group.setId(GroupsAndAgenciesData.getId());
		}
		groups.put(group.getId(), group);
		return clone(group);
	}

	private Group clone(Group group) {
		Group newGroup = new Group();
		newGroup.setId(group.getId());
		newGroup.setName(group.getName());
		return newGroup;
	}

}
