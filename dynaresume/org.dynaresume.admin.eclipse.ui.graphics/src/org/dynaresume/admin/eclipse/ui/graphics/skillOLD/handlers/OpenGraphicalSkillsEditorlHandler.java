package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.handlers;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.GraphicalAllSkillsEditor;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.SkillsEditorInput;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenEditorHandler;

public class OpenGraphicalSkillsEditorlHandler extends OpenEditorHandler {

	@Override
	protected String getEditorId() {
		return GraphicalAllSkillsEditor.ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry) {
		return new SkillsEditorInput(entry, null);
	}
}
