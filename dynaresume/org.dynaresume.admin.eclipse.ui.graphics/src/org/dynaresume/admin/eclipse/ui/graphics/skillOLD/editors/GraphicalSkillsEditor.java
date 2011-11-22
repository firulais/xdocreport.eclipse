package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors;

import java.util.Collection;

import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.SkillsDiagram;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.parts.SkillPartFactory;
import org.dynaresume.domain.hr.Skill;
import org.dynaresume.services.SkillService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

public class GraphicalSkillsEditor extends GraphicalEditorWithFlyoutPalette {

	public static final String ID = "org.dynaresume.admin.eclipse.ui.graphics.skill.editors.GraphicalSkillsEditor";

	private static PaletteRoot PALETTE_MODEL;

	private SkillService skillService;

	private SkillsDiagram skillsContainer;

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	public GraphicalSkillsEditor() {
		super.setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		if (PALETTE_MODEL == null)
			PALETTE_MODEL = SkillsEditorPaletteFactory.createPalette();
		return PALETTE_MODEL;
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

	protected SkillsDiagram getSkillsContainer() {
		return skillsContainer;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		Iterable<Skill> allSkills = skillService.findAll();
		skillsContainer = new SkillsDiagram(allSkills);
	}

}
