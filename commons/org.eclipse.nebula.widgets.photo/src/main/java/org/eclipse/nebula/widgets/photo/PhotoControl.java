package org.eclipse.nebula.widgets.photo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

public class PhotoControl extends Composite implements PropertyChangeListener {

	/** Bundle name constant */
	public static final String BUNDLE_NAME = "org.eclipse.nebula.widgets.photo.resources"; //$NON-NLS-1$

	private Label photoLabel;
	private Control modifyImageLink;
	private byte[] imageByteArray;
	private Image resizedPhotoImage;

	/** Resources bundle */
	protected ResourceBundle resources;

	public static final String IMAGE_BYTEARRAY_PROPERTY = "imageByteArray";

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	private Locale locale;

	public PhotoControl(Composite parent, int compositeStyle, int labelStyle) {
		this(parent, compositeStyle, labelStyle, true);
	}

	public PhotoControl(Composite parent) {
		this(parent, SWT.NONE, SWT.BORDER, true);
	}

	protected PhotoControl(Composite parent, int compositeStyle,
			int labelStyle, boolean createUI) {
		super(parent, compositeStyle);
		setLocale(Locale.getDefault());
		if (createUI) {
			createUI(labelStyle);
		}
	}

	protected void createUI(int labelStyle) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginTop = 0;
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		this.setLayout(layout);

		Composite parent = new Composite(this, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		parent.setLayout(layout);
		GridData g = new GridData();
		g.verticalAlignment = SWT.TOP;
		parent.setLayoutData(g);
		this.photoLabel = createLabelImage(parent, labelStyle);
		this.modifyImageLink = createModifyLink(parent);
	}

	protected Label createLabelImage(Composite parent, int style) {
		Label label = createLabel(parent, style);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1,
				1));
		return label;
	}

	protected Control createModifyLinkControl(Composite parent) {
		return new Link(parent, SWT.NONE);
	}

	private Control createModifyLink(Composite parent) {
		Control modifyImageLink = createModifyLinkControl(parent);
		GridData gridData = new GridData(GridData.CENTER, GridData.CENTER,
				true, false);
		modifyImageLink.setLayoutData(gridData);
		setModifyImageLinkText(modifyImageLink, resources.getString("PhotoControl.modifyImage"));
		addModifyImageHandler(modifyImageLink);
		return modifyImageLink;
	}

	protected void setModifyImageLinkText(Control modifyImageLink, String text) {
		Link link = (Link) modifyImageLink;
		StringBuilder href = new StringBuilder();
		href.append("<a href=\"toto\" >");
		href.append(text);
		href.append("</a>");
		link.setText(href.toString());
	}

	protected void addModifyImageHandler(Control modifyImageLink) {
		Link link = (Link) modifyImageLink;
		link.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				handleModifyImage();
			}
		});
	}

	public void setModifyImageLinkText(String text) {
		setModifyImageLinkText((Link) getModifyImageLink(), text);
	}

	protected void handleModifyImage() {
		FileDialog fd = new FileDialog(this.getShell(), SWT.SHELL_TRIM
				| SWT.SINGLE | SWT.APPLICATION_MODAL);
		String selected = fd.open();
		if (selected != null && selected.length() > 0) {

			File f = new File(selected);
			try {
				FileInputStream in = new FileInputStream(f);
				setImageStream(in);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public Label getPhotoLabel() {
		return photoLabel;
	}

	public Control getModifyImageLink() {
		return modifyImageLink;
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void propertyChange(PropertyChangeEvent arg0) {

	}

	public void setImageStream(InputStream stream) throws IOException {
		// setImageByteArray(IOUtils.toByteArray(stream));
	}

	public InputStream getImageStream() {
		return new ByteArrayInputStream(getImageByteArray());
	}

	public void setImageByteArray(byte[] imageByteArray) {
		byte[] oldImageByteArray = this.imageByteArray;
		this.imageByteArray = imageByteArray;
		ImageData imageData = new ImageData(new ByteArrayInputStream(
				imageByteArray));
		if (this.resizedPhotoImage != null && !resizedPhotoImage.isDisposed()) {
			this.resizedPhotoImage.dispose();
		}
		int height = imageData.height;
		int width = imageData.width;

		float ratio = Math.max(height / 50, width / 50);

		final ImageData resizedImageData = ratio > 0 ? imageData.scaledTo(
				(int) (height / ratio), (int) (width / ratio)) : imageData;

		this.resizedPhotoImage = new Image(super.getDisplay(), resizedImageData);
		photoLabel.setImage(resizedPhotoImage);
		// Problem when image is uploaded and has not the same size, how to
		// recompute layout?
		// this.layout();

		propertyChangeSupport.firePropertyChange(IMAGE_BYTEARRAY_PROPERTY,
				oldImageByteArray, imageByteArray);
	}

	public byte[] getImageByteArray() {
		return imageByteArray;
	}

	protected Label createLabel(Composite parent, int style) {
		return new Label(parent, style);
	}

	protected Button createButton(Composite parent, int style) {
		return new Button(parent, style);
	}

	/**
	 * Sets a new locale to use for calendar. Locale will define the names of
	 * months and days, and the first day of week.
	 * 
	 * @param locale
	 *            new locale (must not be null)
	 */
	public void setLocale(Locale locale) {
		checkWidget();
		if (locale == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);

		this.locale = locale;

		// Loads the resources
		resources = ResourceBundle.getBundle(BUNDLE_NAME, locale);
	}

}
