package fr.opensagres.xdocreport.eclipse.ui.editors;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;

public abstract class ModelAndEntryEditorInput<Model> extends AbstractEditorInput {

	private final IReportModuleEntry entry;
	private final Model model;

	public ModelAndEntryEditorInput(IReportModuleEntry entry, Model model) {
		this.model = model;
		this.entry = entry;
	}

	public Model getModel() {
		return model;
	}

	public IReportModuleEntry getEntry() {
		return entry;
	}
}
