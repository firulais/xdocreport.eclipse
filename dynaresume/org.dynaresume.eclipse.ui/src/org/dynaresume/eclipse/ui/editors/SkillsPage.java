package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;

import fr.opensagres.eclipse.forms.ModelMasterDetailsBlock;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingMasterDetailsFormPage;

public class SkillsPage extends ReportingMasterDetailsFormPage<Resume> {

	public static final String ID = "skills";

	public SkillsPage(ReportingFormEditor<Resume> editor) {
		super(editor, ID, Messages.ResumeFormEditor_SkillsPage_title);
	}

	@Override
	protected Image getFormTitleImage() {
		return ImageResources.getImage(ImageResources.IMG_SKILLS_16);
	}

	@Override
	protected ModelMasterDetailsBlock<Resume> createMasterDetailsBlock() {
		return new SkillsMasterDetailsBlock(this);
	}

}
