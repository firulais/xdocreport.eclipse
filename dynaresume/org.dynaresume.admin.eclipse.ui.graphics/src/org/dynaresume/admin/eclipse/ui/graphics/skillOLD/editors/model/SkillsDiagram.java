package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dynaresume.domain.hr.Skill;

public class SkillsDiagram extends ModelElement {

	/** Property ID to use when a child is added to this diagram. */
	public static final String CHILD_ADDED_PROP = "ShapesDiagram.ChildAdded";
	/** Property ID to use when a child is removed from this diagram. */
	public static final String CHILD_REMOVED_PROP = "ShapesDiagram.ChildRemoved";

	private final List<ConnectableNode> children;
	
	private final List<TreeNode> treeChildren;

	public SkillsDiagram(Iterable<Skill> skills) {
		this.children = new ArrayList<ConnectableNode>();
		Map<Skill, ConnectableNode> skillsMapping = new HashMap<Skill, ConnectableNode>();
		for (Skill skill : skills) {
			addSkill(skill, skillsMapping);
		}
		treeChildren = new ArrayList<TreeNode>();
		TreeNode treeNode = new TreeNode();
		treeNode.setLabel("AAA");
		treeChildren.add(treeNode);
	}

	private void addSkill(Skill skill, Map<Skill, ConnectableNode> skillsMapping) {
		GraphicalSkill skillWrapper = getSkillWrapper(skill, skillsMapping);
		if (skill.getParent() != null) {
			GraphicalSkill parentSkillWrapper = getSkillWrapper(
					skill.getParent(), skillsMapping);
			parentSkillWrapper.addChild(skillWrapper);
//			Connection connection = new Connection(skillWrapper,
//					parentSkillWrapper);
			//skillWrapper.addConnection(connection);
			// parentSkillWrapper.addConnection(connection);
		}
	}

	private GraphicalSkill getSkillWrapper(Skill skill,
			Map<Skill, ConnectableNode> skillsMapping) {
		GraphicalSkill skillWrapper = (GraphicalSkill) skillsMapping.get(skill);
		if (skillWrapper == null) {
			skillWrapper = new GraphicalSkill(skill);
			skillsMapping.put(skill, skillWrapper);
			if (skillWrapper.getParent() == null)
			addChild(skillWrapper);
		}
		return skillWrapper;
	}

	public List<ConnectableNode> getChildren() {
		return children;
	}

	public boolean addChild(ConnectableNode child, int index) {
		if (index >= 0) {
			if (child != null) {
				children.add(index, child);
				firePropertyChange(CHILD_ADDED_PROP, null, child);
				return true;
			}
		} else {
			if (child != null && children.add(child)) {
				firePropertyChange(CHILD_ADDED_PROP, null, child);
				return true;
			}
		}
		return false;
	}

	/**
	 * Add a shape to this diagram.
	 * 
	 * @param s
	 *            a non-null shape instance
	 * @return true, if the shape was added, false otherwise
	 */
	public boolean addChild(ConnectableNode child) {
		return addChild(child, -1);
	}

	/**
	 * Remove a shape from this diagram.
	 * 
	 * @param s
	 *            a non-null shape instance;
	 * @return true, if the shape was removed, false otherwise
	 */
	public boolean removeChild(ConnectableNode s) {
		if (s != null && children.remove(s)) {
			firePropertyChange(CHILD_REMOVED_PROP, null, s);
			return true;
		}
		return false;
	}
	
	public List<TreeNode> getTreeChildren() {
		return treeChildren;
	}
}
