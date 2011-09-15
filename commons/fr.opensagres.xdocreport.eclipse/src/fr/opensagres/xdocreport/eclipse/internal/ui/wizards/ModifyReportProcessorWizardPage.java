package fr.opensagres.xdocreport.eclipse.internal.ui.wizards;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

public class ModifyReportProcessorWizardPage extends WizardPage {

	protected ModifyReportProcessorWizardPage() {
		super("x");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(2, false));

		Button addMultiBtn = new Button(container, SWT.PUSH);
		addMultiBtn.setText("Add Multiple Files");
		addMultiBtn
				.setToolTipText("Launches file dialog for multiple file selection.");
		// addMultiBtn.setLayoutData(factory.create());
		addMultiBtn.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault()
						.getActiveShell(), SWT.SHELL_TRIM | SWT.SINGLE
						| SWT.APPLICATION_MODAL);
				fd.setText("Upload Multiple Files");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.txt", "*.doc", "*.rtf", "*.*" };
				fd.setFilterExtensions(filterExt);
				String[] filterNames = { "Text Files", "Word Document",
						"Rich Text Format", "All Files" };
				fd.setFilterNames(filterNames);
				String selected = fd.open();
				if (selected != null && selected.length() > 0) {

					File f = new File(selected);
					try {
						FileInputStream in = new FileInputStream(f);

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		setControl(container);
	}

}
