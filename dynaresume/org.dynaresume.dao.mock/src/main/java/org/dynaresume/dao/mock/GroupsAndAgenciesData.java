package org.dynaresume.dao.mock;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.dynaresume.domain.core.Agency;
import org.dynaresume.domain.core.Group;

public class GroupsAndAgenciesData {

	static long currentId = 0;
	static final Map<Long, Group> groups;
	static final Map<Long, Agency> agencies;

	static {
		groups = new HashMap<Long, Group>();
		agencies = new HashMap<Long, Agency>();

		// Opensagres
		Group opensagresGroup = createGroup("Opensagres");
		createAgency("Opensagres", opensagresGroup);
		
		// Sodifrance
		Group sodifranceGroup = createGroup("Sodifrance");
		createAgency("Sodifrance Lyon", sodifranceGroup);
		createAgency("Sodifrance Rennes", sodifranceGroup);
		createAgency("Sodifrance Nantes", sodifranceGroup);
	}

	private static Group createGroup(String name) {
		Group group = new Group();
		group.setName(name);
		group.setId(getId());
		group.setSubsidiaries(new HashSet<Agency>());
		GroupsAndAgenciesData.groups.put(group.getId(), group);
		return group;
	}

	private static Agency createAgency(String name, Group group) {
		Agency agency = new Agency();
		agency.setId(agency.getId());
		agency.setName(name);
		if (group != null) {
			group.getSubsidiaries().add(agency);
		}
		GroupsAndAgenciesData.agencies.put(agency.getId(), agency);
		return agency;
	}

	public synchronized static Long getId() {
		return currentId++;
	}
}
