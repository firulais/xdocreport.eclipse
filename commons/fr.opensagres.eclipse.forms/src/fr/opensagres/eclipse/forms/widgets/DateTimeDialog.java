package fr.opensagres.eclipse.forms.widgets;

import java.util.Calendar;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class DateTimeDialog extends Dialog {

	private final FormToolkit toolkit;
	// private final long flags;
	private DateTime calendarDateTime;
	private Calendar date;

	protected DateTimeDialog(IShellProvider parentShell, FormToolkit toolkit) {
		super(parentShell);
		// this.flags = flags;
		this.toolkit = toolkit;
	}

	protected DateTimeDialog(Shell parentShell, FormToolkit toolkit) {
		super(parentShell);
		this.toolkit = toolkit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite page = (Composite) super.createDialogArea(parent);
		int numColumns = 1;
		this.date = Calendar.getInstance();
		this.calendarDateTime = createDateTimeCalendar(page);
		page.setLayout(new GridLayout(numColumns, false));
		return page;
	}

	protected DateTime createDateTimeCalendar(Composite parent) {
		Composite dateComp = new Composite(parent, SWT.BORDER);
		dateComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		dateComp.setLayout(new GridLayout());
		DateTime dateTime = new DateTime(dateComp, SWT.CALENDAR | SWT.BORDER);
		dateTime.setLayoutData(new GridData(GridData.FILL_BOTH));
		return dateTime;
	}
	
	/**
     * Set the initial date to show in this dialog. This must be called
     * before {@link #open()} to have any effect. Internally, a copy of
     * the given {@link Calendar} instance is used. The given instance
     * is never changed. Call {@link #getDate()} to get the date selected
     * by the user after {@link #open()} returns.
     * @param initialDate The initial date to set
     */
    public void setInitialDate(Calendar initialDate) {
        date = (Calendar) initialDate.clone();
    }

    public Calendar getDate() {
        return date;
    }

    @Override
    protected void okPressed() {
        getButton(OK).setFocus();

        if (calendarDateTime != null) {
            date.set(Calendar.YEAR, calendarDateTime.getYear());
            date.set(Calendar.MONTH, calendarDateTime.getMonth());
            date.set(Calendar.DAY_OF_MONTH, calendarDateTime.getDay());
        }

		// if (timeDateTime != null) {
		// date.set(Calendar.HOUR_OF_DAY, timeDateTime.getHours());
		// date.set(Calendar.MINUTE, timeDateTime.getMinutes());
		// date.set(Calendar.SECOND, timeDateTime.getSeconds());
		// }
        super .okPressed();
    }

}
