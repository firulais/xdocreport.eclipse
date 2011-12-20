package org.dynaresume.project.eclipse.ui.editors.project;

import org.dynaresume.domain.project.ProjectDescription;
import org.dynaresume.project.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import fr.opensagres.eclipse.forms.editor.ModelDetailsPage;

public class DescriptionDetailsPage extends ModelDetailsPage<ProjectDescription> {

	private Text projectDescriptionLabelText;

	@Override
	protected void onCreateUI(Composite parent) {

		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);

		FormToolkit toolkit = getManagedForm().getToolkit();

		Section projectDescriptionDetailSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		projectDescriptionDetailSection.marginWidth = 10;
		projectDescriptionDetailSection
				.setText(Messages.ProjectFormEditor_DescriptionsPage_DescriptionDetailsPage_title); //$NON-NLS-1$
		projectDescriptionDetailSection
				.setDescription(Messages.ProjectFormEditor_DescriptionsPage_DescriptionDetailsPage_desc); //$NON-NLS-1$

		TableWrapData td = new TableWrapData(TableWrapData.FILL,
				TableWrapData.TOP);
		td.grabHorizontal = true;
		projectDescriptionDetailSection.setLayoutData(td);

		Composite client = toolkit.createComposite(projectDescriptionDetailSection);
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
				Messages.ProjectFormEditor_DescriptionsPage_DescriptionDetailsPage_description_label);
		projectDescriptionLabelText = toolkit.createText(parent, "", SWT.SINGLE);
		projectDescriptionLabelText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));


	}

	public void onBind(DataBindingContext bindingContext) {
		// Label binding
		IObservableValue projectDescriptionLabelTextObserveTextObserveWidget = SWTObservables
				.observeText(projectDescriptionLabelText, SWT.Modify);
		IObservableValue modelProjectDescriptionLabelObserveValue = PojoObservables
				.observeValue(getModelObject(), ProjectDescription.DESCRIPTION_PROPERTY);
		bindingContext.bindValue(projectDescriptionLabelTextObserveTextObserveWidget,
				modelProjectDescriptionLabelObserveValue, null, null);

	}

}
