package org.dynaresume.admin.eclipse.ui.graphics.skill.handlers;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editor.GraphicalAllSkillsEditor;
import org.dynaresume.admin.eclipse.ui.skill.editors.SkillEditorInput;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenEditorHandler;

public class OpenGraphicalAllSkillsEditorHandler extends OpenEditorHandler {

	@Override
	protected String getEditorId() {
		return GraphicalAllSkillsEditor.ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry) {
		return new SkillEditorInput(entry, null);
	}
}
