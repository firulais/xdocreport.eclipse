package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.PageNode;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.TreeBranch;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.TreeNode;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.policies.TreeNodeContainerEditPolicy;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.policies.TreeNodeDirectEditPolicy;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.policies.TreeNodeEditPolicy;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.policies.TreeNodeLayoutEditPolicy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

public class TreeNodeEditPart extends PropertyChangeGraphicalEditPart {

	protected DirectEditManager manager;

	@Override
	protected IFigure createFigure() {
		TreeBranch treeBranch = new TreeBranch(new PageNode("XXX"));
		treeBranch.setStyle(TreeBranch.STYLE_NORMAL);
		return treeBranch;
	}

	@Override
	public TreeBranch getFigure() {
		return (TreeBranch) super.getFigure();
	}

	@Override
	protected List<TreeNode> getModelChildren() {
		return getModel().getChildren();
	}

	@Override
	public TreeNode getModel() {
		return (TreeNode) super.getModel();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (TreeNode.LABEL_PROP.equals(prop)) {
			refreshVisuals();
			// Causes Graph to re-layout
			((GraphicalEditPart) (getViewer().getContents())).getFigure()
					.revalidate();
		}
		// these properties are fired when Shapes are added into or removed from
		// the ShapeDiagram instance and must cause a call of refreshChildren()
		// to update the diagram's contents.
		if (TreeNode.CHILD_ADDED_PROP.equals(prop)
				|| TreeNode.CHILD_REMOVED_PROP.equals(prop)) {
			refreshChildren();
		}
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();

		TreeNode treeNode = getModel();
		getFigure().setText(treeNode.getLabel());
	}

	@Override
	public IFigure getContentPane() {
		return ((TreeBranch) super.getContentPane()).getContentsPane();
	}

	@Override
	protected void createEditPolicies() {
		// installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
		// new SkillNodeEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new TreeNodeEditPolicy());
		// installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
		// new ActivityContainerHighlightEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new TreeNodeContainerEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new TreeNodeLayoutEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new TreeNodeDirectEditPolicy());
	}

	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
			performDirectEdit();
	}

	protected void performDirectEdit() {
		if (manager == null) {
			Label l = getFigure().getPageNode().getLabel();
			manager = new TreeNodeDirectEditManager(this, TextCellEditor.class,
					new TreeNodeCellEditorLocator(l), l);
		}
		manager.show();
	}
}
