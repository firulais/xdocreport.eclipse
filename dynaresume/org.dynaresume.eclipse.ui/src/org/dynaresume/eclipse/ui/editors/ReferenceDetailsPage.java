package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.Reference;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import fr.opensagres.eclipse.forms.editor.ModelDetailsPage;

public class ReferenceDetailsPage extends ModelDetailsPage<Reference> {

	private Text referenceLabelText;

	@Override
	protected void onCreateUI(Composite parent) {

		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);

		FormToolkit toolkit = getManagedForm().getToolkit();

		Section referenceDetailSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		referenceDetailSection.marginWidth = 10;
		referenceDetailSection
				.setText(Messages.ResumeFormEditor_ReferencesPage_ReferenceDetailsPage_title); //$NON-NLS-1$
		referenceDetailSection
				.setDescription(Messages.ResumeFormEditor_ReferencesPage_ReferenceDetailsPage_desc); //$NON-NLS-1$

		TableWrapData td = new TableWrapData(TableWrapData.FILL,
				TableWrapData.TOP);
		td.grabHorizontal = true;
		referenceDetailSection.setLayoutData(td);

		Composite client = toolkit.createComposite(referenceDetailSection);
		referenceDetailSection.setClient(client);

		// Create generic content
		createBodyContent(toolkit, client);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);
	}

	private void createBodyContent(FormToolkit toolkit, Composite parent) {
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 2;
		parent.setLayout(glayout);

		// Reference label
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_ReferencesPage_ReferenceDetailsPage_referenceLabel_label);
		referenceLabelText = toolkit.createText(parent, "", SWT.SINGLE);
		referenceLabelText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));


	}

	public void onBind(DataBindingContext bindingContext) {
		// Label binding
		IObservableValue referenceLabelTextObserveTextObserveWidget = SWTObservables
				.observeText(referenceLabelText, SWT.Modify);
		IObservableValue modelReferenceLabelObserveValue = PojoObservables
				.observeValue(getModelObject(), Reference.LABEL_PROPERTY);
		bindingContext.bindValue(referenceLabelTextObserveTextObserveWidget,
				modelReferenceLabelObserveValue, null, null);

	}

}
