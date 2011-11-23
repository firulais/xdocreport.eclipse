package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.hr.SkillResume;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import fr.opensagres.eclipse.forms.editor.ModelDetailsPage;

public class SkillDetailsPage extends ModelDetailsPage<SkillResume> {

	private final boolean freeSkill;

	public SkillDetailsPage(boolean freeSkill) {
		this.freeSkill = freeSkill;
	}

	private Text skillFreeLabelText;
	private Label skillLabel;

	@Override
	protected void onCreateUI(Composite parent) {

		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);

		FormToolkit toolkit = getManagedForm().getToolkit();

		Section skillDetailSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		skillDetailSection.marginWidth = 10;
		skillDetailSection
				.setText(Messages.ResumeFormEditor_SkillsPage_SkillDetailsPage_title); //$NON-NLS-1$
		skillDetailSection
				.setDescription(Messages.ResumeFormEditor_SkillsPage_SkillDetailsPage_desc); //$NON-NLS-1$

		TableWrapData td = new TableWrapData(TableWrapData.FILL,
				TableWrapData.TOP);
		td.grabHorizontal = true;
		skillDetailSection.setLayoutData(td);

		Composite client = toolkit.createComposite(skillDetailSection);
		skillDetailSection.setClient(client);

		// Create generic content
		createBodyContent(toolkit, client);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);
	}

	private void createBodyContent(FormToolkit toolkit, Composite parent) {
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 2;
		parent.setLayout(glayout);

		if (isSkillFree()) {
			// Skill Free label
			toolkit.createLabel(
					parent,
					Messages.ResumeFormEditor_SkillsPage_SkillDetailsPage_skillFreeLabel_label);
			skillFreeLabelText = toolkit.createText(parent, "", SWT.SINGLE);
			skillFreeLabelText.setLayoutData(new GridData(
					GridData.FILL_HORIZONTAL));
		} else {
			// Skill label
			toolkit.createLabel(
					parent,
					Messages.ResumeFormEditor_SkillsPage_SkillDetailsPage_skillLabel_label);
			skillLabel = toolkit.createLabel(parent, "", SWT.SINGLE);
			skillLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}
	}

	public void onBind(DataBindingContext bindingContext) {
		if (isSkillFree()) {
			// Free Label binding
			IObservableValue skillLabelTextObserveTextObserveWidget = SWTObservables
					.observeText(skillFreeLabelText, SWT.Modify);
			IObservableValue modelSkillLabelObserveValue = PojoObservables
					.observeValue(getModelObject(),
							SkillResume.FREE_SKILL_PROPERTY);
			bindingContext.bindValue(skillLabelTextObserveTextObserveWidget,
					modelSkillLabelObserveValue, null, null);
		} else {
			// Label binding
			IObservableValue skillLabelObserveTextObserveWidget = SWTObservables
					.observeText(skillLabel);
			IObservableValue modelSkillLabelObserveValue = PojoObservables
					.observeValue(getModelObject(), SkillResume.SKILL_PROPERTY
							+ "." + Skill.LABEL_PROPERTY);
			bindingContext.bindValue(skillLabelObserveTextObserveWidget,
					modelSkillLabelObserveValue, null, null);

		}

	}

	private boolean isSkillFree() {
		return freeSkill;
	}

}
