package org.eclipse.nebula.widgets.pagination.renderers.graphics;

import org.eclipse.nebula.widgets.pagination.Resources;
import org.eclipse.swt.graphics.RGB;

public class BlueGraphicsPageConfigurator implements GraphicsPageConfigurator {

	private final static GraphicsPageConfigurator INSTANCE = new BlueGraphicsPageConfigurator();
	
	private static final RGB BLUE = new RGB(0, 110, 185);
	private static final RGB WHITE = new RGB(255, 255, 255);
	private static final RGB GRAY = new RGB(239, 239, 239);

	public static GraphicsPageConfigurator getInstance() {
		return INSTANCE;
	}

	public void configure(GraphicsPage page) {
		
		// Selected item styles
		page.setSelectedItemBackground(Resources.getColor(BLUE));
		page.setSelectedItemForeground(Resources.getColor(WHITE));
		
		// Item styles
		page.setItemBorderColor(Resources.getColor(GRAY));
		//page.setItemBackground(Resources.getColor(WHITE));
		page.setItemForeground(Resources.getColor(BLUE));
		
		//page.setBackground(Resources.getColor(WHITE));
	}

}
