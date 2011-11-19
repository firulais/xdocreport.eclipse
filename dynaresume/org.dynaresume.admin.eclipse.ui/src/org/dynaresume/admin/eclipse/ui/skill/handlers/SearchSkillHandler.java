package org.dynaresume.admin.eclipse.ui.skill.handlers;

import org.dynaresume.admin.eclipse.ui.skill.dialogs.SearchSkillDialog;
import org.dynaresume.admin.eclipse.ui.skill.editors.SkillEditorInput;
import org.dynaresume.admin.eclipse.ui.skill.editors.SkillFormEditor;
import org.dynaresume.domain.hr.Skill;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.dialogs.SelectionDialog;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenDialogHandler;

public class SearchSkillHandler extends OpenDialogHandler {

	@Override
	protected SelectionDialog createDialog(Shell parentShell) throws Exception {
		return PlatformXDocReport.getDialogFactory().createDialog(parentShell,
				SearchSkillDialog.ID, SearchSkillDialog.class);
	}

	@Override
	protected String getEditorId() {
		return SkillFormEditor.ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model) {
		return new SkillEditorInput(entry, (Skill) model);
	}
}
