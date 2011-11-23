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

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof SkillsResumeTreeModel) {
			SkillsResumeTreeModel treeModel = ((SkillsResumeTreeModel) inputElement);
			return super.getElements(treeModel.getCategories());
		}
		return super.getElements(inputElement);
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof SkillCategoryWrapper) {
			return super.getElements(((SkillCategoryWrapper) parentElement)
					.getChildren());
		}
//		if (parentElement instanceof SkillCategory) {
//			return super.getElements(((SkillCategory) parentElement)
//					.getChildren());
//		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		if (element instanceof SkillCategoryWrapper) {
			return super.getElements(((SkillCategoryWrapper) element)
					.getParent());
		}
		if (element instanceof SkillCategory) {
			return super.getElements(((SkillCategory) element).getParent());
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof SkillCategoryWrapper) {
			return ((SkillCategoryWrapper) element).hasChildren();
		}
//		if (element instanceof SkillCategory) {
//			Collection<SkillCategory> children = ((SkillCategory) element)
//					.getChildren();
//			return children != null && children.size() > 0;
//		}
		return false;
	}

}
