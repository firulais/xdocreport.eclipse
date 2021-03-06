package org.dynaresume.eclipse.ui.viewers;

import java.util.Date;

import org.dynaresume.domain.hr.Experience;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;


public class ExperiencesViewerComparator extends ViewerComparator {
	
	private static ExperiencesViewerComparator instance;

	public static ExperiencesViewerComparator getInstance() {
		synchronized (ExperiencesViewerComparator.class) {
			if (instance == null) {
				instance = new ExperiencesViewerComparator();
			}
			return instance;
		}
	}
	
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		Date d1 = ((Experience) e1).getStartDate();
		Date d2 = ((Experience) e2).getStartDate();
		if (d1 == null) {
			return 1;
		}
		if (d2 == null) {
			return 0;
		}
		return d2.compareTo(d1);
	}

}
