package org.eclipse.nebula.widgets.photo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

public class PhotoControl extends AbstractPhotoControl<Link> {

	public PhotoControl(Composite parent, int compositeStyle, int labelStyle) {
		super(parent, compositeStyle, labelStyle);
	}

	public PhotoControl(Composite parent) {
		super(parent);
	}

	@Override
	protected Label createLabel(Composite parent, int style) {
		return new Label(parent, style);
	}
	
	@Override
	protected Link createLink(Composite parent) {
		return new Link(parent, SWT.NONE);
	}

	protected void setModifyImageLinkText(Link modifyImageLink, String text) {
		StringBuilder href = new StringBuilder();
		href.append("<a href=\"#\" >");
		href.append(text);
		href.append("</a>");
		modifyImageLink.setText(href.toString());
	}

	protected void addModifyImageHandler(Link modifyImageLink) {
		modifyImageLink.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				handleModifyImage();
			}
		});
	}


}
