package org.eclipse.nebula.widgets.pagination.renderers.graphics;

import org.eclipse.nebula.widgets.pagination.Resources;
import org.eclipse.swt.graphics.RGB;

public class GreenGraphicsPageConfigurator implements GraphicsPageConfigurator {

	private final static GraphicsPageConfigurator INSTANCE = new GreenGraphicsPageConfigurator();
	private static final RGB GREEN = new RGB(183, 225, 89);
	private static final RGB WHITE = new RGB(255, 255, 255);

	public static GraphicsPageConfigurator getInstance() {
		return INSTANCE;
	}

	public void configure(GraphicsPage page) {
		// Selected item styles
		page.setSelectedItemBackground(Resources.getColor(GREEN));
		page.setSelectedItemForeground(Resources.getColor(WHITE));

		// Item styles
		// page.setItemBorderColor(Resources.getColor(GRAY));
		// page.setItemBackground(Resources.getColor(WHITE));
		page.setItemForeground(Resources.getColor(GREEN));

		// Disabled
		page.setDisabledItemForeground(Resources.getColor(WHITE));
		page.setDisabledItemBorderColor(Resources.getColor(WHITE));
	}

}
