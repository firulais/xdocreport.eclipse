package org.dynaresume.project.eclipse.ui.editors.client;

import org.dynaresume.domain.project.Client;
import org.dynaresume.project.eclipse.ui.internal.ImageResources;
import org.dynaresume.project.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.jsr303.Jsr303BeansUpdateValueStrategyFactory;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import fr.opensagres.xdocreport.eclipse.ui.FormLayoutFactory;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormPage;

public class OverviewPage extends ReportingFormPage {
	
	public static final String ID = "overview";

	private Text nameText;
	private Text descriptionText;

	public OverviewPage(ReportingFormEditor editor) {
		super(editor, ID, Messages.ClientFormEditor_OverviewPage_title);
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
		// createAddressSection(toolkit, left);

		Composite right = toolkit.createComposite(body);
		right.setLayout(FormLayoutFactory.createFormPaneTableWrapLayout(false,
				1));
		right.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

		// Content section
		// createContentSection(toolkit, right);

		// createResumeInfoSection(toolkit, right);

	}

	private void createGeneralInfoSection(FormToolkit toolkit, Composite left) {
		Section section = toolkit.createSection(left, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setDescription(Messages.ClientFormEditor_OverviewPage_GeneralInfo_desc);
		section.setText(Messages.ClientFormEditor_OverviewPage_GeneralInfo_title);
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);

		Composite sbody = toolkit.createComposite(section);
		section.setClient(sbody);

		GridLayout glayout = new GridLayout();
		// glayout.horizontalSpacing = 10;
		glayout.numColumns = 2;
		sbody.setLayout(glayout);

		// Name
		toolkit.createLabel(sbody,
				Messages.ClientFormEditor_OverviewPage_GeneralInfo_Name_label);
		nameText = toolkit.createText(sbody, "", SWT.SINGLE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		nameText.setLayoutData(gridData);

		// Description
		Label descriptionLabel = toolkit
				.createLabel(
						sbody,
						Messages.ClientFormEditor_OverviewPage_GeneralInfo_Description_label);
		GridData gd_photoLabel = new GridData();
		gd_photoLabel.verticalAlignment = SWT.TOP;
		descriptionLabel.setLayoutData(gd_photoLabel);
		descriptionText = toolkit.createText(sbody, "", SWT.MULTI | SWT.WRAP);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		descriptionText.setLayoutData(gridData);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, sbody);
	}

	// private void createContentSection(FormToolkit toolkit, Composite parent)
	// {
	// Section section = toolkit.createSection(parent, Section.TITLE_BAR);
	// section.setText(Messages.ClientFormEditor_OverviewPage_ResumeContent_title);
	// TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
	// section.setLayoutData(data);
	//
	// Composite sbody = toolkit.createComposite(section);
	// section.setClient(sbody);
	//
	// Composite container = createStaticSectionClient(toolkit, section);
	//
	// FormText text = createClient(container,
	// Messages.ClientFormEditor_OverviewPage_ResumeContent_content,
	// toolkit);
	// text.setImage("educations_page",
	// ImageResources.getImage(ImageResources.IMG_EDUCATION_16));
	// text.setImage("experiences_page",
	// ImageResources.getImage(ImageResources.IMG_EXPERIENCES_16));
	// text.setImage("skills_page",
	// ImageResources.getImage(ImageResources.IMG_SKILLS_16));
	// text.setImage("hobbies_page",
	// ImageResources.getImage(ImageResources.IMG_HOBBIES_16));
	// section.setClient(container);
	//
	// SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, sbody);
	// }

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
	}

	private void onBindGeneralInfo(DataBindingContext bindingContext) {

		// bind name skill
		IObservableValue firstNameTextObserveTextObserveWidget = SWTObservables
				.observeText(nameText, SWT.Modify);
		IObservableValue getModel1FirstNameObserveValue = PojoObservables
				.observeValue(getModelObject(), Client.NAME_PROPERTY);
		bindingContext.bindValue(firstNameTextObserveTextObserveWidget,
				getModel1FirstNameObserveValue,
				Jsr303BeansUpdateValueStrategyFactory
						.create(getModel1FirstNameObserveValue), null);

	}


}
