package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.Education;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
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

import fr.opensagres.eclipse.forms.conversion.DateConverterRegistry;
import fr.opensagres.eclipse.forms.conversion.DateToStringConverter;
import fr.opensagres.eclipse.forms.conversion.StringToDateConverter;
import fr.opensagres.eclipse.forms.conversion.StringToDateValidator;
import fr.opensagres.eclipse.forms.editor.ModelDetailsPage;
import fr.opensagres.eclipse.forms.widgets.DateTimeControl;
import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;

public class EducationDetailsPage extends ModelDetailsPage<Education> {

	private Text educationLabelText;
	private Text educationInstituteText;
	private DateTimeControl educationStartDateTime;
	private DateTimeControl educationEndDateTime;

	@Override
	protected void onCreateUI(Composite parent) {

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		parent.setLayout(layout);

		FormToolkit toolkit = getManagedForm().getToolkit();

		Section educationDetailSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		educationDetailSection.marginWidth = 10;
		educationDetailSection
				.setText(Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_title); //$NON-NLS-1$
		educationDetailSection
				.setDescription(Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_desc); //$NON-NLS-1$

		GridData td = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		educationDetailSection.setLayoutData(td);

		Composite client = toolkit.createComposite(educationDetailSection);
		educationDetailSection.setClient(client);

		// Create generic content
		createBodyContent(toolkit, client);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);
	}

	private void createBodyContent(FormToolkit toolkit, Composite parent) {
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 4;
		parent.setLayout(glayout);

		// Education start date
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_educationStartDate_label);
		educationStartDateTime = new DateTimeControl(parent, SWT.NONE, SWT.SINGLE,
				SWT.FLAT, toolkit);
		educationStartDateTime.setOutputPattern(PlatformXDocReport.getDatePattern());
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 150;
		educationStartDateTime.setLayoutData(data);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit,
				educationStartDateTime);

		// Education end date
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_educationEndDate_label);
		educationEndDateTime = new DateTimeControl(parent, SWT.NONE, SWT.SINGLE,
				SWT.FLAT, toolkit);
		educationEndDateTime.setOutputPattern(PlatformXDocReport.getDatePattern());
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 150;
		educationEndDateTime.setLayoutData(data);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit,
				educationEndDateTime);

		// Education label
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_educationLabel_label);
		educationLabelText = toolkit.createText(parent, "", SWT.SINGLE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		educationLabelText
				.setLayoutData(data);

		// Education institute
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_educationInstitute_label);
		educationInstituteText = toolkit.createText(parent, "", SWT.SINGLE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		educationInstituteText.setLayoutData(data);

	}

	public void onBind(DataBindingContext bindingContext) {

		StringToDateConverter stringToDateConverter = DateConverterRegistry
				.getStringToDateConverter(PlatformXDocReport.getDatePattern());
		DateToStringConverter dateToStringConverter = DateConverterRegistry
				.getDateToStringConverter(PlatformXDocReport.getDatePattern());

		// Start Date binding
		IObservableValue educationStartDateTimeObserveTextObserveWidget = SWTObservables
				.observeText(educationStartDateTime.getDateFieldText(), SWT.Modify);
		IObservableValue educationStartDateObserveValue = PojoObservables
				.observeValue(getModelObject(),
						Education.START_DATE_PROPERTY);
		bindingContext.bindValue(
				educationStartDateTimeObserveTextObserveWidget,
				educationStartDateObserveValue,
				new UpdateValueStrategy().setAfterGetValidator(
						new StringToDateValidator(stringToDateConverter))
						.setConverter(stringToDateConverter),
				new UpdateValueStrategy().setConverter(dateToStringConverter));

		// End Date binding
		IObservableValue educationEndDateTimeObserveTextObserveWidget = SWTObservables
				.observeText(educationEndDateTime.getDateFieldText(), SWT.Modify);
		IObservableValue educationEndDateObserveValue = PojoObservables
				.observeValue(getModelObject(),
						Education.END_DATE_PROPERTY);
		bindingContext.bindValue(
				educationEndDateTimeObserveTextObserveWidget,
				educationEndDateObserveValue,
				new UpdateValueStrategy().setAfterGetValidator(
						new StringToDateValidator(stringToDateConverter))
						.setConverter(stringToDateConverter),
				new UpdateValueStrategy().setConverter(dateToStringConverter));

		// Label binding
		IObservableValue educationLabelTextObserveTextObserveWidget = SWTObservables
				.observeText(educationLabelText, SWT.Modify);
		IObservableValue modelEducationLabelObserveValue = PojoObservables
				.observeValue(getModelObject(), Education.LABEL_PROPERTY);
		bindingContext.bindValue(educationLabelTextObserveTextObserveWidget,
				modelEducationLabelObserveValue, null, null);

		// Institute binding
		IObservableValue educationInstituteTextObserveTextObserveWidget = SWTObservables
				.observeText(educationInstituteText, SWT.Modify);
		IObservableValue modelEducationInstituteObserveValue = PojoObservables
				.observeValue(getModelObject(), Education.INSTITUTE_PROPERTY);
		bindingContext.bindValue(
				educationInstituteTextObserveTextObserveWidget,
				modelEducationInstituteObserveValue, null, null);

	}

}
