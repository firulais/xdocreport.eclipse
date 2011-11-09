package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

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
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Diploma;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages;

public class DiplomaDetailsPage extends ModelDetailsPage<Diploma> {

	private Text diplomaLabelText;
	private Text diplomaInstituteText;

	@Override
	protected void onCreateUI(Composite parent) {

		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);

		FormToolkit toolkit = getManagedForm().getToolkit();

		Section diplomaDetailSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		diplomaDetailSection.marginWidth = 10;
		diplomaDetailSection
				.setText(Messages.ResumeFormEditor_DiplomasPage_DiplomaDetailsPage_title); //$NON-NLS-1$
		diplomaDetailSection
				.setDescription(Messages.ResumeFormEditor_DiplomasPage_DiplomaDetailsPage_desc); //$NON-NLS-1$

		TableWrapData td = new TableWrapData(TableWrapData.FILL,
				TableWrapData.TOP);
		td.grabHorizontal = true;
		diplomaDetailSection.setLayoutData(td);

		Composite client = toolkit.createComposite(diplomaDetailSection);
		diplomaDetailSection.setClient(client);

		// Create generic content
		createBodyContent(toolkit, client);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);
	}

	private void createBodyContent(FormToolkit toolkit, Composite parent) {
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 2;
		parent.setLayout(glayout);

		// Diploma label
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_DiplomasPage_DiplomaDetailsPage_diplomaLabel_label);
		diplomaLabelText = toolkit.createText(parent, "", SWT.SINGLE);
		diplomaLabelText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Diploma institute
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_DiplomasPage_DiplomaDetailsPage_diplomaInstitute_label);
		diplomaInstituteText = toolkit.createText(parent, "", SWT.SINGLE);
		diplomaInstituteText.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

	}

	public void onBind(DataBindingContext bindingContext) {
		// Label binding
		IObservableValue diplomaLabelTextObserveTextObserveWidget = SWTObservables
				.observeText(diplomaLabelText, SWT.Modify);
		IObservableValue modelDiplomaLabelObserveValue = PojoObservables
				.observeValue(getModelObject(), Diploma.LABEL_PROPERTY);
		bindingContext.bindValue(diplomaLabelTextObserveTextObserveWidget,
				modelDiplomaLabelObserveValue, null, null);

		// Institute binding
		IObservableValue diplomaInstituteTextObserveTextObserveWidget = SWTObservables
				.observeText(diplomaInstituteText, SWT.Modify);
		IObservableValue modelDiplomaInstituteObserveValue = PojoObservables
				.observeValue(getModelObject(), Diploma.INSTITUTE_PROPERTY);
		bindingContext.bindValue(diplomaInstituteTextObserveTextObserveWidget,
				modelDiplomaInstituteObserveValue, null, null);

	}

}
