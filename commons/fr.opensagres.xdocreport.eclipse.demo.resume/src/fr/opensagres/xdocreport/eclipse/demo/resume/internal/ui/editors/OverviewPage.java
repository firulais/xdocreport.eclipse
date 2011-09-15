package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.User;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages;
import fr.opensagres.xdocreport.eclipse.ui.FormLayoutFactory;
import fr.opensagres.xdocreport.eclipse.ui.SingleSourcingUtils;
import fr.opensagres.xdocreport.eclipse.ui.editors.AbstractFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.AbstractFormPage;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class OverviewPage extends AbstractFormPage<User> {
	private DataBindingContext m_bindingContext;

	public static final String ID = "overview";
	private Text firstNameText;
	private GridData gd_lastNameText;
	private GridData gd_firstNameText;
	private Text lastNameText;
	private Text txtNewText;

	public OverviewPage(AbstractFormEditor<User> editor) {
		super(editor, ID, "Overview");
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);
		final ScrolledForm form = managedForm.getForm();
		final FormToolkit toolkit = managedForm.getToolkit();
		form.setText(Messages.ResumeFormEditor_OverviewPage_title);
		toolkit.decorateFormHeading(form.getForm());
		fillBody(managedForm, toolkit);
	}

	private void fillBody(IManagedForm managedForm, FormToolkit toolkit) {
		Composite body = managedForm.getForm().getBody();
		TableWrapLayout tableWrapLayout = FormLayoutFactory.createFormTableWrapLayout(true, 2);
		tableWrapLayout.numColumns = 1;
		body.setLayout(tableWrapLayout);

		Composite left = toolkit.createComposite(body);
		left.setLayout(FormLayoutFactory
				.createFormPaneTableWrapLayout(false, 1));
		left.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

		Section section = toolkit.createSection(left, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setDescription(Messages.ResumeFormEditor_OverviewPage_GeneralInfo_desc);
		section.setText(Messages.ResumeFormEditor_OverviewPage_GeneralInfo_title);

		Composite sbody = toolkit.createComposite(section);
		section.setClient(sbody);

		GridLayout glayout = new GridLayout();
		// glayout.horizontalSpacing = 10;
		glayout.numColumns = 2;
		sbody.setLayout(glayout);

		// toolkit.createLabel(sbody, "Birthday:");
		// DateTime birthday = new DateTime(sbody, SWT.CALENDAR);
		// GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.widthHint = 150;
		// birthday.setLayoutData(gd);
		// birthday

		// First name
		toolkit.createLabel(sbody, Messages.ResumeFormEditor_FirstName_label);
		firstNameText = toolkit.createText(sbody, "", SWT.SINGLE);		
		gd_firstNameText = new GridData(GridData.FILL_HORIZONTAL);
		gd_firstNameText.widthHint = 150;
		firstNameText.setLayoutData(gd_firstNameText);

		// Last name
		toolkit.createLabel(sbody, Messages.ResumeFormEditor_LastName_label);
		lastNameText = toolkit.createText(sbody, "", SWT.SINGLE);
		gd_lastNameText = new GridData(GridData.FILL_HORIZONTAL);
		gd_lastNameText.widthHint = 150;
		lastNameText.setLayoutData(gd_lastNameText);

		
//		if (defaultValue != null) {
//			text.setText(defaultValue);
//		}
		
//		createDecoratedTextField(Messages.ResumeFormEditor_Birthday_label,
//				toolkit, sbody, null, null);

		toolkit.createLabel(sbody, Messages.ResumeFormEditor_Birthday_label);
		txtNewText = managedForm.getToolkit().createText(sbody, "", SWT.NONE);
		txtNewText.setText(Messages.OverviewPage_txtNewText_text);
		txtNewText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, sbody);

		m_bindingContext = initDataBindings();
	}

//	private void createDecoratedTextField(String label, FormToolkit toolkit,
//			Composite parent, String defaultValue, final IMessageManager mmng) {
//		toolkit.createLabel(parent, label);
//		final Text text = toolkit.createText(parent, "", SWT.SINGLE);
//		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
//		gd.widthHint = 150;
//		text.setLayoutData(gd);
//
//		if (defaultValue != null) {
//			text.setText(defaultValue);
//		}
//	}
	
	
	public User getUser() {
		return super.getModel();
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue firstNameTextObserveTextObserveWidget = SWTObservables.observeText(firstNameText, SWT.Modify);
		IObservableValue getModel1FirstNameObserveValue = PojoObservables.observeValue(getUser(), "firstName");
		bindingContext.bindValue(firstNameTextObserveTextObserveWidget, getModel1FirstNameObserveValue, null, null);
		//
		IObservableValue lastNameTextObserveTextObserveWidget = SWTObservables.observeText(lastNameText, SWT.Modify);
		IObservableValue getModel1LastNameObserveValue = PojoObservables.observeValue(getUser(), "lastName");
		bindingContext.bindValue(lastNameTextObserveTextObserveWidget, getModel1LastNameObserveValue, null, null);
		//
		return bindingContext;
	}
}
