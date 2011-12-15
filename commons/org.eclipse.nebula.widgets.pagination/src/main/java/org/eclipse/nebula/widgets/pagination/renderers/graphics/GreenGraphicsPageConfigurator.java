package org.eclipse.nebula.widgets.pagination.renderers.graphics;

import org.eclipse.nebula.widgets.pagination.Resources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class GreenGraphicsPageConfigurator implements GraphicsPageConfigurator {

	private final static GraphicsPageConfigurator INSTANCE = new GreenGraphicsPageConfigurator();
	
	private static final RGB DARK_GREEN = new RGB(100, 126, 51);
	private static final RGB LIGHT_GREEN = new RGB(134, 167, 54);
	private static final RGB GREEN = new RGB(121, 152, 55);
	private static final RGB WHITE = new RGB(255, 255, 255);
	private static final RGB ORANGE = new RGB(228,158,22);
	
	
	
	public static GraphicsPageConfigurator getInstance() {
		return INSTANCE;
	}

	public void configure(GraphicsPage page) {
		page.setBackground(Resources.getColor(DARK_GREEN));
		
		// Selected item styles
		page.setSelectedItemBackground(Resources.getColor(ORANGE));
		page.setSelectedItemForeground(Resources.getColor(WHITE));

		// Item styles
		page.setItemBorderColor(Resources.getColor(LIGHT_GREEN));
		page.setItemBackground(Resources.getColor(LIGHT_GREEN));
		page.setItemForeground(Resources.getColor(WHITE));

		// Disabled
		page.setDisabledItemForeground(Resources.getColor(GREEN));
		page.setDisabledItemBorderColor(Resources.getColor(GREEN));
	}

}
