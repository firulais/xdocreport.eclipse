package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.Experience;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import fr.opensagres.eclipse.forms.conversion.DateConverterRegistry;
import fr.opensagres.eclipse.forms.conversion.DateToStringConverter;
import fr.opensagres.eclipse.forms.conversion.StringToDateConverter;
import fr.opensagres.eclipse.forms.conversion.StringToDateValidator;
import fr.opensagres.eclipse.forms.editor.ModelDetailsPage;
import fr.opensagres.eclipse.forms.widgets.DateTimeControl;
import fr.opensagres.eclipse.forms.widgets.SimpleWikiText;
import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;

public class ExperienceDetailsPage extends ModelDetailsPage<Experience> {

	private Text experienceTitleText;
	private Text experienceMissionText;
	private SimpleWikiText experienceDetailWikiText;
	private DateTimeControl experienceStartDateDateTime;
	private DateTimeControl experienceEndDateDateTime;

	@Override
	protected void onCreateUI(Composite parent) {

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		parent.setLayout(layout);

		FormToolkit toolkit = getManagedForm().getToolkit();

		Section experienceDetailSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		experienceDetailSection.marginWidth = 10;
		experienceDetailSection
				.setText(Messages.ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_title); //$NON-NLS-1$
		experienceDetailSection
				.setDescription(Messages.ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_desc); //$NON-NLS-1$

		GridData td = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		experienceDetailSection.setLayoutData(td);

		Composite client = toolkit.createComposite(experienceDetailSection);
		experienceDetailSection.setClient(client);

		// Create generic content
		createBodyContent(toolkit, client);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);
	}

	private void createBodyContent(FormToolkit toolkit, Composite parent) {
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 4;
		parent.setLayout(glayout);

		// Start date
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceStartDate_label);
		experienceStartDateDateTime = new DateTimeControl(parent, SWT.NONE,
				SWT.SINGLE, SWT.FLAT, toolkit);
		experienceStartDateDateTime.setOutputPattern(PlatformXDocReport
				.getDatePattern());
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 150;
		experienceStartDateDateTime.setLayoutData(data);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit,
				experienceStartDateDateTime);

		// Start date
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceEndDate_label);
		experienceEndDateDateTime = new DateTimeControl(parent, SWT.NONE,
				SWT.SINGLE, SWT.FLAT, toolkit);
		experienceEndDateDateTime.setOutputPattern(PlatformXDocReport
				.getDatePattern());
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 150;
		experienceEndDateDateTime.setLayoutData(data);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit,
				experienceEndDateDateTime);

		// Experience title
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceTitle_label);
		experienceTitleText = toolkit.createText(parent, "", SWT.SINGLE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		experienceTitleText.setLayoutData(data);

		// Experience mission
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceMission_label);
		experienceMissionText = toolkit.createText(parent, "", SWT.SINGLE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		experienceMissionText.setLayoutData(data);

		// Experience detail
		Label experienceDetailLabel = toolkit
				.createLabel(
						parent,
						Messages.ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceDetail_label);
		data = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		experienceDetailLabel.setLayoutData(data);
		experienceDetailWikiText = new SimpleWikiText(parent, SWT.NONE,
				SWT.NONE, toolkit);
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 3;
		experienceDetailWikiText.setLayoutData(data);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit,
				experienceDetailWikiText);
	}

	public void onBind(DataBindingContext bindingContext) {

		StringToDateConverter stringToDateConverter = DateConverterRegistry
				.getStringToDateConverter(PlatformXDocReport.getDatePattern());
		DateToStringConverter dateToStringConverter = DateConverterRegistry
				.getDateToStringConverter(PlatformXDocReport.getDatePattern());

		// Start Date binding
		IObservableValue experienceStartDateTimeObserveTextObserveWidget = SWTObservables
				.observeText(experienceStartDateDateTime.getDateFieldText(),
						SWT.Modify);
		IObservableValue experienceStartDateObserveValue = PojoObservables
				.observeValue(getModelObject(), Experience.START_DATE_PROPERTY);
		bindingContext.bindValue(
				experienceStartDateTimeObserveTextObserveWidget,
				experienceStartDateObserveValue,
				new UpdateValueStrategy().setAfterGetValidator(
						new StringToDateValidator(stringToDateConverter))
						.setConverter(stringToDateConverter),
				new UpdateValueStrategy().setConverter(dateToStringConverter));

		// End Date binding
		IObservableValue experienceEndDateTimeObserveTextObserveWidget = SWTObservables
				.observeText(experienceEndDateDateTime.getDateFieldText(),
						SWT.Modify);
		IObservableValue experienceEndDateObserveValue = PojoObservables
				.observeValue(getModelObject(), Experience.END_DATE_PROPERTY);
		bindingContext.bindValue(
				experienceEndDateTimeObserveTextObserveWidget,
				experienceEndDateObserveValue,
				new UpdateValueStrategy().setAfterGetValidator(
						new StringToDateValidator(stringToDateConverter))
						.setConverter(stringToDateConverter),
				new UpdateValueStrategy().setConverter(dateToStringConverter));

		// Title binding
		IObservableValue experienceTitleTextObserveTextObserveWidget = SWTObservables
				.observeText(experienceTitleText, SWT.Modify);
		IObservableValue modelExperienceTitleObserveValue = PojoObservables
				.observeValue(getModelObject(), Experience.TITLE_PROPERTY);
		bindingContext.bindValue(experienceTitleTextObserveTextObserveWidget,
				modelExperienceTitleObserveValue, null, null);

		// Mission binding
		IObservableValue experienceMissionTextObserveTextObserveWidget = SWTObservables
				.observeText(experienceMissionText, SWT.Modify);
		IObservableValue modelExperienceMissionObserveValue = PojoObservables
				.observeValue(getModelObject(), Experience.MISSION_PROPERTY);
		bindingContext.bindValue(experienceMissionTextObserveTextObserveWidget,
				modelExperienceMissionObserveValue, null, null);

		// Detail binding
		IObservableValue experienceDetailTextObserveTextObserveWidget = SWTObservables
				.observeText(experienceDetailWikiText.getTextarea(), SWT.Modify);
		IObservableValue modelExperienceDetailObserveValue = PojoObservables
				.observeValue(getModelObject(), Experience.DETAIL_PROPERTY);
		bindingContext.bindValue(experienceDetailTextObserveTextObserveWidget,
				modelExperienceDetailObserveValue, null, null);
	}

}
