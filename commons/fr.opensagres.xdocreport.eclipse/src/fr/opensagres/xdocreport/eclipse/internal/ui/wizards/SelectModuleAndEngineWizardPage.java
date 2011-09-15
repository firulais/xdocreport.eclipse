package fr.opensagres.xdocreport.eclipse.internal.ui.wizards;

import java.util.Collection;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngineType;
import fr.opensagres.xdocreport.eclipse.internal.ui.viewers.ReportEngineTypeContentProvider;
import fr.opensagres.xdocreport.eclipse.internal.ui.viewers.ReportEngineTypeLabelProvider;
import fr.opensagres.xdocreport.eclipse.internal.ui.viewers.ReportModuleContentProvider;
import fr.opensagres.xdocreport.eclipse.internal.ui.viewers.ReprortModuleLabelProvider;

public class SelectModuleAndEngineWizardPage extends WizardPage {
	private Composite composite_1;

	protected SelectModuleAndEngineWizardPage() {
		super("pageName");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		composite_1 = new Composite(parent, SWT.NULL);
		GridLayout gl_composite_1 = new GridLayout();
		gl_composite_1.numColumns = 2;
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.HORIZONTAL_ALIGN_FILL));

		createReportAndEngineArea(composite_1);
		// setPreferenceTransfers();

		// restoreWidgetValues();
		// updateWidgetEnablements();

		// can not finish initially, but don't want to start with an error
		// message either
		// if (!(validDestination() && validateOptionsGroup() &&
		// validateSourceGroup())) {
		// setPageComplete(false);
		// }

		setControl(composite_1);

		// giveFocusToDestination();
		Dialog.applyDialogFont(composite_1);
	}

	private void createReportAndEngineArea(Composite composite) {
		createTransfersList(composite);

	}

	/**
	 * @param composite
	 */
	protected void createTransfersList(Composite composite) {

		// transferAllButton = new Button(composite, SWT.CHECK);
		// transferAllButton.setText(getAllButtonText());
		//
		// Group group = new Group(composite, SWT.NONE);
		// group.setText("Module");
		// GridData groupData = new GridData(GridData.FILL_BOTH);
		// groupData.horizontalSpan = 2;
		// groupData.horizontalIndent = IDialogConstants.INDENT;
		// Object compositeLayout = composite.getLayout();
		// if (compositeLayout instanceof GridLayout) {
		// groupData.horizontalIndent -= ((GridLayout)
		// compositeLayout).marginWidth;
		// groupData.horizontalIndent -= ((GridLayout)
		// compositeLayout).marginLeft;
		// }
		// group.setLayoutData(groupData);
		//
		// GridLayout layout = new GridLayout();
		// group.setLayout(layout);

		// Module
		Label moduleLabel = new Label(composite, SWT.NONE);
		moduleLabel.setText("Module:");
		moduleLabel.setLayoutData(new GridData(SWT.FILL));

		Combo moduleCombo = new Combo(composite, SWT.READ_ONLY);
		moduleCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ComboViewer moduleViewer = new ComboViewer(moduleCombo);
		moduleViewer.setContentProvider(ReportModuleContentProvider
				.getInstance());
		moduleViewer.setLabelProvider(ReprortModuleLabelProvider.getInstance());
		moduleViewer.setInput(PlatformXDocReport.getReportModuleRegistry()
				.getReportModules());
		Collection<IReportModule> modules = PlatformXDocReport
				.getReportModuleRegistry().getReportModules();
		moduleViewer.setInput(modules);
		if (modules.size() > 0) {
			moduleCombo.select(0);
		}
		// Engine
		Label engineLabel = new Label(composite, SWT.NONE);
		engineLabel.setText("Report engine:");
		engineLabel.setLayoutData(new GridData(SWT.FILL));

		Combo engineCombo = new Combo(composite, SWT.READ_ONLY);
		engineCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ComboViewer engineViewer = new ComboViewer(engineCombo);
		engineViewer.setContentProvider(ReportEngineTypeContentProvider
				.getInstance());
		engineViewer.setLabelProvider(ReportEngineTypeLabelProvider
				.getInstance());
		Collection<IReportEngineType> enginTypes = PlatformXDocReport
				.getReportEngineRegistry().getEngineTypes();
		engineViewer.setInput(enginTypes);
		if (enginTypes.size() > 0) {
			engineCombo.select(0);
		}
	}
}
