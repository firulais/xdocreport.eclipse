package fr.opensagres.eclipse.forms.widgets;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.eclipse.forms.internal.ImageResources;

public class DateTimeControl extends Composite {

	private final FormToolkit toolkit;
	private Text dateFieldText;
	private Button lookupButton;
	private Date date;
	private boolean allowPast;
	private boolean suppressModifyEvent;
	private String outputPattern = "dd//MM/yyyy";
	private Image calendarImage;

	public DateTimeControl(Composite parent, int style, FormToolkit toolkit) {
		this(parent, style, SWT.NONE, SWT.NONE, toolkit);
	}

	public DateTimeControl(Composite parent, int compositeStyle, int textStyle,
			int buttonStyle, FormToolkit toolkit) {
		super(parent, compositeStyle);
		if (toolkit != null) {
			toolkit.adapt(this);
		}
		this.toolkit = toolkit;
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		this.setLayout(layout);

		dateFieldText = createText(this, textStyle);
		dateFieldText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lookupButton = createButton(this, buttonStyle);
		lookupButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lookupButtonClicked();
			}
		});
		this.calendarImage = ImageResources
				.getImage(ImageResources.IMG_CALENDAR);
		if (calendarImage != null) {
			lookupButton.setImage(calendarImage);
		}
	}

	protected Text createText(Composite parent, int style) {
		FormToolkit toolkit = getToolkit();
		if (toolkit != null) {
			return toolkit.createText(parent, "", style);
		}
		return new Text(parent, style);
	}

	protected Button createButton(Composite parent, int style) {
		FormToolkit toolkit = getToolkit();
		if (toolkit != null) {
			return toolkit.createButton(parent, "", style);
		}
		return new Button(parent, style);
	}

	public FormToolkit getToolkit() {
		return toolkit;
	}

	public Text getDateFieldText() {
		return dateFieldText;
	}

	public Button getLookupButton() {
		return lookupButton;
	}

	public void setCalendarImage(Image calendarImage) {
		Assert.isTrue(calendarImage == null || !calendarImage.isDisposed());
		// Do not send changes if they are the same
		if (this.calendarImage == calendarImage) {
			return;
		}
		this.calendarImage = calendarImage;
		getLookupButton().setImage(calendarImage);
	}

	/**
	 * Called when the lookup button was clicked. Open a date time lookup dialog
	 * at the buttons position.
	 */
	private void lookupButtonClicked() {
		DateTimeDialog dialog = new DateTimeDialog(getShell(), getToolkit());
		Calendar cal = Calendar.getInstance();
		if (date == null)
			date = new Date();
		cal.setTime(date);

		dialog.setInitialDate(cal);
		if (dialog.open() == Window.OK) {
			setDate(dialog.getDate().getTime());
		}

		// Object[] listeners = selectionListeners.getListeners();
		// if (listeners.length < 1)
		// return;
		//
		// Event event = new Event();
		// event.widget = DateTimeControl.this ;
		// event.display = getDisplay();
		// // event.time = e.time;
		// // event.data = e.data;
		// SelectionEvent se = new SelectionEvent(event);
		// for (Object listener : listeners) {
		// SelectionListener l = (SelectionListener) listener;
		// l.widgetSelected(se);
		// }
	}

	/**
	 * @param date
	 *            The date to set. May be null to indicate that no date should
	 *            be set.
	 */
	public void setDate(Date date) {
		this.date = date;
		if (date != null) {
			suppressModifyEvent = true;
			try {
				dateFieldText.setText(format(date));
			} finally {
				suppressModifyEvent = false;
			}
		}
		// this .dateParseException = null;
	}

	protected String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(outputPattern);
		return sdf.format(date);
	}

	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}

}
