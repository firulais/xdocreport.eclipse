package org.dynaresume.project.eclipse.ui.editors.project;

import java.net.URL;

import org.dynaresume.domain.project.Client;
import org.dynaresume.domain.project.Project;
import org.dynaresume.eclipse.search.ui.modelpickers.ModelPickersFactory;
import org.dynaresume.project.eclipse.ui.editors.client.ClientEditorInput;
import org.dynaresume.project.eclipse.ui.editors.client.ClientFormEditor;
import org.dynaresume.project.eclipse.ui.internal.Activator;
import org.dynaresume.project.eclipse.ui.internal.ImageResources;
import org.dynaresume.project.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.jsr303.Jsr303BeansUpdateValueStrategyFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.nebula.widgets.modelpicker.ModelPicker;
import org.eclipse.nebula.widgets.modelpicker.ModelPropertyChangeListener;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import fr.opensagres.eclipse.forms.widgets.SimpleWikiText;
import fr.opensagres.xdocreport.eclipse.ui.FormLayoutFactory;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormPage;

public class OverviewPage extends ReportingFormPage<Project> implements
		IHyperlinkListener {

	public static final String ID = "overview";

	private Text nameText;
	private SimpleWikiText descriptionText;
	private Text urlText;

	private Hyperlink urlHyperlink;

	private ModelPicker<Client> clientPicker;

	public OverviewPage(ReportingFormEditor editor) {
		super(editor, ID, Messages.ProjectFormEditor_OverviewPage_title);
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

		Composite right = toolkit.createComposite(body);
		right.setLayout(FormLayoutFactory.createFormPaneTableWrapLayout(false,
				1));
		right.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

		// Content section
		createContentSection(toolkit, right);

		// Composite bottom = toolkit.createComposite(body);
		// bottom.setLayout(FormLayoutFactory.createFormPaneTableWrapLayout(false,
		// 1));
		// TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		// data.colspan = 2;
		// bottom.setLayoutData(data);

		// Description section
		// createDescriptionSection(toolkit, bottom);

	}

	private void createGeneralInfoSection(FormToolkit toolkit, Composite left) {
		Section section = toolkit.createSection(left, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setDescription(Messages.ProjectFormEditor_OverviewPage_GeneralInfo_desc);
		section.setText(Messages.ProjectFormEditor_OverviewPage_GeneralInfo_title);
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
				Messages.ProjectFormEditor_OverviewPage_GeneralInfo_Name_label);
		nameText = toolkit.createText(sbody, "", SWT.SINGLE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		nameText.setLayoutData(gridData);

		// URL
		urlHyperlink = toolkit.createHyperlink(sbody,
				Messages.ProjectFormEditor_OverviewPage_GeneralInfo_URL_label,
				SWT.NONE);
		urlHyperlink.addHyperlinkListener(this);
		urlText = toolkit.createText(sbody, "", SWT.SINGLE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		urlText.setLayoutData(gridData);

		// Client
		final Hyperlink hyperlink = toolkit
				.createHyperlink(
						sbody,
						Messages.ProjectFormEditor_OverviewPage_GeneralInfo_Client_label,
						SWT.NONE);
		clientPicker = ModelPickersFactory.createClientPicker(sbody, SWT.NONE,
				SWT.SINGLE, toolkit, ((ProjectFormEditor) OverviewPage.this
						.getEditor()).getClientService());

		new ModelPropertyChangeListener<Client>(clientPicker) {
			@Override
			protected void setModelEnabled(boolean enabled) {
				hyperlink.setEnabled(enabled);
			}
		};

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		clientPicker.setLayoutData(gridData);

		hyperlink.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				handleSkillNameHyperlink(e);
			}
		});

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, clientPicker);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, sbody);
	}

	private void handleSkillNameHyperlink(HyperlinkEvent e) {
		IWorkbenchPage page = getSite().getWorkbenchWindow().getActivePage();
		try {
			page.openEditor(new ClientEditorInput(getEditor().getEntry(),
					(Client) clientPicker.getModel()), ClientFormEditor.ID,
					false);
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void createContentSection(FormToolkit toolkit, Composite parent) {
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText(Messages.ProjectFormEditor_OverviewPage_ProjectContent_title);
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);

		Composite sbody = toolkit.createComposite(section);
		section.setClient(sbody);

		Composite container = createStaticSectionClient(toolkit, section);

		FormText text = createClient(container,
				Messages.ProjectFormEditor_OverviewPage_ProjectContent_content,
				toolkit);
		text.setImage("descriptions_page",
				ImageResources.getImage(ImageResources.IMG_DESCRIPTION_16));
		text.setImage("skills_page",
				ImageResources.getImage(ImageResources.IMG_SKILLS_16));
		section.setClient(container);

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
	}

	private void createDescriptionSection(FormToolkit toolkit, Composite bottom) {
		Section section = toolkit.createSection(bottom, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setDescription(Messages.ProjectFormEditor_OverviewPage_Description_desc);
		section.setText(Messages.ProjectFormEditor_OverviewPage_Description_title);
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);

		Composite sbody = toolkit.createComposite(section);
		section.setClient(sbody);

		GridLayout glayout = new GridLayout();
		// glayout.horizontalSpacing = 10;
		glayout.numColumns = 1;
		sbody.setLayout(glayout);

		// Description
		descriptionText = new SimpleWikiText(sbody, SWT.NONE, SWT.NONE, toolkit);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		descriptionText.setLayoutData(gridData);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, sbody);

	}

	private void onBindGeneralInfo(DataBindingContext bindingContext) {

		// bind name skill
		IObservableValue firstNameTextObserveTextObserveWidget = SWTObservables
				.observeText(nameText, SWT.Modify);
		IObservableValue getModel1FirstNameObserveValue = PojoObservables
				.observeValue(getModelObject(), Project.NAME_PROPERTY);
		bindingContext.bindValue(firstNameTextObserveTextObserveWidget,
				getModel1FirstNameObserveValue,
				Jsr303BeansUpdateValueStrategyFactory
						.create(getModel1FirstNameObserveValue), null);

		// // bind description skill
		// IObservableValue descriptionTextObserveTextObserveWidget =
		// SWTObservables
		// .observeText(descriptionText.getTextarea(), SWT.Modify);
		// IObservableValue modelDescriptionObserveValue = PojoObservables
		// .observeValue(getModelObject(), Project.DESCRIPTION_PROPERTY);
		// bindingContext.bindValue(descriptionTextObserveTextObserveWidget,
		// modelDescriptionObserveValue,
		// Jsr303BeansUpdateValueStrategyFactory
		// .create(modelDescriptionObserveValue), null);

		// bind client
		IObservableValue clientSearchObserveTextObserveWidget = BeansObservables
				.observeValue(clientPicker, ModelPicker.MODEL);
		IObservableValue modelClientObserveValue = PojoObservables
				.observeValue(getModelObject(), Project.CLIENT_PROPERTY);
		bindingContext.bindValue(clientSearchObserveTextObserveWidget,
				modelClientObserveValue, Jsr303BeansUpdateValueStrategyFactory
						.create(modelClientObserveValue), null);

		// bind url skill
		IObservableValue urlTextObserveTextObserveWidget = SWTObservables
				.observeText(urlText, SWT.Modify);
		IObservableValue modelURLObserveValue = PojoObservables.observeValue(
				getModelObject(), Project.URL_PROPERTY);
		bindingContext.bindValue(urlTextObserveTextObserveWidget,
				modelURLObserveValue, Jsr303BeansUpdateValueStrategyFactory
						.create(modelURLObserveValue), null);

		// bind client
		// clientSearch.setModel(getModelObject().getClient());

	}

	public void linkActivated(HyperlinkEvent ev) {
		if (urlHyperlink.equals(ev.getSource())) {
			String url = urlText.getText();
			try {
				PlatformUI.getWorkbench().getBrowserSupport()
						.getExternalBrowser().openURL(new URL(url));
			} catch (Exception e) {
				// TODO : log that
				e.printStackTrace();
				IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						e.getMessage(), e);
				ErrorDialog.openError(nameText.getShell(), Messages.error,
						e.getMessage(), status);
			}
		} else {
			String href = (String) ev.getHref();
			getEditor().setActivePage(href);
		}
	}

	public void linkEntered(HyperlinkEvent e) {
		// Do nothing
	}

	public void linkExited(HyperlinkEvent e) {
		// Do nothing
	}
}
