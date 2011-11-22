package org.dynaresume.admin.eclipse.ui.group.editors;

import org.dynaresume.admin.eclipse.ui.internal.ImageResources;
import org.dynaresume.admin.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormPage;

public class OverviewPage extends ReportingFormPage {

	public static final String ID = "overview";
	
	public OverviewPage(ReportingFormEditor editor) {
		super(editor, ID, Messages.GroupFormEditor_OverviewPage_title);
	}

	@Override
	protected Image getFormTitleImage() {
		return ImageResources.getImage(ImageResources.IMG_OVERVIEW_16);
	}

	public void onBind(DataBindingContext dataBindingContext) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fillBody(IManagedForm managedForm, FormToolkit toolkit) {
		// TODO Auto-generated method stub
		
	}

}
