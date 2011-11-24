package org.dynaresume.eclipse.ui.viewers;

import java.util.ArrayList;
import java.util.Collection;

import org.dynaresume.domain.hr.SkillCategory;
import org.dynaresume.domain.hr.SkillResume;

public class SkillCategoryWrapper {

	private final SkillsResumeTreeModel treeModel;
	private final SkillCategory category;
	private final Collection<Object> children;
	private boolean skillsResumealreadyLoaded;

	public SkillCategoryWrapper(SkillsResumeTreeModel treeModel,
			SkillCategory category) {
		this.treeModel = treeModel;
		this.category = category;
		this.children = new ArrayList<Object>();
		this.skillsResumealreadyLoaded = false;
	}

	public Long getId() {
		return category.getId();
	}

	public String getLabel() {
		return category.getLabel();
	}

	public SkillCategoryWrapper getParent() {
		return treeModel.getCategoryWrapper(category.getParent());
	}

	public Collection<Object> getChildren() {
		if (!skillsResumealreadyLoaded) {
			skillsResumealreadyLoaded = true;
			Collection<SkillResume> skillsResume = treeModel
					.getSkillsResume(category);
			if (skillsResume != null) {
				children.addAll(skillsResume);
			}
		}
		return children;
	}

	public int hashCode() {
		return category.hashCode();
	}

	public String toString() {
		return category.toString();
	}

	public boolean hasChildren() {
		return getChildren().size() > 0;
	}

	public SkillCategory getCategory() {
		return category;
	}

	public void addChild(SkillCategoryWrapper wrapper) {
		children.add(wrapper);
	}

	public void removeChild(SkillResume skill) {
		children.remove(skill);
		treeModel.removeSkill(skill);
	}
}
