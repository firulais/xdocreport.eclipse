package org.dynaresume.admin.eclipse.ui.graphics.group.editor;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.admin.eclipse.ui.graphics.group.editor.model.GroupTreeDiagram;
import org.dynaresume.admin.eclipse.ui.group.editors.GroupEditorInput;
import org.dynaresume.domain.core.Group;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.eclipse.gef.tree.editor.GraphicalTreeEditor;
import fr.opensagres.eclipse.gef.tree.editor.model.TreeDiagram;

public class GraphicalGroupEditor extends GraphicalTreeEditor {
	
	public static final String ID = "org.dynaresume.admin.eclipse.ui.graphics.group.editors.GraphicalGroupsEditor";

	@Override
	protected TreeDiagram createTreeDiagram(IEditorInput input) {
		Iterable<Group> allGroups = getGroups(input);
		return new GroupTreeDiagram(allGroups);
	}

	protected Iterable<Group> getGroups(IEditorInput input) {
		if (input instanceof GroupEditorInput) {
			List<Group> groups = new ArrayList<Group>();
			groups.add(((GroupEditorInput) input).getModel());
			return groups;
		}
		return null;
	}

	//
	private static PaletteRoot PALETTE_MODEL;

	@Override
	protected PaletteRoot getPaletteRoot() {
		if (PALETTE_MODEL == null)
			PALETTE_MODEL = GroupTreeEditorPaletteFactory.createPalette();
		return PALETTE_MODEL;
	}

}
