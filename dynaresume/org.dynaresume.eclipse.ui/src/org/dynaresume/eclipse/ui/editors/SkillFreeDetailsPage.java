package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.SkillResume;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class SkillFreeDetailsPage extends AbstractSkillDetailsPage {

	private Text skillFreeLabelText;

	@Override
	protected void createBodyContent(FormToolkit toolkit, Composite parent) {
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 2;
		parent.setLayout(glayout);

		// Skill Free label
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_SkillsPage_SkillDetailsPage_skillFreeLabel_label);
		skillFreeLabelText = toolkit.createText(parent, "", SWT.SINGLE);
		skillFreeLabelText
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	public void onBind(DataBindingContext bindingContext) {
		// Free Label binding
		IObservableValue skillLabelTextObserveTextObserveWidget = SWTObservables
				.observeText(skillFreeLabelText, SWT.Modify);
		IObservableValue modelSkillLabelObserveValue = PojoObservables
				.observeValue(getModelObject(), SkillResume.FREE_SKILL_PROPERTY);
		bindingContext.bindValue(skillLabelTextObserveTextObserveWidget,
				modelSkillLabelObserveValue, null, null);

	}

}
