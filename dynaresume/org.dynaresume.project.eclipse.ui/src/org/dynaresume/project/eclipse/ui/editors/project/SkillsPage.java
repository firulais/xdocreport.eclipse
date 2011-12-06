package org.dynaresume.project.eclipse.ui.editors.project;

import org.dynaresume.project.eclipse.ui.internal.ImageResources;
import org.dynaresume.project.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.eclipse.forms.widgets.SimpleWikiText;
import fr.opensagres.xdocreport.eclipse.ui.FormLayoutFactory;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormPage;

public class SkillsPage extends ReportingFormPage {

	public static final String ID = "skills";

	private Text nameText;
	private SimpleWikiText descriptionText;
	private Text urlText;

	public SkillsPage(ReportingFormEditor editor) {
		super(editor, ID, Messages.ProjectFormEditor_SkillsPage_title);
	}

	@Override
	protected Image getFormTitleImage() {
		return ImageResources.getImage(ImageResources.IMG_SKILLS_16);
	}

	@Override
	protected void fillBody(IManagedForm managedForm, FormToolkit toolkit) {
		Composite body = managedForm.getForm().getBody();
		body.setLayout(FormLayoutFactory.createFormTableWrapLayout(true, 2));

	}

	public void onBind(DataBindingContext dataBindingContext) {
		// TODO Auto-generated method stub
		
	}

}
