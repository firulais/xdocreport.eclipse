package org.dynaresume.data;

import java.util.HashSet;

import org.dynaresume.domain.core.Agency;
import org.dynaresume.domain.core.Group;
import org.dynaresume.services.GroupService;

public class GroupsInjector {

	private GroupService groupService;

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	public void inject() {

		// Opensagres
		Group opensagresGroup = createGroup("Opensagres");
		createAgency("Opensagres", opensagresGroup);
		groupService.save(opensagresGroup);

		// Sodifrance
		Group sodifranceGroup = createGroup("Sodifrance");
		createAgency("Brest", sodifranceGroup);
		createAgency("Le Mans", sodifranceGroup);
		createAgency("Nantes", sodifranceGroup);
		createAgency("Lyon", sodifranceGroup);
		createAgency("Aix-en-Provence", sodifranceGroup);
		createAgency("Niort", sodifranceGroup);
		createAgency("Noisy-le-Grand", sodifranceGroup);
		createAgency("Orléans", sodifranceGroup);
		createAgency("Paris", sodifranceGroup);
		createAgency("Rennes", sodifranceGroup);
		createAgency("Tours", sodifranceGroup);
		createAgency("Bruxelles (Belgique)", sodifranceGroup);
		createAgency("Tunis (Tunisie)", sodifranceGroup);
		groupService.save(sodifranceGroup);
	}

	private Group createGroup(String name) {
		Group group = new Group();
		group.setName(name);
		// group.setId(getId());
		group.setSubsidiaries(new HashSet<Agency>());
		// GroupsInjector.groups.put(group.getId(), group);
		return group;
	}

	private Agency createAgency(String name, Group group) {
		Agency agency = new Agency();
		// agency.setId(agency.getId());
		agency.setName(name);
		if (group != null) {
			agency.setGroup(group);
			group.getSubsidiaries().add(agency);
		}
		// GroupsInjector.agencies.put(agency.getId(), agency);
		return agency;
	}

}
