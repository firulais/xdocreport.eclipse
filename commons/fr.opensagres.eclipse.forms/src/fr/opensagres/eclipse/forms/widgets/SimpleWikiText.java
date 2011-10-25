package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class SimpleWikiText extends BaseComposite {

	private Text text;
	private ToolBar toolBar;
	private ToolItem boldButton;
	private ToolItem italicButton;
	
	public SimpleWikiText(Composite parent, int compositeStyle, int textStyle,
			FormToolkit toolkit) {
		super(parent, compositeStyle, toolkit);
		this.setLayout(new GridLayout());
		this.toolBar = createToolBar(this);
		text = createTextarea(this, textStyle);
	}

	private ToolBar createToolBar(Composite parent) {
	    toolBar = new ToolBar(parent, SWT.NULL);
	    toolBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	        setStyle(event.widget);
	      }
	    };
	    boldButton = new ToolItem(toolBar, SWT.PUSH);
	    boldButton.setText("B");
	    boldButton.setToolTipText("Bold");
	    boldButton.addSelectionListener(listener);
	    italicButton = new ToolItem(toolBar, SWT.PUSH);
	    italicButton.setText("I");
	    italicButton.setToolTipText("Italic");
	    italicButton.addSelectionListener(listener);
	    return toolBar;
	}

	private void setStyle(Widget widget) {
		
		
	}
	
	private Text createTextarea(Composite parent, int style) {
		Text text =  super.createText(parent, style | SWT.MULTI | SWT.WRAP);
		GridData spec = new GridData();
	    spec.horizontalAlignment = GridData.FILL;
	    spec.grabExcessHorizontalSpace = true;
	    spec.verticalAlignment = GridData.FILL;
	    spec.grabExcessVerticalSpace = true;
	    text.setLayoutData(spec);
		return text;
	}
	
	public Text getTextarea() {
		return text;
	}
}
