package org.eclipse.nebula.widgets.pagination.decorators.draw;

import org.eclipse.nebula.widgets.pagination.Resources;
import org.eclipse.swt.graphics.RGB;

public class PageItemsThemesHelper {

	private static final RGB BLUE = new RGB(0, 110, 185);
	private static final RGB GREEN = new RGB(183, 225, 89);
	private static final RGB WHITE = new RGB(255, 255, 255);
	
	public static void setBlueTheme(PageItems pageItems) {
		pageItems.setSelectedItemBackground(Resources.getColor(BLUE));
		pageItems.setSelectedItemForeground(Resources.getColor(WHITE));
	}

	public static void setGreenTheme(PageItems pageItems) {
		pageItems.setSelectedItemBackground(Resources.getColor(GREEN));
		pageItems.setSelectedItemForeground(Resources.getColor(WHITE));
	}
}
