package fr.opensagres.xdocreport.eclipse.internal.ui.editors;

import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

import fr.opensagres.xdocreport.eclipse.internal.Messages;

public class ReportProcessorsPage extends FormPage {

	public static final String PAGE_ID = "processors";
	
	public ReportProcessorsPage(FormEditor editor) {
		super(editor, PAGE_ID, Messages.ReportProcessorsPage_title);
	}

}
