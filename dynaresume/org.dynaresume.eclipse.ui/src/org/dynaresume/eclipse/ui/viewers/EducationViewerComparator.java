package org.dynaresume.eclipse.ui.viewers;

import java.util.Date;

import org.dynaresume.domain.hr.Education;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;


public class EducationViewerComparator extends ViewerComparator {
	
	private static EducationViewerComparator instance;

	public static EducationViewerComparator getInstance() {
		synchronized (EducationViewerComparator.class) {
			if (instance == null) {
				instance = new EducationViewerComparator();
			}
			return instance;
		}
	}
	
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		Date d1 = ((Education) e1).getEndDate();
		Date d2 = ((Education) e2).getEndDate();
		if (d1 == null) {
			return 1;
		}
		if (d2 == null) {
			return 0;
		}
		return d2.compareTo(d1);
	}

}
