package org.dynaresume.admin.eclipse.ui.skill.editors;

import java.net.URL;

import org.dynaresume.admin.eclipse.ui.internal.Activator;
import org.dynaresume.admin.eclipse.ui.internal.ImageResources;
import org.dynaresume.admin.eclipse.ui.internal.Messages;
import org.dynaresume.domain.hr.Skill;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.jsr303.Jsr303BeansUpdateValueStrategyFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import fr.opensagres.eclipse.forms.editor.ModelToolbarFormPage;
import fr.opensagres.xdocreport.eclipse.ui.FormLayoutFactory;

public class OverviewPage extends ModelToolbarFormPage<Skill> implements
		IHyperlinkListener {

	public static final String ID = "overview";
	private Text nameText;
	private Text descriptionText;
	private Text urlText;

	public OverviewPage(SkillFormEditor editor) {
		super(editor, ID, Messages.SkillFormEditor_OverviewPage_title);
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
		section.setDescription(Messages.SkillFormEditor_OverviewPage_GeneralInfo_desc);
		section.setText(Messages.SkillFormEditor_OverviewPage_GeneralInfo_title);
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
				Messages.SkillFormEditor_OverviewPage_GeneralInfo_Name_label);
		nameText = toolkit.createText(sbody, "", SWT.SINGLE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		nameText.setLayoutData(gridData);

		// URL
		Hyperlink urlHyperlink = toolkit.createHyperlink(sbody,
				Messages.SkillFormEditor_OverviewPage_GeneralInfo_URL_label,
				SWT.NONE);
		urlHyperlink.addHyperlinkListener(this);
		urlText = toolkit.createText(sbody, "", SWT.SINGLE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		urlText.setLayoutData(gridData);

		// Description
		Label descriptionLabel = toolkit
				.createLabel(
						sbody,
						Messages.SkillFormEditor_OverviewPage_GeneralInfo_Description_label);
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
	// section.setText(Messages.SkillFormEditor_OverviewPage_ResumeContent_title);
	// TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
	// section.setLayoutData(data);
	//
	// Composite sbody = toolkit.createComposite(section);
	// section.setClient(sbody);
	//
	// Composite container = createStaticSectionClient(toolkit, section);
	//
	// FormText text = createClient(container,
	// Messages.SkillFormEditor_OverviewPage_ResumeContent_content,
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
	}

	private void onBindGeneralInfo(DataBindingContext bindingContext) {

		// bind name skill
		IObservableValue firstNameTextObserveTextObserveWidget = SWTObservables
				.observeText(nameText, SWT.Modify);
		IObservableValue getModel1FirstNameObserveValue = PojoObservables
				.observeValue(getModelObject(), Skill.NAME_PROPERTY);
		bindingContext.bindValue(firstNameTextObserveTextObserveWidget,
				getModel1FirstNameObserveValue,
				Jsr303BeansUpdateValueStrategyFactory
						.create(getModel1FirstNameObserveValue), null);

		// bind url skill
		IObservableValue urlTextObserveTextObserveWidget = SWTObservables
				.observeText(urlText, SWT.Modify);
		IObservableValue modelURLObserveValue = PojoObservables.observeValue(
				getModelObject(), Skill.URL_PROPERTY);
		bindingContext.bindValue(urlTextObserveTextObserveWidget,
				modelURLObserveValue, Jsr303BeansUpdateValueStrategyFactory
						.create(modelURLObserveValue), null);

		// bind description skill
		IObservableValue descriptionTextObserveTextObserveWidget = SWTObservables
				.observeText(descriptionText, SWT.Modify);
		IObservableValue modelDescriptionObserveValue = PojoObservables
				.observeValue(getModelObject(), Skill.DESCRIPTION_PROPERTY);
		bindingContext.bindValue(descriptionTextObserveTextObserveWidget,
				modelDescriptionObserveValue,
				Jsr303BeansUpdateValueStrategyFactory
						.create(modelDescriptionObserveValue), null);

	}

	public void linkActivated(HyperlinkEvent ev) {
		String url = urlText.getText();
		try {
			PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser()
					.openURL(new URL(url));
		} catch (Exception e) {
			// TODO : log that
			e.printStackTrace();
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
					e.getMessage(), e);
			ErrorDialog.openError(nameText.getShell(), Messages.error,
					e.getMessage(), status);
		}
	}

	public void linkEntered(HyperlinkEvent e) {
		// Do nothing
	}

	public void linkExited(HyperlinkEvent e) {
		// Do nothing
	}

}
