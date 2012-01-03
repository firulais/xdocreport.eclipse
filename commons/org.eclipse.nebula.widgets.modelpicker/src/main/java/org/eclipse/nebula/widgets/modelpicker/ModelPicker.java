package org.eclipse.nebula.widgets.modelpicker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

public class ModelPicker<T> extends Composite implements PropertyChangeListener {

	public static final String MODEL = ModelHolder.MODEL;

	private ModelHolder<T> modelHolder;
	private Menu menu;
	private final int textStyle;

	public ModelPicker(Composite parent, int style, int textStyle) {
		this(parent, style, textStyle, false);
	}

	protected ModelPicker(Composite parent, int style, int textStyle,
			boolean createUI) {
		super(parent, style);
		this.textStyle = textStyle;
		if (createUI) {
			createUI(this);
		}
	}

	protected void createUI(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 1;
		parent.setLayout(layout);

		Text text = createText(parent, textStyle);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL);
		toolBar.setLayoutData(new GridData());

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

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		modelHolder.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		modelHolder.removePropertyChangeListener(listener);
	}

	public void propertyChange(PropertyChangeEvent arg0) {

	}

	public void setModelLabelProvider(IModelLabelProvider modelLabelProvider) {
		this.modelHolder.setModelLabelProvider(modelLabelProvider);
	}

	public IModelLabelProvider getModelLabelProvider() {
		return modelHolder.getModelLabelProvider();
	}
}
