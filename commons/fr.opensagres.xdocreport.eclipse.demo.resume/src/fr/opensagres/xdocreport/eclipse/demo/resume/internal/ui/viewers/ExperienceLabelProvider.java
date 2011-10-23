package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Experience;

public class ExperienceLabelProvider extends LabelProvider {

	private static ExperienceLabelProvider instance;

	public static ExperienceLabelProvider getInstance() {
		synchronized (ExperienceLabelProvider.class) {
			if (instance == null) {
				instance = new ExperienceLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Experience) {
			return ((Experience) element).getProjectName();
		}
		return super.getText(element);
	}
}
