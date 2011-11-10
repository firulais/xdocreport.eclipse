package fr.opensagres.xdocreport.eclipse.reporting.ui.internal.editors;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import fr.opensagres.xdocreport.eclipse.reporting.ui.internal.Messages;

public class ReportProcessorsPage extends FormPage {

	public static final String PAGE_ID = "processors";
	
	private final ReportLoaderMasterDetailsBlock block;
	
	public ReportProcessorsPage(FormEditor editor) {
		super(editor, PAGE_ID, Messages.ReportProcessorsPage_title);
		block = new ReportLoaderMasterDetailsBlock(this);
	}	
	
	protected void createFormContent(final IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText(Messages.ReportProcessorsPage_title); //$NON-NLS-1$
		//form.setBackgroundImage(FormArticlePlugin.getDefault().getImage(
		//		FormArticlePlugin.IMG_FORM_BG));
		block.createContent(managedForm);
	}

}
