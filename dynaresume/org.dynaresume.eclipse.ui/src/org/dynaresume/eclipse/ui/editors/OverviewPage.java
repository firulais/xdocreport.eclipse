package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.core.Address;
import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.core.Person;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.jsr303.Jsr303BeansUpdateValueStrategyFactory;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.nebula.widgets.picture.PictureControl;
import org.eclipse.nebula.widgets.picture.forms.FormPictureControl;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import fr.opensagres.eclipse.forms.conversion.DateConverterRegistry;
import fr.opensagres.eclipse.forms.conversion.DateToStringConverter;
import fr.opensagres.eclipse.forms.conversion.StringToDateConverter;
import fr.opensagres.eclipse.forms.conversion.StringToDateValidator;
import fr.opensagres.eclipse.forms.widgets.DateTimeControl;
import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.ui.FormLayoutFactory;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormPage;

public class OverviewPage extends ReportingFormPage<Resume> implements
		IHyperlinkListener {

	public static final String ID = "overview";
	private Text firstNameText;
	private GridData gd_lastNameText;
	private GridData gd_firstNameText;
	private Text lastNameText;
	private DateTimeControl birthDayDateTime;
	private FormPictureControl photo;
	private Text emailText;
	private Text zipCodeText;
	private Text cityText;
	private Text streetText;
	private Control resumeTitleText;

	public OverviewPage(ReportingFormEditor<Resume> editor) {
		super(editor, ID, Messages.ResumeFormEditor_OverviewPage_title);
	}

	@Override
	protected Image getFormTitleImage() {
		return ImageResources.getImage(ImageResources.IMG_OVERVIEW_16);
	}

	@Override
	protected void fillBody(IManagedForm managedForm, FormToolkit toolkit) {
		Composite body = managedForm.getForm().getBody();
		body.setLayout(FormLayoutFactory.createFormTableWrapLayout(true, 2));

		Composite left = toolkit.createComposite(body);
		left.setLayout(FormLayoutFactory
				.createFormPaneTableWrapLayout(false, 1));
		left.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

		// General info section
		createGeneralInfoSection(toolkit, left);

		// Address section
		createAddressSection(toolkit, left);

		Composite right = toolkit.createComposite(body);
		right.setLayout(FormLayoutFactory.createFormPaneTableWrapLayout(false,
				1));
		right.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

		// Content section
		createContentSection(toolkit, right);

		createResumeInfoSection(toolkit, right);
	}

	private void createGeneralInfoSection(FormToolkit toolkit, Composite left) {
		Section section = toolkit.createSection(left, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setDescription(Messages.ResumeFormEditor_OverviewPage_GeneralInfo_desc);
		section.setText(Messages.ResumeFormEditor_OverviewPage_GeneralInfo_title);
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);

		Composite sbody = toolkit.createComposite(section);
		section.setClient(sbody);

		GridLayout glayout = new GridLayout();
		// glayout.horizontalSpacing = 10;
		glayout.numColumns = 2;
		sbody.setLayout(glayout);

		// First name
		toolkit.createLabel(
				sbody,
				Messages.ResumeFormEditor_OverviewPage_GeneralInfo_FirstName_label);
		firstNameText = toolkit.createText(sbody, "", SWT.SINGLE);
		gd_firstNameText = new GridData(GridData.FILL_HORIZONTAL);
		gd_firstNameText.widthHint = 150;
		firstNameText.setLayoutData(gd_firstNameText);

		// Last name
		toolkit.createLabel(
				sbody,
				Messages.ResumeFormEditor_OverviewPage_GeneralInfo_LastName_label);
		lastNameText = toolkit.createText(sbody, "", SWT.SINGLE);
		gd_lastNameText = new GridData(GridData.FILL_HORIZONTAL);
		gd_lastNameText.widthHint = 150;
		lastNameText.setLayoutData(gd_lastNameText);

		// Birthday
		toolkit.createLabel(
				sbody,
				Messages.ResumeFormEditor_OverviewPage_GeneralInfo_Birthday_label);
		birthDayDateTime = new DateTimeControl(sbody, SWT.NONE, SWT.SINGLE,
				SWT.FLAT, toolkit);
		birthDayDateTime.setOutputPattern(PlatformXDocReport.getDatePattern());
		GridData gd_birthDayDateTimet = new GridData(GridData.FILL_HORIZONTAL);
		gd_birthDayDateTimet.widthHint = 150;
		birthDayDateTime.setLayoutData(gd_birthDayDateTimet);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit,
				birthDayDateTime);

		// Email
		toolkit.createLabel(sbody,
				Messages.ResumeFormEditor_OverviewPage_GeneralInfo_Email_label);
		emailText = toolkit.createText(sbody, "", SWT.SINGLE);
		GridData gd_emailText = new GridData(GridData.FILL_HORIZONTAL);
		gd_emailText.widthHint = 150;
		emailText.setLayoutData(gd_emailText);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, sbody);
	}

	private void createAddressSection(FormToolkit toolkit, Composite parent) {
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setDescription(Messages.ResumeFormEditor_OverviewPage_Address_desc);
		section.setText(Messages.ResumeFormEditor_OverviewPage_Address_title);
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);

		Composite sbody = toolkit.createComposite(section);
		section.setClient(sbody);

		GridLayout glayout = new GridLayout();
		// glayout.horizontalSpacing = 10;
		glayout.numColumns = 2;
		sbody.setLayout(glayout);

		// Street
		toolkit.createLabel(sbody,
				Messages.ResumeFormEditor_OverviewPage_Address_Street_label);
		streetText = toolkit.createText(sbody, "", SWT.SINGLE);
		GridData streetData = new GridData(GridData.FILL_HORIZONTAL);
		streetData.widthHint = 150;
		streetText.setLayoutData(streetData);

		// Zip Code
		toolkit.createLabel(sbody,
				Messages.ResumeFormEditor_OverviewPage_Address_ZipCode_label);
		zipCodeText = toolkit.createText(sbody, "", SWT.SINGLE);
		GridData zipCodeData = new GridData(GridData.FILL_HORIZONTAL);
		zipCodeData.widthHint = 150;
		zipCodeText.setLayoutData(zipCodeData);

		// City
		toolkit.createLabel(sbody,
				Messages.ResumeFormEditor_OverviewPage_Address_City_label);
		cityText = toolkit.createText(sbody, "", SWT.SINGLE);
		GridData cityData = new GridData(GridData.FILL_HORIZONTAL);
		cityData.widthHint = 150;
		cityText.setLayoutData(cityData);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, sbody);
	}

	private void createContentSection(FormToolkit toolkit, Composite parent) {
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText(Messages.ResumeFormEditor_OverviewPage_ResumeContent_title);
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);

		Composite sbody = toolkit.createComposite(section);
		section.setClient(sbody);

		Composite container = createStaticSectionClient(toolkit, section);

		FormText text = createClient(container,
				Messages.ResumeFormEditor_OverviewPage_ResumeContent_content,
				toolkit);
		text.setImage("educations_page",
				ImageResources.getImage(ImageResources.IMG_EDUCATION_16));
		text.setImage("experiences_page",
				ImageResources.getImage(ImageResources.IMG_EXPERIENCES_16));
		text.setImage("skills_page",
				ImageResources.getImage(ImageResources.IMG_SKILLS_16));
		text.setImage("references_page",
				ImageResources.getImage(ImageResources.IMG_REFERENCES_16));
		text.setImage("languages_page",
				ImageResources.getImage(ImageResources.IMG_LANGUAGES_16));		
		text.setImage("hobbies_page",
				ImageResources.getImage(ImageResources.IMG_HOBBIES_16));
		section.setClient(container);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, sbody);
	}

	private void createResumeInfoSection(FormToolkit toolkit, Composite left) {
		Section section = toolkit.createSection(left, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setDescription(Messages.ResumeFormEditor_OverviewPage_ResumeInfo_desc);
		section.setText(Messages.ResumeFormEditor_OverviewPage_ResumeInfo_title);
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);

		Composite sbody = toolkit.createComposite(section);
		section.setClient(sbody);

		GridLayout glayout = new GridLayout();
		// glayout.horizontalSpacing = 10;
		glayout.numColumns = 2;
		sbody.setLayout(glayout);

		// Resume title
		toolkit.createLabel(sbody,
				Messages.ResumeFormEditor_OverviewPage_ResumeInfo_Title_label);
		resumeTitleText = toolkit.createText(sbody, "", SWT.SINGLE);
		GridData resumeTitleData = new GridData(GridData.FILL_HORIZONTAL);
		resumeTitleData.widthHint = 150;
		resumeTitleText.setLayoutData(resumeTitleData);		
		
		// Photo
		Label photoLabel = toolkit.createLabel(sbody,
				Messages.ResumeFormEditor_OverviewPage_ResumeInfo_Photo_label);
		GridData gd_photoLabel = new GridData();
		gd_photoLabel.verticalAlignment = SWT.TOP;
		photoLabel.setLayoutData(gd_photoLabel);
		photo = new FormPictureControl(sbody, toolkit);
		photo.setDefaultImage(ImageResources.getImage(ImageResources.IMG_EMPTY_PHOTO));
		GridData gd_photo = new GridData(GridData.FILL_HORIZONTAL);
		gd_photo.widthHint = 150;
		//photo.setLayoutData(gd_photo);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, photo);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, sbody);
	}

	protected final FormText createClient(Composite section, String content,
			FormToolkit toolkit) {
		FormText text = toolkit.createFormText(section, true);
		try {
			text.setText(content, true, false);
		} catch (SWTException e) {
			text.setText(e.getMessage(), false, false);
		}
		text.addHyperlinkListener(this);
		return text;
	}

	protected Composite createStaticSectionClient(FormToolkit toolkit,
			Composite parent) {
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		container.setLayout(FormLayoutFactory
				.createSectionClientTableWrapLayout(false, 1));
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		container.setLayoutData(data);
		return container;
	}

	public void onBind(DataBindingContext bindingContext) {
		onBindGeneralInfo(bindingContext);
		onBindAddress(bindingContext);
		onBindResumeInfo(bindingContext);
	}

	private void onBindGeneralInfo(DataBindingContext bindingContext) {

		// bind first name
		IObservableValue firstNameTextObserveTextObserveWidget = SWTObservables
				.observeText(firstNameText, SWT.Modify);
		IObservableValue getModel1FirstNameObserveValue = PojoObservables
				.observeValue(getModelObject().getOwner(),
						NaturalPerson.FIRST_NAME_PROPERTY);
		bindingContext.bindValue(firstNameTextObserveTextObserveWidget,
				getModel1FirstNameObserveValue,
				Jsr303BeansUpdateValueStrategyFactory
						.create(getModel1FirstNameObserveValue), null);

		// bind last name
		IObservableValue lastNameTextObserveTextObserveWidget = SWTObservables
				.observeText(lastNameText, SWT.Modify);
		IObservableValue getModel1LastNameObserveValue = PojoObservables
				.observeValue(getModelObject().getOwner(),
						NaturalPerson.LAST_NAME_PROPERTY);
		bindingContext.bindValue(lastNameTextObserveTextObserveWidget,
				getModel1LastNameObserveValue,
				Jsr303BeansUpdateValueStrategyFactory
						.create(getModel1LastNameObserveValue), null);

		// bind birthday
		StringToDateConverter stringToDateConverter = DateConverterRegistry
				.getStringToDateConverter(PlatformXDocReport.getDatePattern());
		DateToStringConverter dateToStringConverter = DateConverterRegistry
				.getDateToStringConverter(PlatformXDocReport.getDatePattern());
		IObservableValue birthDayDateTimeObserveTextObserveWidget = SWTObservables
				.observeText(birthDayDateTime.getDateFieldText(), SWT.Modify);
		IObservableValue personBirthDayObserveValue = PojoObservables
				.observeValue(getModelObject().getOwner(),
						NaturalPerson.BIRTH_DATE_PROPERTY);
		bindingContext.bindValue(
				birthDayDateTimeObserveTextObserveWidget,
				personBirthDayObserveValue,
				new UpdateValueStrategy().setAfterGetValidator(
						new StringToDateValidator(stringToDateConverter))
						.setConverter(stringToDateConverter),
				new UpdateValueStrategy().setConverter(dateToStringConverter));

		// bind email
		IObservableValue emailTextObserveTextObserveWidget = SWTObservables
				.observeText(emailText, SWT.Modify);
		IObservableValue personEmailObserveValue = PojoObservables
				.observeValue(getModelObject().getOwner(),
						NaturalPerson.EMAIL_PROPERTY);
		bindingContext.bindValue(emailTextObserveTextObserveWidget,
				personEmailObserveValue, Jsr303BeansUpdateValueStrategyFactory
						.create(personEmailObserveValue), null);

	}

	private void onBindAddress(DataBindingContext bindingContext) {

		// bind Street
		IObservableValue streetTextObserveTextObserveWidget = SWTObservables
				.observeText(streetText, SWT.Modify);
		IObservableValue addressStreetObserveValue = PojoObservables
				.observeValue(getModelObject().getOwner(),
						Person.ADDRESS_PROPERTY + "." + Address.STREET_PROPERTY);
		bindingContext.bindValue(streetTextObserveTextObserveWidget,
				addressStreetObserveValue, null, null);

		// bind city
		IObservableValue cityTextObserveTextObserveWidget = SWTObservables
				.observeText(cityText, SWT.Modify);
		IObservableValue addressCityObserveValue = PojoObservables
				.observeValue(getModelObject().getOwner(),
						Person.ADDRESS_PROPERTY + "." + Address.CITY_PROPERTY);
		bindingContext.bindValue(cityTextObserveTextObserveWidget,
				addressCityObserveValue, null, null);

		// bind Zip Code
		IObservableValue zipCodeTextObserveTextObserveWidget = SWTObservables
				.observeText(zipCodeText, SWT.Modify);
		IObservableValue addressZipCodeObserveValue = PojoObservables
				.observeValue(getModelObject().getOwner(),
						Person.ADDRESS_PROPERTY + "."
								+ Address.ZIPCODE_PROPERTY);
		bindingContext.bindValue(zipCodeTextObserveTextObserveWidget,
				addressZipCodeObserveValue, null, null);

	}

	private void onBindResumeInfo(DataBindingContext bindingContext) {

		// bind resume title
		IObservableValue resumeTitleTextObserveTextObserveWidget = SWTObservables
				.observeText(resumeTitleText, SWT.Modify);
		IObservableValue personEmailObserveValue = PojoObservables
				.observeValue(getModelObject(),
						Resume.TITLE_PROPERTY);
		bindingContext.bindValue(resumeTitleTextObserveTextObserveWidget,
				personEmailObserveValue, Jsr303BeansUpdateValueStrategyFactory
						.create(personEmailObserveValue), null);

		// bind photo
		IObservableValue photoObserveImageObserveWidget = BeansObservables
				.observeValue(photo, PictureControl.IMAGE_BYTEARRAY_PROPERTY);
		IObservableValue personPhotoObserveValue = PojoObservables
				.observeValue(getModelObject(), Resume.PICTURE_PROPERTY);
		bindingContext.bindValue(photoObserveImageObserveWidget,
				personPhotoObserveValue, null, null);
	}

	public void linkActivated(HyperlinkEvent e) {
		String href = (String) e.getHref();
		getEditor().setActivePage(href);
	}

	public void linkEntered(HyperlinkEvent e) {
		// Do nothing
	}

	public void linkExited(HyperlinkEvent e) {
		// Do nothing
	}

}
