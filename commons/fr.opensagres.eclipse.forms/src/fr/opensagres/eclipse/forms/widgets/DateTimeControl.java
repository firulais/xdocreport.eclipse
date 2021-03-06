package fr.opensagres.eclipse.forms.widgets;

import java.text.DateFormat;
import java.text.ParseException;
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
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;

import fr.opensagres.eclipse.forms.internal.ImageResources;

public class DateTimeControl extends BaseComposite {

	private static final String DEFAULT_PATTERN = "dd/MM/yyyy";

	private Text dateFieldText;
	private Button lookupButton;
	private Date date;
	private boolean allowPast;
	private boolean suppressModifyEvent;
	private String outputPattern = DEFAULT_PATTERN;
	private Image calendarImage;

	private ImageHyperlink lookupHyperlink;

	private DateFormat formatter;

	public static final String IMG_CALENDAR = "icons/obj16/calendar.png";

	public static final String OWNER_DATETIME_CONTROL = "___DateTimeControl";

	public DateTimeControl(Composite parent, int style, FormToolkit toolkit) {
		this(parent, style, SWT.NONE, SWT.NONE, toolkit);
	}

	public DateTimeControl(Composite parent, int compositeStyle, int textStyle,
			int buttonStyle, FormToolkit toolkit) {
		super(parent, compositeStyle, toolkit);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = false;
		layout.marginWidth = 1;
		layout.marginHeight = 1;
		this.setLayout(layout);

		dateFieldText = createText(this, textStyle);
		dateFieldText.setData(OWNER_DATETIME_CONTROL, this);
		GridData d = new GridData(GridData.FILL_HORIZONTAL);
		//d.verticalAlignment = SWT.TOP;
		dateFieldText.setLayoutData(d);

		this.calendarImage = ImageResources
				.getImage(DateTimeControl.IMG_CALENDAR);
		if (toolkit != null) {
			lookupHyperlink = toolkit.createImageHyperlink(this, buttonStyle);

			lookupHyperlink.setImage(calendarImage);
			lookupHyperlink.addHyperlinkListener(new HyperlinkAdapter() {
				@Override
				public void linkActivated(HyperlinkEvent e) {
					lookupButtonClicked();
				}
			});

		} else {
			lookupButton = createButton(this, buttonStyle);
			lookupButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					lookupButtonClicked();
				}
			});
			lookupButton.setImage(calendarImage);
		}
	}

	public Text getDateFieldText() {
		return dateFieldText;
	}

	public Button getLookupButton() {
		return lookupButton;
	}

	public ImageHyperlink getLookupHyperlink() {
		return lookupHyperlink;
	}

	public void setCalendarImage(Image calendarImage) {
		Assert.isTrue(calendarImage == null || !calendarImage.isDisposed());
		// Do not send changes if they are the same
		if (this.calendarImage == calendarImage) {
			return;
		}
		this.calendarImage = calendarImage;
		if (getLookupHyperlink() != null) {
			getLookupHyperlink().setImage(calendarImage);
		} else {
			getLookupButton().setImage(calendarImage);
		}
	}

	/**
	 * Called when the lookup button was clicked. Open a date time lookup dialog
	 * at the buttons position.
	 */
	private void lookupButtonClicked() {
		DateTimeDialog dialog = new DateTimeDialog(getShell(), getToolkit());
		//
		Date d = getDate(dateFieldText.getText());
		if (d != null) {
			date = d;
		} else {
			if (date == null)
				date = new Date();
		}

		Calendar cal = Calendar.getInstance();
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

	private Date getDate(String text) {
		try {
			return getFormatter().parse(text);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @param date
	 *            The date to set. May be null to indicate that no date should
	 *            be set.
	 */
	public void setDate(Date date) {
		this.date = date;
		if (date != null) {
			// suppressModifyEvent = true;
			try {
				dateFieldText.setText(format(date));
			} finally {
				// suppressModifyEvent = false;
			}
		} else {
			dateFieldText.setText("");
		}
		// this .dateParseException = null;
	}

	protected String format(Date date) {
		return getFormatter().format(date);
	}

	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}

	public void setOutputPattern(String outputPattern) {
		this.outputPattern = outputPattern;
		this.formatter = null;
	}

	public String getOutputPattern() {
		return outputPattern;
	}

	public DateFormat getFormatter() {
		if (formatter == null) {
			formatter = new SimpleDateFormat(getOutputPattern());
		}
		return formatter;
	}
}
