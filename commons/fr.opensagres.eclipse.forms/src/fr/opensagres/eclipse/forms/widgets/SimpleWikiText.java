package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.eclipse.forms.internal.ImageResources;

public class SimpleWikiText extends BaseComposite {

	private Text text;
	private ToolBar toolBar;
	private ToolItem boldButton;
	private ToolItem italicButton;
	private ToolItem underlineButton;
//	private ToolItem alignLeftButton;
//	private ToolItem alignCenterButton;
//	private ToolItem alignRightButton;
	private ToolItem insertLinkButton;
	//TODO private ToolItem insertImageButton;
	private ToolItem numberedListButton;
	private ToolItem bulletedListButton;

	public SimpleWikiText(Composite parent, int compositeStyle, int textStyle,
			FormToolkit toolkit) {
		super(parent, compositeStyle, toolkit);
		this.setLayout(new GridLayout());
		this.toolBar = createToolBar(this);
		final Composite comp = new Composite(this, SWT.NONE);
		comp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_comp = new GridLayout();
		gl_comp.marginWidth = 0;
		gl_comp.horizontalSpacing = 0;
		comp.setLayout(gl_comp);
	
		text = createTextarea(comp, textStyle);
	}

	private ToolBar createToolBar(Composite parent) {
		toolBar = new ToolBar(parent, SWT.BORDER);
		GridData gd_toolBar = new GridData(GridData.FILL_HORIZONTAL);
		gd_toolBar.horizontalAlignment = SWT.FILL;
		gd_toolBar.grabExcessVerticalSpace = false;
		toolBar.setLayoutData(gd_toolBar);
		SelectionAdapter listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setStyle(event.widget);
			}
		};
		
		boldButton=createToolItem(listener,"icons/obj16/b_bold.gif","Bold");

		italicButton=createToolItem(listener,"icons/obj16/b_italic.gif","Italic");
		
		underlineButton=createToolItem(listener,"icons/obj16/b_underline.gif","Underline");

//		alignLeftButton=createToolItem(listener,"icons/obj16/b_aleft.gif","Align Left");
//		alignCenterButton=createToolItem(listener,"icons/obj16/b_acenter.gif","Align Center");
//		alignRightButton=createToolItem(listener,"icons/obj16/b_aright.gif","Align Right");
		insertLinkButton=createToolItem(listener,"icons/obj16/b_url.gif","Insert Link");
		numberedListButton=createToolItem(listener,"icons/obj16/b_numlist.gif","Numbered List");
		bulletedListButton=createToolItem(listener,"icons/obj16/b_bulletlist.gif","Bullet List");
		return toolBar;
	}

	private ToolItem createToolItem(SelectionAdapter listener, String icon, String toolTipText) {
		ToolItem	newItem = new ToolItem(toolBar, SWT.PUSH);
		newItem.setImage(ImageResources.getImage(icon));
		newItem.setToolTipText(toolTipText);
		newItem.addSelectionListener(listener);
		return newItem;
	}

	private void setStyle(Widget widget) {
		String startTag="";
		String endTag="";
		// if not empty
		Point selection = text.getSelection();
		if (selection.x != selection.y) {
			if (boldButton.equals(widget)) {
				startTag="<b>";
				endTag="</b>";
			}
			else if (italicButton.equals(widget)) {
				startTag="<i>";
				endTag="</i>";

			}
			else if(underlineButton.equals(widget)){
				startTag="<u>";
				endTag="</u>";
//			}else if(alignLeftButton.equals(widget)){
//				startTag="<P align=\"left\">";
//				endTag="</p>";
//			}else if(alignCenterButton.equals(widget)){
//				startTag="<P align=\"center\">";
//				endTag="</p>";
//			}else if(alignRightButton.equals(widget)){
//				startTag="<P align=\"right\">";
//				endTag="</p>";
			} else if (insertLinkButton.equals(widget)){
				//TODO : dialog box to prompt for a URL..
				startTag="<a href=\"TODO\">";
				endTag="</a>";
				
			} else if (numberedListButton.equals(widget)){
				//TODO : split into different <li> ?
				startTag="<ol><li>";
				endTag="</li></ol>";
				
			} else if(bulletedListButton.equals(widget)){
				//TODO : split into different <li> ?
				startTag="<ul><li>";
				endTag="</li></ul>";
			}	
			
			
			applyTags(startTag,endTag);
		
		}
	}
	private void applyTags(String startTag, String endTag) {
		String fullText = text.getText();
		StringBuffer buffer = new StringBuffer(fullText);
		Point selection = text.getSelection();
		// first insert the ending tag ex: "</b>"
		buffer.insert(selection.y, endTag);
		// then insert the beginning tag ex: "<b>" 
		buffer.insert(selection.x, startTag);
		text.setText(buffer.toString());
		text.setFocus();
		text.setSelection(selection.x);
		//might have full selection instead:
		//text.setSelection(selection.x,selection.y+startTag.length()+endTag.length());
		
	}
	Browser preview;
	private Text createTextarea(Composite parent, int style) {
		final Text text = super.createText(parent, style | SWT.MULTI | SWT.WRAP);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Group grpPreview = new Group(parent,SWT.NONE);
		
		grpPreview.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpPreview.setText("Preview...");
		grpPreview.setLayout(new FillLayout());
		preview = new Browser(grpPreview,SWT.NONE);
		text.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent event) {
				System.out.println(preview.setText(text.getText()));
				
			}
		});
		
		return text;
	}

	public Text getTextarea() {
		return text;
	}
}
