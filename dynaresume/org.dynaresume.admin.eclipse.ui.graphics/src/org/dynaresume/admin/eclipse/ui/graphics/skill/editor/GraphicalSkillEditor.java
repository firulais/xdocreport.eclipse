package org.dynaresume.admin.eclipse.ui.graphics.skill.editor;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editor.model.SkillTreeDiagram;
import org.dynaresume.domain.hr.Skill;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.eclipse.forms.editor.IModelProvider;
import fr.opensagres.eclipse.gef.tree.editor.GraphicalTreeEditor;
import fr.opensagres.eclipse.gef.tree.editor.model.TreeDiagram;

public class GraphicalSkillEditor extends GraphicalTreeEditor {

	public static final String ID = "org.dynaresume.admin.eclipse.ui.graphics.skill.editors.GraphicalSkillsEditor";

	@Override
	protected TreeDiagram createTreeDiagram(IEditorInput input) {
		Iterable<Skill> allSkills = getSkills(input);
		return new SkillTreeDiagram(allSkills);
	}

	protected Iterable<Skill> getSkills(IEditorInput input) {
		if (input instanceof IModelProvider<?>) {
			List<Skill> skills = new ArrayList<Skill>();
			skills.add(((IModelProvider<Skill>) input).getModel());
			return skills;
		}
		return null;
	}

	//
	private static PaletteRoot PALETTE_MODEL;

	@Override
	protected PaletteRoot getPaletteRoot() {
		if (PALETTE_MODEL == null)
			PALETTE_MODEL = SkillTreeEditorPaletteFactory.createPalette();
		return PALETTE_MODEL;
	}

}
