package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormPage;

public class SkillsPage extends ReportingFormPage<Resume> {

	public static final String ID = "skills";

	public SkillsPage(ReportingFormEditor<Resume> editor) {
		super(editor, ID, Messages.ResumeFormEditor_SkillsPage_title);
	}
	
	@Override
	protected Image getFormTitleImage() {
		return ImageResources.getImage(ImageResources.IMG_SKILLS_16);
	}

	@Override
	protected void fillBody(IManagedForm managedForm, FormToolkit toolkit) {
		// TODO Auto-generated method stub

	}

	public void onBind(DataBindingContext dataBindingContext) {

	}

}
