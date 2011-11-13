package fr.opensagres.xdocreport.eclipse;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.ui.dialogs.IDialogFactory;
import fr.opensagres.xdocreport.eclipse.extensions.ui.wizards.IWizardFactory;
import fr.opensagres.xdocreport.eclipse.internal.extensions.modules.ReportModuleRegistry;
import fr.opensagres.xdocreport.eclipse.internal.extensions.ui.dialogs.DialogFactory;
import fr.opensagres.xdocreport.eclipse.internal.extensions.ui.wizards.WizardFactory;

public final class PlatformXDocReport {

	private static final String FRENCH_DATE_PATTERN = "dd/MM/yyyy";

	public static IReportModuleRegistry getReportModuleRegistry() {
		return ReportModuleRegistry.getRegistry();
	}
	
	public static IDialogFactory getDialogFactory() {
		return DialogFactory.getRegistry();
	}
	
	public static IWizardFactory getWizardFactory() {
		return WizardFactory.getRegistry();
	}
	
	public static String getDatePattern() {
		return FRENCH_DATE_PATTERN;
	}
}
