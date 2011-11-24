package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.SkillResume;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import fr.opensagres.eclipse.forms.editor.ModelDetailsPage;

public abstract class AbstractSkillDetailsPage extends
		ModelDetailsPage<SkillResume> {

	@Override
	protected void onCreateUI(Composite parent) {

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		parent.setLayout(layout);

		FormToolkit toolkit = getManagedForm().getToolkit();

		Section skillDetailSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		skillDetailSection.marginWidth = 10;
		skillDetailSection
				.setText(Messages.ResumeFormEditor_SkillsPage_SkillDetailsPage_title); //$NON-NLS-1$
		skillDetailSection
				.setDescription(Messages.ResumeFormEditor_SkillsPage_SkillDetailsPage_desc); //$NON-NLS-1$

		GridData td = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		skillDetailSection.setLayoutData(td);

		Composite client = toolkit.createComposite(skillDetailSection,
				SWT.NONE);
		skillDetailSection.setClient(client);

		// Create generic content
		createBodyContent(toolkit, client);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);
	}

	protected abstract void createBodyContent(FormToolkit toolkit,
			Composite parent);
}
