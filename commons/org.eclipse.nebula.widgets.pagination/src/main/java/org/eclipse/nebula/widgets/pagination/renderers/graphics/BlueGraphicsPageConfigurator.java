package org.eclipse.nebula.widgets.pagination.renderers.graphics;

import org.eclipse.nebula.widgets.pagination.Resources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class BlueGraphicsPageConfigurator implements GraphicsPageConfigurator {

	private final static GraphicsPageConfigurator INSTANCE = new BlueGraphicsPageConfigurator();

	private static final RGB DARK_BLUE = new RGB(0,49,82);
	private static final RGB BLUE = new RGB(148, 148, 231);	
	private static final RGB LIGHT_BLUE = new RGB(222, 239, 247);
	private static final RGB WHITE = new RGB(255, 255, 255);
	private static final RGB ORANGE = new RGB(236, 82, 16);
	private static final RGB GRAY = new RGB(239,237,247);	
	private static final RGB LIGHT_GRAY = new RGB(134, 134, 134);
	
	public static GraphicsPageConfigurator getInstance() {
		return INSTANCE;
	}

	public void configure(GraphicsPage page) {

		page.setBackground(page.getDisplay().getSystemColor(
				SWT.COLOR_WIDGET_BACKGROUND));
		// page.setBackground(Resources.getColor(LIGHT_BLUE));

		// Selected item styles
		page.setSelectedItemBorderColor(Resources.getColor(ORANGE));
		page.setSelectedItemBackground(Resources.getColor(ORANGE));
		page.setSelectedItemForeground(Resources.getColor(WHITE));

		// Item styles
		page.setItemBorderColor(Resources.getColor(BLUE));
		page.setItemBackground(Resources.getColor(LIGHT_BLUE));
		page.setItemForeground(Resources.getColor(DARK_BLUE));

		// Disabled
		page.setDisabledItemBorderColor(Resources.getColor(LIGHT_GRAY));
		page.setDisabledItemForeground(Resources.getColor(LIGHT_GRAY));
		page.setDisabledItemBackground(Resources.getColor(GRAY));
		
		page.setRound(5);
	}

}
