package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.eclipse.forms.ModelMasterDetailsBlock;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingMasterDetailsFormPage;

public class ExperiencesPage extends ReportingMasterDetailsFormPage<Resume> {

	public static final String ID = "experiences";

	public ExperiencesPage(ReportingFormEditor<Resume> editor) {
		super(editor, ID, Messages.ResumeFormEditor_ExperiencesPage_title);
	}

	@Override
	protected Image getFormTitleImage() {
		return ImageResources.getImage(ImageResources.IMG_EXPERIENCES_16);
	}

	@Override
	protected ModelMasterDetailsBlock<Resume> createMasterDetailsBlock() {
		return new ExperiencesMasterDetailsBlock(this);
	}

}
