package org.dynaresume.eclipse.ui.wizards;

import org.dynaresume.eclipse.ui.internal.Messages;
import org.dynaresume.eclipse.ui.viewers.SkillResumeContentProvider;
import org.dynaresume.eclipse.ui.viewers.SkillResumeLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

public class QuickAddSillsPreviewWizardPage extends WizardPage {

	private static final String ID = "QuickAddSillsPreviewWizardPage";
	private Text skillText;
	private TableViewer existingSkillsViewer;
	private TableViewer skillsViewer;
	private TableViewer freeSkillsViewer;

	protected QuickAddSillsPreviewWizardPage() {
		super(ID, Messages.QuickAddSillsPreviewWizardPage_title, null);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		// 1) create Skills Table
		createSkillsTable(composite);
		// 2) create Free Skills Table
		createFreeSkillsTable(composite);
		// 3) create Existing Skills Table
		createExistingSkillsTable(composite);
		super.setControl(composite);
	}

	private void createSkillsTable(Composite parent) {
		Label label = new Label(parent, SWT.LEFT);
		label.setText(Messages.QuickAddSillsPreviewWizardPage_skillsTable_label);
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		Table skillsTable = new Table(parent, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		skillsTable.setLayoutData(gd);

		skillsViewer = new TableViewer(skillsTable);
		skillsViewer.setContentProvider(SkillResumeContentProvider.getInstance());
		skillsViewer.setLabelProvider(SkillResumeLabelProvider.getInstance());		
	}

	private void createFreeSkillsTable(Composite parent) {
		Label label = new Label(parent, SWT.LEFT);
		label.setText(Messages.QuickAddSillsPreviewWizardPage_freeSkillsTable_label);
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		Table skillsTable = new Table(parent, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		skillsTable.setLayoutData(gd);

		freeSkillsViewer = new TableViewer(skillsTable);
		freeSkillsViewer.setContentProvider(SkillResumeContentProvider.getInstance());
		freeSkillsViewer.setLabelProvider(SkillResumeLabelProvider.getInstance());
		freeSkillsViewer.setInput(getWizard().getFreeSkills());
	}

	private void createExistingSkillsTable(Composite parent) {
		Label label = new Label(parent, SWT.LEFT);
		label.setText(Messages.QuickAddSillsPreviewWizardPage_existingSkillsTable_label);
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		Table skillsTable = new Table(parent, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		skillsTable.setLayoutData(gd);

		existingSkillsViewer = new TableViewer(skillsTable);
		existingSkillsViewer.setContentProvider(SkillResumeContentProvider.getInstance());
		existingSkillsViewer.setLabelProvider(SkillResumeLabelProvider.getInstance());
	}

	@Override
	public QuickAddSkillsWizard getWizard() {
		return (QuickAddSkillsWizard) super.getWizard();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			skillsViewer.setInput(getWizard().getSkills());
			freeSkillsViewer.setInput(getWizard().getFreeSkills());
			existingSkillsViewer.setInput(getWizard().getExistingSkills());
		}
	}

}
