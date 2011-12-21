package org.dynaresume.project.eclipse.ui.editors.project;

import org.dynaresume.domain.project.ProjectDescriptionType;
import org.dynaresume.project.eclipse.ui.internal.Messages;
import org.dynaresume.project.eclipse.ui.viewer.ProjectDescriptionTypeWrapper;
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
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import fr.opensagres.eclipse.forms.editor.ModelDetailsPage;

public class DescriptionTypeDetailsPage extends
		ModelDetailsPage<ProjectDescriptionTypeWrapper> {

	private Label projectDescriptionTypeLabel;

	@Override
	protected void onCreateUI(Composite parent) {

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		parent.setLayout(layout);

		FormToolkit toolkit = getManagedForm().getToolkit();

		Section projectDescriptionDetailSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		projectDescriptionDetailSection.marginWidth = 10;
		projectDescriptionDetailSection
				.setText(Messages.ProjectFormEditor_DescriptionsPage_DescriptionTypeDetailsPage_title); //$NON-NLS-1$
		projectDescriptionDetailSection
				.setDescription(Messages.ProjectFormEditor_DescriptionsPage_DescriptionTypeDetailsPage_desc); //$NON-NLS-1$

		GridData td = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		projectDescriptionDetailSection.setLayoutData(td);

		Composite client = toolkit
				.createComposite(projectDescriptionDetailSection);
		projectDescriptionDetailSection.setClient(client);

		// Create generic content
		createBodyContent(toolkit, client);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);
	}

	private void createBodyContent(FormToolkit toolkit, Composite parent) {
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 2;
		parent.setLayout(glayout);

		// ProjectDescription label
		toolkit.createLabel(
				parent,
				Messages.ProjectFormEditor_DescriptionsPage_DescriptionTypeDetailsPage_label_label);
		projectDescriptionTypeLabel = toolkit.createLabel(parent, "",
				SWT.SINGLE);
		projectDescriptionTypeLabel.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

	}

	public void onBind(DataBindingContext bindingContext) {
		// Label binding
		IObservableValue projectDescriptionLabelTextObserveTextObserveWidget = SWTObservables
				.observeText(projectDescriptionTypeLabel);
		IObservableValue modelProjectDescriptionLabelObserveValue = PojoObservables
				.observeValue(getModelObject().getType(),
						ProjectDescriptionType.LABEL_PROPERTY);
		bindingContext.bindValue(
				projectDescriptionLabelTextObserveTextObserveWidget,
				modelProjectDescriptionLabelObserveValue, null, null);

	}

}
