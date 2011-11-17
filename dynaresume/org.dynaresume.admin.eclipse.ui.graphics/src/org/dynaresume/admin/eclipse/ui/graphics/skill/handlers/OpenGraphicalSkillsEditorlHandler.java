package org.dynaresume.admin.eclipse.ui.graphics.skill.handlers;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.GraphicalSkillsEditor;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.SkillsEditorInput;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenEditorHandler;

public class OpenGraphicalSkillsEditorlHandler extends OpenEditorHandler{

	@Override
	protected String getEditorId() {
		return GraphicalSkillsEditor.ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry) {
		return new SkillsEditorInput(entry, null);
	}
}
