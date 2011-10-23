package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.eclipse.forms.ModelMasterDetailsBlock;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ImageResources;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingMasterDetailsFormPage;

public class ExperiencesPage extends ReportingMasterDetailsFormPage<Resume> {

	public static final String ID = "experiences";

	public ExperiencesPage(ReportingFormEditor<Resume> editor) {
		super(editor, ID, Messages.ResumeFormEditor_ExperiencesPage_title);
	}

	@Override
	protected Image getFormTitleImage() {
		return ImageResources.getImage(ImageResources.IMG_EXPERIENCES);
	}

	@Override
	protected void fillBody(IManagedForm managedForm, FormToolkit toolkit) {
		block.createContent(managedForm);
	}
	
	@Override
	protected ModelMasterDetailsBlock<Resume> createMasterDetailsBlock() {
		return new ExperiencesMasterDetailsBlock(this);
	}

}
