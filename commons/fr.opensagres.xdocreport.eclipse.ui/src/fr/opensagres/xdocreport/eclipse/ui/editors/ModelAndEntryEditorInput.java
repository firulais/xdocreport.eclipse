package fr.opensagres.xdocreport.eclipse.ui.editors;

import fr.opensagres.eclipse.forms.editor.ModelEditorInput;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;

public abstract class ModelAndEntryEditorInput<Model> extends
		ModelEditorInput<Model> {

	private final IReportModuleEntry entry;

	public ModelAndEntryEditorInput(IReportModuleEntry entry, Model model) {
		super(model);
		this.entry = entry;
	}

	public IReportModuleEntry getEntry() {
		return entry;
	}
}
