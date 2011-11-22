package org.dynaresume.admin.eclipse.ui.graphics.skill.editors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.List;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.actions.TreeNodeContextMenuProvider;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.TreeDiagram;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts.TreeNodeEditPartFactory;
import org.dynaresume.admin.eclipse.ui.skill.editors.SkillEditorInput;
import org.dynaresume.domain.hr.Skill;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;

public class GraphicalSkillEditor extends GraphicalEditorWithFlyoutPalette {

	public static final String ID = "org.dynaresume.admin.eclipse.ui.graphics.skill.editors.GraphicalSkillsEditor";

	private static PaletteRoot PALETTE_MODEL;

	private TreeDiagram diagram;
	private KeyHandler sharedKeyHandler;

	public GraphicalSkillEditor() {
		super.setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	public void commandStackChanged(EventObject event) {
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}

	@Override
	protected void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		IAction action;

		action = new DirectEditAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		if (PALETTE_MODEL == null)
			PALETTE_MODEL = SkillsEditorPaletteFactory.createPalette();
		return PALETTE_MODEL;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		MessageDialog.openInformation(getSite().getShell(), "TODO",
				"TODO Implement Editor#doSave");
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		getGraphicalViewer().setRootEditPart(new ScalableRootEditPart());
		getGraphicalViewer().setEditPartFactory(
				TreeNodeEditPartFactory.INSTANCE);
		getGraphicalViewer().setKeyHandler(
				new GraphicalViewerKeyHandler(getGraphicalViewer()) {
					@Override
					public boolean keyPressed(KeyEvent event) {

						EditPart editPart = (EditPart) ((IStructuredSelection) getGraphicalViewer()
								.getSelection()).getFirstElement();
						// Open the CPS if enter is pressed
						if (event.keyCode == SWT.KEYPAD_CR
								|| event.character == '\r') {
							editPart.performRequest(new Request(
									RequestConstants.REQ_DIRECT_EDIT));
						}

						return super.keyPressed(event);
					}

				}.setParent(getCommonKeyHandler()));

		ContextMenuProvider provider = new TreeNodeContextMenuProvider(
				getGraphicalViewer(), getActionRegistry());
		getGraphicalViewer().setContextMenu(provider);
		getSite().registerContextMenu(
				"org.dynaresume.admin.eclipse.ui.graphics.contextmenu", //$NON-NLS-1$
				provider, getGraphicalViewer());

	}

	protected KeyHandler getCommonKeyHandler() {
		if (sharedKeyHandler == null) {
			sharedKeyHandler = new KeyHandler();
			sharedKeyHandler
					.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
							getActionRegistry().getAction(
									ActionFactory.DELETE.getId()));
			sharedKeyHandler.put(
					KeyStroke.getPressed(SWT.F2, 0),
					getActionRegistry().getAction(
							GEFActionConstants.DIRECT_EDIT));
		}
		return sharedKeyHandler;
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();

		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(getDiagram()); // set the contents of this
		// editor
	}

	protected TreeDiagram getDiagram() {
		return diagram;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		Iterable<Skill> allSkills = getSkills(input);
		diagram = new TreeDiagram(allSkills);
	}

	protected Iterable<Skill> getSkills(IEditorInput input) {
		if (input instanceof SkillEditorInput) {
			List<Skill> skills = new ArrayList<Skill>();
			skills.add(((SkillEditorInput)input).getModel());
			return skills;
		}
		return null;
	}

}
