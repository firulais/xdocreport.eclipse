package org.dynaresume.project.eclipse.ui.wizards;

import org.dynaresume.domain.project.Project;
import org.dynaresume.project.eclipse.ui.internal.Messages;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import fr.opensagres.eclipse.forms.widgets.SimpleWikiText;

public class CreateProjectWizardPage extends WizardPage implements
		ModifyListener {

	private static final String ID = "CreateProjectWizardPage";
	private Text projectNameText;
	private SimpleWikiText projectNameDesc;

	public CreateProjectWizardPage() {
		super(ID, Messages.CreateProjectWizardPage_title, null);
		super.setDescription(Messages.CreateProjectWizardPage_desc);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		// Project name
		Label projectNameLabel = new Label(composite, SWT.LEFT);
		projectNameLabel
				.setText(Messages.CreateProjectWizardPage_projectName_label);
		projectNameText = new Text(composite, SWT.BORDER);
		projectNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		projectNameText.addModifyListener(this);

		// Project name
//		Label projectDescLabel = new Label(composite, SWT.LEFT);
//		projectDescLabel
//				.setText(Messages.CreateProjectWizardPage_projectDescription_label);
//		projectDescLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
//		projectNameDesc = new SimpleWikiText(composite, SWT.NONE, SWT.NONE);
//		projectNameDesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		projectNameDesc.getTextarea().addModifyListener(this);

		super.setControl(composite);

	}

	public void modifyText(ModifyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void updateData(Project project) {
		project.setName(projectNameText.getText());
	}
}
