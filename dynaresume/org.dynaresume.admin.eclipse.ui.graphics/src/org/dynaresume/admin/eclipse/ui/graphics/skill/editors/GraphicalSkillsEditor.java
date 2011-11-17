package org.dynaresume.admin.eclipse.ui.graphics.skill.editors;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.GraphicalSkillsContainer;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts.SkillPartFactory;
import org.dynaresume.services.SkillService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;

public class GraphicalSkillsEditor extends GraphicalEditorWithFlyoutPalette {

	public static final String ID = "org.dynaresume.admin.eclipse.ui.graphics.skill.editors.GraphicalSkillsEditor";

	private SkillService skillService;

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	public GraphicalSkillsEditor() {
		super.setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(SkillPartFactory.INSTANCE);

	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();

		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(getSkillsContainer()); // set the contents of this
		// editor
	}

	protected GraphicalSkillsContainer getSkillsContainer() {
		GraphicalSkillsContainer container = new GraphicalSkillsContainer();
		return container;
	}

}
