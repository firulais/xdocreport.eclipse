package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Education;
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
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import fr.opensagres.eclipse.forms.conversion.DateConverterRegistry;
import fr.opensagres.eclipse.forms.conversion.DateToStringConverter;
import fr.opensagres.eclipse.forms.conversion.StringToDateConverter;
import fr.opensagres.eclipse.forms.conversion.StringToDateValidator;
import fr.opensagres.eclipse.forms.editor.ModelDetailsPage;
import fr.opensagres.eclipse.forms.widgets.DateTimeControl;
import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages;

public class EducationDetailsPage extends ModelDetailsPage<Education> {

	private Text educationLabelText;
	private Text educationInstituteText;
	private DateTimeControl educationDateTime;

	@Override
	protected void onCreateUI(Composite parent) {

		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);

		FormToolkit toolkit = getManagedForm().getToolkit();

		Section educationDetailSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		educationDetailSection.marginWidth = 10;
		educationDetailSection
				.setText(Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_title); //$NON-NLS-1$
		educationDetailSection
				.setDescription(Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_desc); //$NON-NLS-1$

		TableWrapData td = new TableWrapData(TableWrapData.FILL,
				TableWrapData.TOP);
		td.grabHorizontal = true;
		educationDetailSection.setLayoutData(td);

		Composite client = toolkit.createComposite(educationDetailSection);
		educationDetailSection.setClient(client);

		// Create generic content
		createBodyContent(toolkit, client);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);
	}

	private void createBodyContent(FormToolkit toolkit, Composite parent) {
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 2;
		parent.setLayout(glayout);

		// Education date
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_educationDate_label);
		educationDateTime = new DateTimeControl(parent, SWT.NONE, SWT.SINGLE,
				SWT.FLAT, toolkit);
		educationDateTime.setOutputPattern(PlatformXDocReport.getDatePattern());
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 150;
		educationDateTime.setLayoutData(data);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit,
				educationDateTime);

		// Education label
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_educationLabel_label);
		educationLabelText = toolkit.createText(parent, "", SWT.SINGLE);
		educationLabelText
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Education institute
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_EducationsPage_EducationDetailsPage_educationInstitute_label);
		educationInstituteText = toolkit.createText(parent, "", SWT.SINGLE);
		educationInstituteText.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

	}

	public void onBind(DataBindingContext bindingContext) {

		// Date binding
		StringToDateConverter stringToDateConverter = DateConverterRegistry
				.getStringToDateConverter(PlatformXDocReport.getDatePattern());
		DateToStringConverter dateToStringConverter = DateConverterRegistry
				.getDateToStringConverter(PlatformXDocReport.getDatePattern());
		IObservableValue educationDateTimeObserveTextObserveWidget = SWTObservables
				.observeText(educationDateTime.getDateFieldText(), SWT.Modify);
		IObservableValue educationDateObserveValue = PojoObservables
				.observeValue(getModelObject(),
						Education.DATE_PROPERTY);
		bindingContext.bindValue(
				educationDateTimeObserveTextObserveWidget,
				educationDateObserveValue,
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
