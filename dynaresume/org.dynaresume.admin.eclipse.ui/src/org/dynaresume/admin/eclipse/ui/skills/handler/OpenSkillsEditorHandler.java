package org.dynaresume.admin.eclipse.ui.skills.handler;

import org.dynaresume.admin.eclipse.ui.skills.editors.SkillsEditorInput;
import org.dynaresume.admin.eclipse.ui.skills.editors.SkillsFormEditor;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenEditorHandler;

public class OpenSkillsEditorHandler extends OpenEditorHandler {

	@Override
	protected String getEditorId() {
		return SkillsFormEditor.ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry) {
		return new SkillsEditorInput(entry);
	}

}
