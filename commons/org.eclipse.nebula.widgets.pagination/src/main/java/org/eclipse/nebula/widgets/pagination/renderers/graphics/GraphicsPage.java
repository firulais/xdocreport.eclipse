package org.eclipse.nebula.widgets.pagination.renderers.graphics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class GraphicsPage extends Canvas {

	private int pageIndexSelected;
	// private int[] pageIndexes;

	private List<GraphicsPageItem> pageItems;
	private GraphicsPageItem selectedItem;
	private boolean round = false;

	private Color selectedItemForeground;
	private Color selectedItemBackground;
	private Color selectedItemBorderColor;

	private Color itemForeground;
	private Color itemBackground;
	private Color itemBorderColor;

	private Integer totalWidth;

	public GraphicsPage(Composite parent, int style) {
		this(parent, style, BlueGraphicsPageConfigurator.getInstance());
	}

	public GraphicsPage(Composite parent, int style,
			GraphicsPageConfigurator configurator) {
		super(parent, style);
		this.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				onPaint(e.gc);
			}
		});
		this.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				onResize();
			}
		});
		this.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
				GraphicsPageItem s = getItem(e.x, e.y);
				if (s != null) {
					select(s);
				}
				// PShelfItem item = getItem(new Point(1,e.y));
				// if ((item) == null)
				// return;
				// if (item == mouseDownItem && item != openItem){
				// openItem(item,true);
				// }
			}

			public void mouseDown(MouseEvent e) {
				// mouseDownItem = getItem(new Point(1,e.y));
			}

			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});

		if (configurator != null) {
			configurator.configure(this);
		}
	}

	private void onPaint(GC gc) {
		gc.setAdvanced(true);
		if (gc.getAdvanced())
			gc.setTextAntialias(SWT.ON);
		if (pageItems == null) {
			return;
		}
		computeBoundsIfneeded(gc);

		Color fg = gc.getForeground();
		Color bg = gc.getBackground();

		boolean dot = false;
		int x, y, width, height=0;
		boolean selected = false;
		for (GraphicsPageItem pageItem : pageItems) {
			selected = pageItem.equals(selectedItem);
			dot = pageItem.isDot();
			
			x=pageItem.getBounds().x;
			y=pageItem.getBounds().y;
			width=pageItem.getBounds().width;
			height=pageItem.getBounds().height;
			
			if (selected) {
				// Background
				gc.setBackground(selectedItemBackground != null ? selectedItemBackground
						: bg);
				// Foreground
				if (round) {
					gc.fillRoundRectangle(x, y, width, height, 10, 10);
				} else {
					gc.fillRectangle(x, y, width, height);
				}
			} else {
				gc.setForeground(itemBorderColor != null ? itemBorderColor : bg);
				if (!dot) {
					if (round) {
						gc.drawRoundRectangle(x, y, width, height, 10, 10);
					} else {
						gc.drawRectangle(x, y, width, height);
					}
				}
			}

			if (dot) {
				gc.setForeground(itemForeground != null ? itemForeground: fg);
				gc.drawString(pageItem.getText(), x + 3, y, true);
			} else {
				if (selected) {
					gc.setForeground(selectedItemForeground != null ? selectedItemForeground
							: fg);
				} else {
					gc.setForeground(itemForeground != null ? itemForeground: fg);
				}
				gc.drawString(pageItem.getText(), x + 3, y, true);
			}
			pageItem.setBounds(new Rectangle(x, y, width, height));
		}
	}

	private void onResize() {
		// TODO Auto-generated method stub

	}

	public void setIndexes(int[] indexes) {
		this.pageItems = new ArrayList<GraphicsPageItem>(indexes.length);
		int index = -1;
		for (int i = 0; i < indexes.length; i++) {
			index = indexes[i];
			pageItems.add(new GraphicsPageItem(this, index));
		}
		this.totalWidth = null;
		// getParent().layout(true);
	}

	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		checkWidget();

		if (wHint == SWT.DEFAULT || hHint == SWT.DEFAULT) {
			computeBoundsIfneeded(null);
			if (totalWidth != null) {
				return new Point(totalWidth, 15);
			}
			return new Point(wHint, 15);
		}
		return super.computeSize(wHint, hHint, changed);
	}

	private void computeBoundsIfneeded(GC gc) {
		if (pageItems == null || totalWidth != null) {
			return;
		}
		GC tempGC = null;
		if (gc == null) {
			tempGC = new GC(this);
			gc = tempGC;
		}
		this.totalWidth = 0;
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		String text = null;
		for (GraphicsPageItem pageItem : pageItems) {
			text = pageItem.getText();
			Point size = gc.stringExtent(text);

			width = size.x + 4;
			height = size.y;

			pageItem.setBounds(new Rectangle(x, y, width, height));
			x += width + 3;
		}
		totalWidth = x + width - 3;
		if (tempGC != null) {
			tempGC.dispose();
		}
	}

	public void setPageIndexSelected(int pageIndexSelected) {
		// this.pageIndexSelected = pageIndexSelected;
		selectedItem = getItem(pageIndexSelected);

		redraw();
	}

	GraphicsPageItem getItem(int index) {
		if (pageItems == null) {
			return null;
		}
		for (GraphicsPageItem pageItem : pageItems) {
			if (pageItem.getIndex() == index) {
				return pageItem;
			}
		}
		return null;
	}

	public GraphicsPageItem getItem(int x, int y) {
		checkWidget();

		if (pageItems == null) {
			return null;
		}
		int y1 = 0;
		int y2 = 0;
		int c = 0;
		//
		// if (point == null)
		// SWT.error(SWT.ERROR_NULL_ARGUMENT);

		for (GraphicsPageItem pageItem : pageItems) {
			if (pageItem.contains(x, y)) {
				return pageItem;
			}
		}
		// for (int i = 0; i < pageIndexes.length; i++) {
		// if (point.y >= y1 && point.y <= y2 - 1) {
		// return pageIndexes[i];
		// }
		// }
		return null;
	}

	public void select(GraphicsPageItem pageItem) {
		if (!pageItem.isDot()) {
			selectedItem = pageItem;
			redraw();
			getDisplay().update();
		}

		this.handleSelection(pageItem);
	}

	protected void handleSelection(GraphicsPageItem pageItem) {

	}

	public void setSelectedItemBackground(Color selectedItemBackground) {
		this.selectedItemBackground = selectedItemBackground;
	}

	public Color getSelectedItemBackground() {
		return selectedItemBackground;
	}

	public void setSelectedItemForeground(Color selectedItemForeground) {
		this.selectedItemForeground = selectedItemForeground;
	}

	public Color getSelectedItemForeground() {
		return selectedItemForeground;
	}

	public Color getItemForeground() {
		return itemForeground;
	}

	public void setItemForeground(Color itemForeground) {
		this.itemForeground = itemForeground;
	}

	public Color getItemBackground() {
		return itemBackground;
	}

	public void setItemBackground(Color itemBackground) {
		this.itemBackground = itemBackground;
	}

	public Color getItemBorderColor() {
		return itemBorderColor;
	}

	public void setItemBorderColor(Color itemBorderColor) {
		this.itemBorderColor = itemBorderColor;
	}

	public Color getSelectedItemBorderColor() {
		return selectedItemBorderColor;
	}

	public void setSelectedItemBorderColor(Color selectedItemBorderColor) {
		this.selectedItemBorderColor = selectedItemBorderColor;
	}

	// /**
	// * Adds the listener to the collection of listeners who will be notified
	// * when the receiver's selection changes, by sending it one of the
	// messages
	// * defined in the <code>SelectionListener</code> interface.
	// * <p>
	// * When <code>widgetSelected</code> is called, the item field of the event
	// * object is valid. <code>widgetDefaultSelected</code> is not called.
	// * </p>
	// *
	// * @param listener
	// * the listener which should be notified
	// *
	// * @exception IllegalArgumentException
	// * <ul>
	// * <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
	// * </ul>
	// * @exception SWTException
	// * <ul>
	// * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	// * disposed</li>
	// * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	// * thread that created the receiver</li>
	// * </ul>
	// *
	// * @see SelectionListener
	// * @see #removeSelectionListener
	// * @see SelectionEvent
	// */
	// public void addSelectionListener(SelectionListener listener) {
	// checkWidget();
	// if (SWT.getPlatform().equals("rap")) {
	// // SelectionEvent.addListener( this, listener );
	// try {
	// Method m = Class.forName(
	// "org.eclipse.swt.events.SelectionEvent").getMethod(
	// "addListener",
	// Class.forName("org.eclipse.rwt.Adaptable"),
	// SelectionListener.class);
	// m.invoke(this, listener);
	// } catch (Throwable e) {
	// e.printStackTrace();
	// }
	// } else {
	// if (listener == null)
	// SWT.error(SWT.ERROR_NULL_ARGUMENT);
	// TypedListener typedListener = new TypedListener(listener);
	// addListener(SWT.Selection, typedListener);
	// addListener(SWT.DefaultSelection, typedListener);
	// }
	// }
	//
	// /**
	// * Removes the listener from the collection of listeners who will be
	// * notified when the receiver's selection changes.
	// *
	// * @param listener
	// * the listener which should no longer be notified
	// *
	// * @exception IllegalArgumentException
	// * <ul>
	// * <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
	// * </ul>
	// * @exception SWTException
	// * <ul>
	// * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	// * disposed</li>
	// * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	// * thread that created the receiver</li>
	// * </ul>
	// *
	// * @see SelectionListener
	// * @see #addSelectionListener
	// */
	// public void removeSelectionListener(SelectionListener listener) {
	// checkWidget();
	//
	// if (SWT.getPlatform().equals("rap")) {
	// try {
	// Method m = Class.forName(
	// "org.eclipse.swt.events.SelectionEvent").getMethod(
	// "removeListener",
	// Class.forName("org.eclipse.rwt.Adaptable"),
	// SelectionListener.class);
	// m.invoke(this, listener);
	// } catch (Throwable e) {
	// e.printStackTrace();
	// }
	// // SelectionEvent.removeListener(this, listener);
	// } else {
	// if (listener == null)
	// SWT.error(SWT.ERROR_NULL_ARGUMENT);
	// try {
	// Method m = this.getClass().getMethod("removeListener",
	// int.class, SWTEventListener.class);
	// m.invoke(this, SWT.Selection, listener);
	// m.invoke(this, SWT.DefaultSelection, listener);
	// } catch (Throwable e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }
}
