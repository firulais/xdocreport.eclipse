package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;

import fr.opensagres.eclipse.forms.ModelMasterDetailsBlock;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingMasterDetailsFormPage;

public class EducationsPage extends ReportingMasterDetailsFormPage<Resume> {

	public static final String ID = "educations";

	public EducationsPage(ReportingFormEditor<Resume> editor) {
		super(editor, ID, Messages.ResumeFormEditor_EducationsPage_title);
	}
	
	@Override
	protected Image getFormTitleImage() {
		return ImageResources.getImage(ImageResources.IMG_EDUCATION_24);
	}

	@Override
	protected ModelMasterDetailsBlock<Resume> createMasterDetailsBlock() {
		return new EducationsMasterDetailsBlock(this);
	}

}
