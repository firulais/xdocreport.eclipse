package org.dynaresume.project.eclipse.ui.editors.project;

import org.dynaresume.domain.project.Project;
import org.dynaresume.project.eclipse.ui.internal.ImageResources;
import org.dynaresume.project.eclipse.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Text;

import fr.opensagres.eclipse.forms.ModelMasterDetailsBlock;
import fr.opensagres.eclipse.forms.widgets.SimpleWikiText;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingMasterDetailsFormPage;

public class DescriptionsPage extends ReportingMasterDetailsFormPage<Project> {

	public static final String ID = "descriptions";

	private Text nameText;
	private SimpleWikiText descriptionText;
	private Text urlText;

	public DescriptionsPage(ReportingFormEditor editor) {
		super(editor, ID, Messages.ProjectFormEditor_DescriptionsPage_title);
	}

	@Override
	protected Image getFormTitleImage() {
		return ImageResources.getImage(ImageResources.IMG_CLIENT_16);
	}

	@Override
	protected ModelMasterDetailsBlock<Project> createMasterDetailsBlock() {
		return new DescriptionsMasterDetailsBlock(this);
	}

}
