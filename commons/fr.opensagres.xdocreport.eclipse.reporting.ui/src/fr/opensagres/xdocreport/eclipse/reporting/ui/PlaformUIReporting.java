package fr.opensagres.xdocreport.eclipse.reporting.ui;

import fr.opensagres.xdocreport.eclipse.reporting.ui.fragment.IUIFragmentRegistry;
import fr.opensagres.xdocreport.eclipse.reporting.ui.internal.fragment.UIFragmentRegistry;

public final class PlaformUIReporting {

	public static IUIFragmentRegistry getUIFragmentRegistry() {
		return UIFragmentRegistry.getRegistry();
	}

}
