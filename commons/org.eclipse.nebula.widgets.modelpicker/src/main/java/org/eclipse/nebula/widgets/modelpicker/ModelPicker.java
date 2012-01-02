package org.eclipse.nebula.widgets.modelpicker;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ModelPicker<T> extends Composite {

	private ModelHolder<T> modelHolder;
	private Menu menu;

	public ModelPicker(Composite parent, int style, int textStyle) {
		super(parent, style);
		createUI(this, textStyle);
	}

	protected void createUI(Composite parent, int textStyle) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);

		Text text = createText(parent, textStyle);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL);
		menu = new Menu(getShell(), SWT.POP_UP);
		final ToolItem item = new ToolItem(toolBar, SWT.DROP_DOWN);
		item.setText("Browse");
		item.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {

				if (event.detail == SWT.ARROW) {
					Point point = new Point(event.x, event.y);
					point = getDisplay().map(toolBar, null, point);
					menu.setLocation(point);
					menu.setVisible(true);
				} else {
					menu.getItem(0).notifyListeners(SWT.Selection, event);
				}
			}

		});

		modelHolder = new ModelHolder<T>(text);
	}

	public void setModel(T model) {
		modelHolder.setModel(model);
	}

	public T getModel() {
		return modelHolder.getModel();
	}

	public SearcherDescriptor addSearcher(ISearcher<T> initializer,
			String keyStroke, String description, Image icon) {
		SearcherDescriptor descriptor = modelHolder.addSearcher(initializer,
				keyStroke, description, icon);
		createMenuItem(descriptor);
		return descriptor;
	}

	public SearcherDescriptor addSearcher(SearcherDescriptor descriptor) {
		createMenuItem(descriptor);
		return modelHolder.addSearcher(descriptor);
	}

	private void createMenuItem(final SearcherDescriptor descriptor) {
		final MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
		menuItem.setText(descriptor.getKeyStroke() + ":"
				+ descriptor.getDescription());
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				descriptor.handle();
			}
		});
	}

	public void removeSearcher(SearcherDescriptor searcher) {
		modelHolder.removeSearcher(searcher);
	}

	protected Text createText(Composite parent, int textStyle) {
		return new Text(parent, textStyle);
	}

	public Text getText() {
		return modelHolder.getText();
	}

	@Override
	public void dispose() {
		modelHolder.dispose();
		super.dispose();
	}
}
