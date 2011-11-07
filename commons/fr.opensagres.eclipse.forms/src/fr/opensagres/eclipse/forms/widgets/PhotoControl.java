package fr.opensagres.eclipse.forms.widgets;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.internal.dialogs.DialogUtil;

import fr.opensagres.eclipse.forms.internal.IOUtils;

public class PhotoControl extends BaseComposite implements
		PropertyChangeListener {

	private final Label photoLabel;
	private final Button uploadButton;
	private byte[] imageByteArray;
	private Image resizedPhotoImage;

	public static final String IMAGE_BYTEARRAY_PROPERTY = "imageByteArray";

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public PhotoControl(Composite parent, int compositeStyle, int labelStyle,
			FormToolkit toolkit) {
		super(parent, compositeStyle, toolkit);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		this.setLayout(layout);
		this.photoLabel = createLabelImage(this, labelStyle);
		this.uploadButton = createUploadButton();
	}

	private Label createLabelImage(Composite parent, int style) {
		Label label = super.createLabel(parent, style);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1,
				1));
		return label;
	}

	private Button createUploadButton() {
		Button btnChange = super.createButton(this, SWT.NONE);
		btnChange.setText("Change");
		GridData gd_btnChange = new GridData(GridData.BEGINNING);
		gd_btnChange.horizontalAlignment = SWT.FILL;
		gd_btnChange.verticalAlignment = SWT.TOP;
		btnChange.setLayoutData(gd_btnChange);
		btnChange.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleUploadImage();
			}
		});
		return btnChange;
	}

	private void handleUploadImage() {
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

	public Button getUploadButton() {
		return uploadButton;
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
		setImageByteArray(IOUtils.toByteArray(stream));
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

}
