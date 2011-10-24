package fr.opensagres.eclipse.forms.widgets;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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

import fr.opensagres.eclipse.forms.internal.ImageResources;

public class PhotoControl extends BaseComposite {

	private final Label photoLabel;
	private final Button uploadButton;
	private Image photo;
	private Image defaultImage;

	public PhotoControl(Composite parent, int compositeStyle, int labelStyle,
			FormToolkit toolkit) {
		this(parent, compositeStyle, labelStyle, ImageResources
				.getImage(ImageResources.IMG_DEFAULT_PHOTO), toolkit);
	}

	public PhotoControl(Composite parent, int compositeStyle, int labelStyle,
			Image defaultImage, FormToolkit toolkit) {
		super(parent, compositeStyle, toolkit);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		this.setLayout(layout);
		this.defaultImage = defaultImage;
		this.photoLabel = createLabelImage(this, labelStyle);
		this.uploadButton = createUploadButton();
	}

	private Label createLabelImage(Composite parent, int style) {
		Label label = super.createLabel(parent, style);
		label.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		return label;
	}

	private Button createUploadButton() {
		Button button = super.createButton(this, SWT.NONE);
		button.setText("Modify");
		button.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleUploadImage();
			}
		});
		return button;
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
			}
		}
	}

	public void setImageStream(InputStream stream) {
		ImageData imageData = new ImageData(stream);
		if (photo != null && photo != defaultImage) {
			photo.dispose();
		}

		int height = imageData.height;
		int width = imageData.width;

		float ratio = Math.max(height / 50, width / 50);

		final ImageData resized = imageData.scaledTo((int) (height / ratio),
				(int) (width / ratio));

		this.photo = new Image(super.getDisplay(), resized);
		photoLabel.setImage(photo);
		// Problem when image is uploaded and has not the same size, how to
		// recompute layout?
		// this.layout();
	}

	public Label getPhotoLabel() {
		return photoLabel;
	}
}
