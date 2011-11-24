package fr.opensagres.xdocreport.eclipse.ui.editors;

import fr.opensagres.eclipse.forms.editor.ModelToolbarFormPage;

public abstract class ReportingFormPage<Model> extends
		ModelToolbarFormPage<Model> {

	public ReportingFormPage(ReportingFormEditor<Model> editor, String id,
			String title) {
		super(editor, id, title);
	}

	@Override
	public ReportingFormEditor<Model> getEditor() {
		return (ReportingFormEditor<Model>) super.getEditor();
	}

}
