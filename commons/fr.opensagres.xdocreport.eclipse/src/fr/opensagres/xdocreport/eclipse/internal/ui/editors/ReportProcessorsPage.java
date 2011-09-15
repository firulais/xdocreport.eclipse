package fr.opensagres.xdocreport.eclipse.internal.ui.editors;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import fr.opensagres.xdocreport.eclipse.internal.Messages;

public class ReportProcessorsPage extends FormPage {

	public static final String PAGE_ID = "processors";
	
	private final ScrolledPropertiesBlock block;
	
	public ReportProcessorsPage(FormEditor editor) {
		super(editor, PAGE_ID, Messages.ReportProcessorsPage_title);
		block = new ScrolledPropertiesBlock(this);
	}	
	
	protected void createFormContent(final IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		//FormToolkit toolkit = managedForm.getToolkit();
		//form.setText(Messages.getString("MasterDetailsPage.title")); //$NON-NLS-1$
		//form.setBackgroundImage(FormArticlePlugin.getDefault().getImage(
		//		FormArticlePlugin.IMG_FORM_BG));
		block.createContent(managedForm);
	}

}
