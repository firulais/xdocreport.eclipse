package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
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
import org.eclipse.swt.widgets.TypedListener;

public class SearchControl extends Composite {

	private Text searchText;
	private int textStyle;
	private SearchContentProposalAdapter adapter;
	private ControlDecoration deco;

	public SearchControl(Composite parent, int style, int textStyle) {
		this(parent, style, textStyle, true);
	}

	protected SearchControl(Composite parent, int style, int textStyle,
			boolean createUI) {
		super(parent, style);
		this.textStyle = textStyle;
		if (createUI) {
			createUI(this);
		}
	}

	protected void createUI(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		//layout.marginWidth = 1;
		// layout.marginHeight = 1;
		// layout.horizontalSpacing = 0;

		this.setLayout(layout);

		searchText = createText(parent, getTextStyle());
		searchText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createDecoration();
		createContentAssist();

		// Button button = new Button(parent, SWT.NONE);
		// button.setText("Browse");
		// button.setLayoutData(new GridData());
		// button.addSelectionListener(new SelectionAdapter() {
		// @Override
		// public void widgetSelected(SelectionEvent e) {
		// adapter.forceOpenProposalPopup();
		// }
		// });

		// Menu menu = new Menu(parent);
		// ActionContributionItem item = new ActionContributionItem(new Action()
		// {
		// @Override
		// public String getText() {
		// // TODO Auto-generated method stub
		// return "Browse";
		// }
		// });
		//
		// item.fill(menu, -1);
		// menu.setLayoutData(new GridData());
		//
		final ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL|SWT.BORDER);
		final Menu menu = new Menu(getShell(), SWT.POP_UP);
		// for (int i = 0; i < 8; i++) {
		final MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
		menuItem.setText("Open");
		final MenuItem menuItem2 = new MenuItem(menu, SWT.PUSH);
		menuItem2.setText("Search");
		
		final ToolItem item = new ToolItem(toolBar, SWT.DROP_DOWN);
		item.setText("Browse");
		// item.setImage(ImageResources.getImage(DateTimeControl.IMG_CALENDAR));
		// item.setEnabled(false);

		item.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {

				if (event.detail == SWT.ARROW) {
					Point point = new Point(event.x, event.y);
					point = getDisplay().map(toolBar, null, point);
					menu.setLocation(point);
					menu.setVisible(true);
				} else {
					menuItem.notifyListeners(SWT.Selection, event);
				}
			}

		});

		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// item.setEnabled(false);
				// item.setImage(ImageResources.getImage("icons/obj16/default_photo.jpg"));
				// try {
				adapter.forceOpenProposalPopup();
				// } finally {
				// item.setImage(ImageResources.getImage(DateTimeControl.IMG_CALENDAR));
				// item.setEnabled(true);
				// }
			}
		});
		
		menuItem2.addSelectionListener(new SelectionAdapter() {
		});
	}
	
	public void addSearcher(ISearcher2 searcher) {
		
	}

	private void createDecoration() {

		// Create the decoration for the text UI component
		deco = new ControlDecoration(searchText, SWT.BOTTOM | SWT.LEFT);
		// Re-use an existing image
		Image image = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION)
				.getImage();
		// Set description and image
		deco.setDescriptionText("Use Ctrl+Space to see possible values");
		deco.setImage(image);
		// Hide deco if not in focus
		deco.setShowOnlyOnFocus(false);

	}

	private void createContentAssist() {

		char[] autoActivationCharacters = new char[] {};
		KeyStroke keyStroke = null;
		try {
			keyStroke = KeyStroke.getInstance("Ctrl+Space");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		adapter = new SearchContentProposalAdapter(searchText, keyStroke,
				autoActivationCharacters);
		adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		adapter.setPropagateKeys(true);

		// adapter.setAutoActivationCharacters(null);
		// adapter.setAutoActivationDelay(1);
		adapter.addSearchListener(new ISearchListener() {

			public void modelChanged(Object oldModel, Object newModel) {
				if (newModel == null) {
					if (searchText.getText().length() > 0) {
						deco.setImage(FieldDecorationRegistry
								.getDefault()
								.getFieldDecoration(
										FieldDecorationRegistry.DEC_ERROR)
								.getImage());
						deco.setDescriptionText("Don't exist, use Ctrl+Space to see possible values");
						deco.show();
					} else {
						deco.setImage(FieldDecorationRegistry
								.getDefault()
								.getFieldDecoration(
										FieldDecorationRegistry.DEC_INFORMATION)
								.getImage());
						deco.setDescriptionText("Use Ctrl+Space to see possible values");
						deco.show();
					}
				} else {
					deco.hide();
				}

			}
		});
	}

	protected Text createText(Composite parent, int textStyle) {
		return new Text(parent, textStyle);
	}

	private int getTextStyle() {
		return textStyle;
	}

	public void setSearcher(ISearcher searcher) {
		adapter.setSearcher(searcher);
	}

	public void setCompletionLabelProvider(
			ICompletionLabelProvider completionLabelProvider) {
		adapter.setCompletionLabelProvider(completionLabelProvider);
	}

	public void addSearchListener(ISearchListener listener) {
		adapter.addSearchListener(listener);
	}

	public void removeSearchListener(ISearchListener listener) {
		adapter.removeSearchListener(listener);
	}

	public void setModel(Object model) {
		if (model != null) {
			searchText.setText(adapter.getCompletionLabelProvider().getContent(
					model));
		}
		adapter.setCurrentModel(model);
	}

	public Object getModel() {
		return adapter.getCurrentModel();
	}

	@Override
	public void addListener(int eventType, Listener listener) {
		if (eventType == 9999) {			
			adapter.addSearchListener(listener);
		} else {
			super.addListener(eventType, listener);
		}
	}

	@Override
	public void removeListener(int eventType, Listener listener) {
		if (eventType == 9999) {
			adapter.removeSearchListener(listener);
		} else {
			super.removeListener(eventType, listener);
		}
	}
}
