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

public class OverviewPage extends AbstractFormPage<User> {

	public static final String ID = "overview";

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
		body.setLayout(FormLayoutFactory.createFormTableWrapLayout(true, 2));

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

		User user = getModel();
		createDecoratedTextField(Messages.ResumeFormEditor_FirstName_label,
				toolkit, sbody, (user != null ? user.getFirstName() : null),
				null);
		createDecoratedTextField(Messages.ResumeFormEditor_LastName_label,
				toolkit, sbody, (user != null ? user.getLastName() : null),
				null);

		// toolkit.createLabel(sbody, "Birthday:");
		// DateTime birthday = new DateTime(sbody, SWT.CALENDAR);
		// GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.widthHint = 150;
		// birthday.setLayoutData(gd);
		// birthday
		createDecoratedTextField(Messages.ResumeFormEditor_Birthday_label,
				toolkit, sbody, null, null);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, sbody);
	}

	private void createDecoratedTextField(String label, FormToolkit toolkit,
			Composite parent, String defaultValue, final IMessageManager mmng) {
		toolkit.createLabel(parent, label);
		final Text text = toolkit.createText(parent, "", SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 150;
		text.setLayoutData(gd);

		if (defaultValue != null) {
			text.setText(defaultValue);
		}
	}
}
