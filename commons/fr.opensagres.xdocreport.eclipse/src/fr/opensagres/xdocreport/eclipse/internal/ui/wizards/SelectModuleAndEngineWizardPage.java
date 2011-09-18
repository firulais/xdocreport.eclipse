package fr.opensagres.xdocreport.eclipse.internal.ui.wizards;

import java.util.Collection;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;
import fr.opensagres.xdocreport.eclipse.internal.ui.viewers.ReportModuleContentProvider;
import fr.opensagres.xdocreport.eclipse.internal.ui.viewers.ReportModuleLabelProvider;
import fr.opensagres.xdocreport.eclipse.internal.ui.viewers.ReportProcessorContentProvider;
import fr.opensagres.xdocreport.eclipse.internal.ui.viewers.ReportProcessorLabelProvider;

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

		// Module
		Label moduleLabel = new Label(composite, SWT.NONE);
		moduleLabel.setText("Module:");
		moduleLabel.setLayoutData(new GridData(SWT.FILL));

		Combo moduleCombo = new Combo(composite, SWT.READ_ONLY);
		moduleCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final ComboViewer moduleViewer = new ComboViewer(moduleCombo);
		moduleViewer.setContentProvider(ReportModuleContentProvider
				.getInstance());
		moduleViewer.setLabelProvider(ReportModuleLabelProvider.getInstance());
		moduleViewer.setInput(PlatformXDocReport.getReportModuleRegistry()
				.getReportModules());
		Collection<IReportModule> modules = PlatformXDocReport
				.getReportModuleRegistry().getReportModules();
		moduleViewer.setInput(modules);
		
		
		
		// Engine
		Label processorLabel = new Label(composite, SWT.NONE);
		processorLabel.setText("Report processor:");
		processorLabel.setLayoutData(new GridData(SWT.FILL));

		Combo processorCombo = new Combo(composite, SWT.READ_ONLY);
		processorCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final ComboViewer engineViewer = new ComboViewer(processorCombo);
		engineViewer.setContentProvider(ReportProcessorContentProvider
				.getInstance());
		engineViewer.setLabelProvider(ReportProcessorLabelProvider
				.getInstance());
		
		moduleCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {		
				
				refreshProcessors(moduleViewer, engineViewer);
				
				//engineViewer.setInput(input);
			}

			
		});
		
		if (modules.size() > 0) {
			moduleCombo.select(0);
			refreshProcessors(moduleViewer, engineViewer);
		}
		
		// Engine
//		Label engineLabel = new Label(composite, SWT.NONE);
//		engineLabel.setText("Report engine:");
//		engineLabel.setLayoutData(new GridData(SWT.FILL));
//
//		Combo engineCombo = new Combo(composite, SWT.READ_ONLY);
//		engineCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		final ComboViewer engineViewer = new ComboViewer(engineCombo);
//		engineViewer.setContentProvider(ReportEngineTypeContentProvider
//				.getInstance());
//		engineViewer.setLabelProvider(ReportEngineTypeLabelProvider
//				.getInstance());
//		Collection<IReportEngineType> enginTypes = PlatformXDocReport
//				.getReportEngineRegistry().getEngineTypes();
//		engineViewer.setInput(enginTypes);
//		if (enginTypes.size() > 0) {
//			engineCombo.select(0);
//		}
	}
	
	private void refreshProcessors(final ComboViewer moduleViewer,
			final ComboViewer engineViewer) {
		IStructuredSelection selection = (IStructuredSelection)moduleViewer.getSelection();
		if (!selection.isEmpty()) {
			IReportModule module = (IReportModule)selection.getFirstElement();
			engineViewer.setInput(module.getProcessors().getProcessors());
			engineViewer.refresh();
		}
	}
}
