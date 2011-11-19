package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.parts;

import java.beans.PropertyChangeEvent;

import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.figures.TreeBranchFigure;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.figures.TreeNodeFigure;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.ModelElement;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.TreeNode;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;

public class TreeNodeEditPart extends AbstractBaseModelGraphicalEditPart {

	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
    protected IFigure createFigure() {
        TreeNode model = (TreeNode) getModel();
        IFigure figure = null;
        // nodes in xml tree
//        if (XmlMapUtil.getXPathLength(model.getXpath()) > 2) {
//            String status = model.isLoop() ? String.valueOf(model.isLoop()) : "";
//            figure = new XmlTreeBranch(new TreeBranchFigure(model), XmlTreeBranch.STYLE_ROW_HANGING);
//            // figure = new TreeBranch(new TreeBranchFigure(getTreeBranchName(model), "true"),
//            // TreeBranch.STYLE_HANGING);
//            treeBranchFigure = (XmlTreeBranch) figure;
//
//            // for test
//            // if (model.getChildren().size() == 1) {
//            // figure.setOpaque(true);
//            // figure.setBackgroundColor(ColorConstants.green);
//            // }
//
//        }
//        // normal column and tree root
//        else {
            figure = new TreeNodeFigure(this);
            // figure.setLayoutManager(new EqualWidthLayout());
            figure.setLayoutManager(new ToolbarLayout());
        //}
        return figure;
    }


	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TreeNode getModel() {
		// TODO Auto-generated method stub
		return (TreeNode)super.getModel();
	}
}
