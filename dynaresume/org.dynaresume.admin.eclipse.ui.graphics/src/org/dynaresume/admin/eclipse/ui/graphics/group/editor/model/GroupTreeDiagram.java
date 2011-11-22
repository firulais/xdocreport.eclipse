package org.dynaresume.admin.eclipse.ui.graphics.group.editor.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.dynaresume.domain.core.Agency;
import org.dynaresume.domain.core.Group;

import fr.opensagres.eclipse.gef.tree.editor.model.TreeDiagram;
import fr.opensagres.eclipse.gef.tree.editor.model.TreeNode;

public class GroupTreeDiagram extends TreeDiagram {

	public GroupTreeDiagram(Iterable<Group> groups) {
		super();
		setLabel("Root");
		Map<Object, TreeNode> mappings = new HashMap<Object, TreeNode>();
		for (Group group : groups) {
			addGroup(group, mappings);
		}
	}

	private void addGroup(Group group, Map<Object, TreeNode> mappings) {
		TreeNode groupWrapper = getGroupTreeNode(group, mappings);
		Set<Agency> subsidiaries = group.getSubsidiaries();
		if (subsidiaries != null) {
			for (Agency agency : subsidiaries) {
				getAgencyTreeNode(agency, mappings);
			}
		}
	}

	private TreeNode getAgencyTreeNode(Agency agency,
			Map<Object, TreeNode> mappings) {
		TreeNode agencyTreeNode = (TreeNode) mappings.get(agency);
		if (agencyTreeNode == null) {
			agencyTreeNode = new AgencyTreeNode(agency);
			mappings.put(agency, agencyTreeNode);

			TreeNode parentTreeNode = null;
			if (agency.getGroup() == null) {
				parentTreeNode = this;
			} else {
				parentTreeNode = getGroupTreeNode(agency.getGroup(), mappings);
			}
			parentTreeNode.addChild(agencyTreeNode);
		}
		return agencyTreeNode;

	}

	private TreeNode getGroupTreeNode(Group group,
			Map<Object, TreeNode> groupsMapping) {
		TreeNode groupWrapper = (TreeNode) groupsMapping.get(group);
		if (groupWrapper == null) {
			groupWrapper = new GroupTreeNode(group);
			groupsMapping.put(group, groupWrapper);
			if (groupWrapper.getParent() == null)
				addChild(groupWrapper);
		}
		return groupWrapper;
	}
}
