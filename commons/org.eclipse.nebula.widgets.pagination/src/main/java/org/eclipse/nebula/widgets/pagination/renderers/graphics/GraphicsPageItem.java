package org.eclipse.nebula.widgets.pagination.renderers.graphics;

import org.eclipse.nebula.widgets.pagination.PaginationHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Item;

public class GraphicsPageItem extends Item {

	private final int index;

	private Rectangle bounds;

	public GraphicsPageItem(GraphicsPage parent, int index) {
		super(parent, SWT.NONE);
		this.index = index;
		if (isDot()) {
			super.setText("...");
		} else {
			super.setText((index+1) + "");
		}
	}

	public int getIndex() {
		return index;
	}

	public boolean isDot() {
		return index == PaginationHelper.DOT;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean contains(int x, int y) {
		return bounds.contains(x, y);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
