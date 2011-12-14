package org.eclipse.nebula.widgets.pagination.decorators.draw;

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

public class PageItems extends Canvas {

	private int pageIndexSelected;
	// private int[] pageIndexes;

	private List<PageItem> pageItems;
	private PageItem selectedItem;
	private boolean round = false;

	private Color selectedItemForeground;
	private Color selectedItemBackground;
	private Integer totalWidth;

	public PageItems(Composite parent, int style) {
		super(parent, style);
		// super.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
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
				PageItem s = getItem(e.x, e.y);
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

	}

	private void onPaint(GC gc) {
		gc.setAdvanced(true);
		if (gc.getAdvanced())
			gc.setTextAntialias(SWT.ON);

		int x = 0;
		int y = 0;
		int width = 12;
		int height = 12;

		Color fg = gc.getForeground();
		Color bg = gc.getBackground();

		boolean dot = false;
		String text = null;
		if (pageItems == null) {
			return;
		}
		for (PageItem pageItem : pageItems) {
			dot = pageItem.isDot();
			text = pageItem.getText();
			Point size = gc.stringExtent(text);

			width = size.x + 4;
			height = size.y;
			// Draw rectangle or fill the rectangle.
			if (pageItem.equals(selectedItem)) {
				// Background
				if (selectedItemBackground != null) {
					gc.setBackground(selectedItemBackground);
				} else {
					gc.setBackground(new Color(getDisplay(), 74, 74, 255));// gc.getDevice().getSystemColor(SWT.COLOR_GREEN));
				}
				// Foreground
				if (selectedItemForeground != null) {
					gc.setForeground(selectedItemForeground);

				} else {
					gc.setForeground(gc.getDevice().getSystemColor(
							SWT.COLOR_WHITE));
				}
				if (round) {
					gc.fillRoundRectangle(x, y, width, height, 10, 10);
				} else {
					gc.fillRectangle(x, y, width, height);
				}
			} else {
				gc.setBackground(bg);
				gc.setForeground(fg);
				if (!dot) {
					if (round) {
						gc.drawRoundRectangle(x, y, width, height, 10, 10);
					} else {
						gc.drawRectangle(x, y, width, height);
					}
				}
			}

			if (dot) {
				gc.drawString("...", x + 2, y, true);
			} else {
				gc.drawString(pageItem.getText(), x + 3, y, true);
			}
			pageItem.setBounds(new Rectangle(x, y, width, height));

			x += width + 3;

		}
	}

	private void onResize() {
		// TODO Auto-generated method stub

	}

	public void setIndexes(int[] indexes) {
		this.pageItems = new ArrayList<PageItem>(indexes.length);
		int index = -1;
		for (int i = 0; i < indexes.length; i++) {
			index = indexes[i];
			pageItems.add(new PageItem(this, index));
		}
		this.totalWidth = null;
		//getParent().layout(true);
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

		// return super.computeSize(wHint, 15, changed);
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
		for (PageItem pageItem : pageItems) {
			text = pageItem.getText();
			Point size = gc.stringExtent(text);

			width = size.x + 4;
			height = size.y;

			pageItem.setBounds(new Rectangle(x, y, width, height));
			x += width + 3;
		}
		totalWidth = x+ width -3;
		if (tempGC != null) {
			tempGC.dispose();
		}
	}

	public void setPageIndexSelected(int pageIndexSelected) {
		// this.pageIndexSelected = pageIndexSelected;
		selectedItem = getItem(pageIndexSelected);

		redraw();
	}

	PageItem getItem(int index) {
		if (pageItems == null) {
			return null;
		}
		for (PageItem pageItem : pageItems) {
			if (pageItem.getIndex() == index) {
				return pageItem;
			}
		}
		return null;
	}

	public PageItem getItem(int x, int y) {
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

		for (PageItem pageItem : pageItems) {
			if (pageItem.isContains(x, y)) {
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

	public void select(PageItem pageItem) {
		if (!pageItem.isDot()) {
			selectedItem = pageItem;
			redraw();
			getDisplay().update();
		}

		this.handleSelection(pageItem);
	}

	protected void handleSelection(PageItem pageItem) {

	}

	public void setSelectedItemBackground(Color selectedItemBackground) {
		this.selectedItemBackground = selectedItemBackground;
	}

	public void setSelectedItemForeground(Color selectedItemForeground) {
		this.selectedItemForeground = selectedItemForeground;
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
