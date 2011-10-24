package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Diploma;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Experience;

public class DiplomaLabelProvider extends LabelProvider {

	private static DiplomaLabelProvider instance;

	public static DiplomaLabelProvider getInstance() {
		synchronized (DiplomaLabelProvider.class) {
			if (instance == null) {
				instance = new DiplomaLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Diploma) {
			return ((Diploma) element).getLabel();
		}
		return super.getText(element);
	}
}
