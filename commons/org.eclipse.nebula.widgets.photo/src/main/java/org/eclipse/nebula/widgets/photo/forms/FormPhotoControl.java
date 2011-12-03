package org.eclipse.nebula.widgets.photo.forms;

import org.eclipse.nebula.widgets.photo.PhotoControl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

public class FormPhotoControl extends PhotoControl {

	private final FormToolkit toolkit;

	public FormPhotoControl(Composite parent, FormToolkit toolkit) {
		this(parent, SWT.NONE, SWT.BORDER, toolkit);
	}

	public FormPhotoControl(Composite parent, int compositeStyle,
			int labelStyle, FormToolkit toolkit) {
		super(parent, compositeStyle, labelStyle, false);
		this.toolkit = toolkit;
		this.toolkit.adapt(this);
		super.createUI(labelStyle);
	}

	protected Label createLabel(Composite parent, int style) {
		return toolkit.createLabel(parent, "", style);
	}

	protected Button createButton(Composite parent, int style) {
		return toolkit.createButton(parent, "", style);
	}

	protected Control createModifyLinkControl(Composite parent) {
		return toolkit.createHyperlink(parent, "", SWT.NONE);
	}

	protected void setModifyImageLinkText(Control modifyImageLink, String text) {
		Hyperlink hyperlink = (Hyperlink) modifyImageLink;
		hyperlink.setText(text);
	}

	protected void addModifyImageHandler(Control modifyImageLink) {
		Hyperlink hyperlink = (Hyperlink) modifyImageLink;
		hyperlink.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				handleModifyImage();
			}
		});
	}

}
