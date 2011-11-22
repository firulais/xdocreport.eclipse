package org.dynaresume.eclipse.ui.viewers;

import java.util.Collection;

import org.dynaresume.domain.hr.SkillCategory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class SkillCategoryContentProvider extends ArrayContentProvider
		implements ITreeContentProvider {

	private static SkillCategoryContentProvider instance;

	public static SkillCategoryContentProvider getInstance() {
		synchronized (SkillCategoryContentProvider.class) {
			if (instance == null) {
				instance = new SkillCategoryContentProvider();
			}
			return instance;
		}
	}

	public Object[] getChildren(Object parentElement) {
		Collection<SkillCategory> children = ((SkillCategory) parentElement)
				.getChildren();
		return super.getElements(children);
	}

	public Object getParent(Object element) {
		return ((SkillCategory) element).getParent();
	}

	public boolean hasChildren(Object element) {
		Collection<SkillCategory> children = ((SkillCategory) element)
				.getChildren();
		return children != null && children.size() > 0;
	}

}
