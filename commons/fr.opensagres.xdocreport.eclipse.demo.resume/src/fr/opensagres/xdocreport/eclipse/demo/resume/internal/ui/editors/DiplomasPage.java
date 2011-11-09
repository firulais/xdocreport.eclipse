package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import org.eclipse.swt.graphics.Image;

import fr.opensagres.eclipse.forms.ModelMasterDetailsBlock;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ImageResources;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingMasterDetailsFormPage;

public class DiplomasPage extends ReportingMasterDetailsFormPage<Resume> {

	public static final String ID = "diploma";

	public DiplomasPage(ReportingFormEditor<Resume> editor) {
		super(editor, ID, Messages.ResumeFormEditor_DiplomasPage_title);
	}
	
	@Override
	protected Image getFormTitleImage() {
		return ImageResources.getImage(ImageResources.IMG_DIPLOMA_24);
	}

	@Override
	protected ModelMasterDetailsBlock<Resume> createMasterDetailsBlock() {
		return new DiplomasMasterDetailsBlock(this);
	}

}
