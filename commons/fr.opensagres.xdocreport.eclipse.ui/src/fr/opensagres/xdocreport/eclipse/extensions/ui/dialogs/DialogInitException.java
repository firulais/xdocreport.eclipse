package fr.opensagres.xdocreport.eclipse.extensions.ui.dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.WorkbenchException;

public class DialogInitException extends WorkbenchException {

	private static final long serialVersionUID = -7781798070868433645L;

	/**
	 * Creates a new exception with the given message.
	 * 
	 * @param message
	 *            the message
	 */
	public DialogInitException(String message) {
		super(message);
	}

	/**
	 * Creates a new exception with the given message.
	 * 
	 * @param message
	 *            the message
	 * @param nestedException
	 *            a exception to be wrapped by this PartInitException
	 */
	public DialogInitException(String message, Throwable nestedException) {
		super(message, nestedException);
	}

	/**
	 * Creates a new exception with the given status object. The message of the
	 * given status is used as the exception message.
	 * 
	 * @param status
	 *            the status object to be associated with this exception
	 */
	public DialogInitException(IStatus status) {
		super(status);
	}

}
