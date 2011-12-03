package org.dynaresume.eclipse.ui.viewers;

import org.dynaresume.domain.hr.SkillCategory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class SkillCategoryTreeContentProvider extends ArrayContentProvider
		implements ITreeContentProvider {

	private static SkillCategoryTreeContentProvider instance;

	public static SkillCategoryTreeContentProvider getInstance() {
		synchronized (SkillCategoryTreeContentProvider.class) {
			if (instance == null) {
				instance = new SkillCategoryTreeContentProvider();
			}
			return instance;
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof SkillsResumeTreeModel) {
			SkillsResumeTreeModel treeModel = ((SkillsResumeTreeModel) inputElement);
			return super.getElements(treeModel.getRootCategories());
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
