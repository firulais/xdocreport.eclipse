package org.eclipse.nebula.widgets.pagination.renderers.graphics;

import org.eclipse.nebula.widgets.pagination.PaginationHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Item;

public class GraphicsPageItem extends Item {

	public static final int PREVIOUS = PaginationHelper.DOT - 1;
	public static final int NEXT = PaginationHelper.DOT - 2;
	private final int index;

	private Rectangle bounds;
	private boolean enabled;

	public GraphicsPageItem(GraphicsPage parent, int index) {
		super(parent, SWT.NONE);
		this.index = index;
		boolean enabled = true;
		if (isDot()) {
			enabled = false;
			super.setText("...");

		} else if (isPrevious()) {
			super.setText("Previous");
		} else if (isNext()) {
			super.setText("Next");
		} else {
			super.setText((index + 1) + "");
		}
		setEnabled(enabled);
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

	public boolean isPrevious() {
		return index == PREVIOUS;
	}

	public boolean isNext() {
		return index == NEXT;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}
}
