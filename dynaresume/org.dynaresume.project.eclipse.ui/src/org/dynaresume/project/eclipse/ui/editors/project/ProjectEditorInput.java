package org.dynaresume.project.eclipse.ui.editors.project;

import org.dynaresume.domain.project.Project;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;

public class ProjectEditorInput extends ModelAndEntryEditorInput<Project> {

	public ProjectEditorInput(IReportModuleEntry entry, Project group) {
		super(entry, group);
	}

	public String getName() {
		return "Projects";
	}

	public String getToolTipText() {
		return getName();
	}

}
