package org.dynaresume.project.eclipse.ui.viewer;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class ProjectDescriptionTreeContentProvider extends ArrayContentProvider
		implements ITreeContentProvider {

	private static ProjectDescriptionTreeContentProvider instance;

	public static ProjectDescriptionTreeContentProvider getInstance() {
		synchronized (ProjectDescriptionTreeContentProvider.class) {
			if (instance == null) {
				instance = new ProjectDescriptionTreeContentProvider();
			}
			return instance;
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// if (inputElement instanceof ProjectDescriptionTypeWrapper) {
		// ProjectDescriptionTypeWrapper treeModel = ((SkillsResumeTreeModel)
		// inputElement);
		// return super.getElements(treeModel.getRootCategories());
		// }
		return super.getElements(inputElement);
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ProjectDescriptionTypeWrapper) {
			return super
					.getElements(((ProjectDescriptionTypeWrapper) parentElement)
							.getDescriptions());
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		// if (element instanceof SkillCategoryWrapper) {
		// return super.getElements(((SkillCategoryWrapper) element)
		// .getParent());
		// }
		// if (element instanceof SkillCategory) {
		// return super.getElements(((SkillCategory) element).getParent());
		// }
		return null;
	}

	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

}
