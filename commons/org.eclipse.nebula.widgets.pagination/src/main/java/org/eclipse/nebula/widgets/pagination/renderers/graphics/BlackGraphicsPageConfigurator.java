package org.eclipse.nebula.widgets.pagination.renderers.graphics;

import org.eclipse.nebula.widgets.pagination.Resources;
import org.eclipse.swt.graphics.RGB;

public class BlackGraphicsPageConfigurator implements GraphicsPageConfigurator {

	private final static GraphicsPageConfigurator INSTANCE = new BlackGraphicsPageConfigurator();

	private static final RGB ORANGE = new RGB(236, 82, 16);
	private static final RGB WHITE = new RGB(255, 255, 255);
	private static final RGB BIG_DARK_GRAY = new RGB(49,49,49);
	private static final RGB DARK_GRAY = new RGB(62,62,62);
	private static final RGB LIGHT_GRAY = new RGB(134,134,134);

	public static GraphicsPageConfigurator getInstance() {
		return INSTANCE;
	}

	public void configure(GraphicsPage page) {

		// Selected item styles
		page.setSelectedItemBackground(Resources.getColor(ORANGE));
		page.setSelectedItemForeground(Resources.getColor(WHITE));

		// Item styles
		page.setItemBorderColor(Resources.getColor(DARK_GRAY));
		page.setItemBackground(Resources.getColor(DARK_GRAY));
		page.setItemForeground(Resources.getColor(WHITE));
		// page.setItemBackground(Resources.getColor(WHITE));
		// page.setItemForeground(Resources.getColor(BLUE));

		// Disabled
		page.setDisabledItemForeground(Resources.getColor(LIGHT_GRAY));
		page.setDisabledItemBorderColor(Resources.getColor(LIGHT_GRAY));
		//page.setDisabledItemBorderColor(Resources.getColor(GRAY));
		// page.setBackground(Resources.getColor(WHITE));
		
		page.setBackground(Resources.getColor(BIG_DARK_GRAY));
	}

}
