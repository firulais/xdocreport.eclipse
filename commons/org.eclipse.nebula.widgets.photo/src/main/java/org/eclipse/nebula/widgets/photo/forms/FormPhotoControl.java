package org.eclipse.nebula.widgets.photo.forms;

import org.eclipse.nebula.widgets.photo.AbstractPhotoControl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

public class FormPhotoControl extends AbstractPhotoControl<Hyperlink> {

	private final FormToolkit toolkit;

	public FormPhotoControl(Composite parent, FormToolkit toolkit) {
		this(parent, toolkit, SWT.NONE, SWT.BORDER);
	}

	public FormPhotoControl(Composite parent, FormToolkit toolkit,
			int compositeStyle, int labelStyle) {
		super(parent, compositeStyle, labelStyle, false);
		this.toolkit = toolkit;
		this.toolkit.adapt(this);
		super.createUI(labelStyle);
	}

	@Override
	protected Label createLabel(Composite parent, int style) {
		return toolkit.createLabel(parent, "", style);
	}

	@Override
	protected Hyperlink createLink(Composite parent) {
		return toolkit.createHyperlink(parent, "", SWT.NONE);
	}

	@Override
	protected void setModifyImageLinkText(Hyperlink modifyImageLink, String text) {
		modifyImageLink.setText(text);
	}

	@Override
	protected void addModifyImageHandler(Hyperlink modifyImageLink) {
		modifyImageLink.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				handleModifyImage();
			}
		});
	}

}
