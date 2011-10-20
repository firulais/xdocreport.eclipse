package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormPage;

public class SkillsPage extends ReportingFormPage<Resume> {

	public static final String ID = "skills";

	public SkillsPage(ReportingFormEditor<Resume> editor) {
		super(editor, ID, Messages.ResumeFormEditor_SkillsPage_title);
	}

	@Override
	protected void fillBody(IManagedForm managedForm, FormToolkit toolkit) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onBind(DataBindingContext dataBindingContext) {

	}

}
