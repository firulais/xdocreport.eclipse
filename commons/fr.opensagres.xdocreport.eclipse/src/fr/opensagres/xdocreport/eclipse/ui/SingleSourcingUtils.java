package fr.opensagres.xdocreport.eclipse.ui;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class SingleSourcingUtils {

	private static final String FormToolkit_paintBordersFor = "paintBordersFor";
	private static Map<String, Boolean> methodsExists = new HashMap<String, Boolean>();

/**
	 * Invoke {@link FormToolkit#paintBordersFor(Composite) using reflection because in RAP context, this method doesn't exist. 
	 * 
	 * @param toolkit
	 * @param parent
	 *            the parent that owns the children for which the border needs
	 *            to be painted.
	 */
	public static void FormToolkit_paintBordersFor(FormToolkit toolkit,
			Composite parent) {
		// the method paintBordersFor does not exist in the RAP implementation.
		// invoke it using reflection.
		try {
			if (!isMethodExists(FormToolkit_paintBordersFor)) {
				// RAP context, the FormToolkit#paintBordersFor doesn't exist.
				return;
			}
			Method method = toolkit.getClass().getMethod(
					FormToolkit_paintBordersFor, Composite.class);
			setMethodExists(FormToolkit_paintBordersFor, true);
			// RCP context, the FormToolkit#paintBordersFor exists.
			method.invoke(toolkit, parent);
		} catch (Exception e) {
			setMethodExists(FormToolkit_paintBordersFor, false);
		}
	}

	private static void setMethodExists(String methodName, boolean exists) {
		methodsExists.put(methodName, exists);
	}

	private static boolean isMethodExists(String methodName) {
		Boolean exists = methodsExists.get(methodName);
		if (exists != null && exists == false) {
			return false;
		}
		return true;
	}

}
