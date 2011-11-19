package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.figures;

import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.TreeNode;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.parts.TreeNodeEditPart;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

// http://www.talendforge.org/svn/tos/tags/release-4_2_0M3/org.talend.designer.xmlmap/src/main/java/org/talend/designer/xmlmap/figures/ToolBarContainer.java
public class TreeNodeFigure extends Figure {

    //private XmlTreeBranch treeBranch;

    private TreeNodeEditPart treeNodePart;

    private TreeNode treeNode;

    protected Figure treeNodeExpressionFigure;

    protected Label columnExpressionFigure;

    private Label nameLabel;

    public TreeNodeFigure() {

    }

    public TreeNodeFigure(TreeNodeEditPart treeNodePart) {
        this.treeNodePart = treeNodePart;
        this.treeNode = (TreeNode) treeNodePart.getModel();
        createContent();
    }

    private void createContent() {
        // column
//        if (!XmlMapUtil.DOCUMENT.equals(treeNode.getType())) {
//            nameLabel = new Label();
//            nameLabel.setText(treeNode.getName());
//            nameLabel.setBorder(new RowBorder());
//            this.add(nameLabel);
//        }
//        // xml root
//        else if (XmlMapUtil.DOCUMENT.equals(treeNode.getType()) && treeNode.eContainer() instanceof InputXmlTree) {
//            String status = treeNode.isLoop() ? String.valueOf(treeNode.isLoop()) : "";
//            treeBranch = new XmlTreeRoot(new TreeBranchFigure(treeNode), XmlTreeBranch.STYLE_ROW_HANGING);
//            treeBranch.setBorder(new RowBorder());
//            this.add(treeBranch);
//        }
    }

    public IFigure getContentPane() {
        //if (!XmlMapUtil.DOCUMENT.equals(treeNode.getType())) {
            return this;
//        } else {
//            return treeBranch.getContentsPane();
//        }
    }

//    public XmlTreeBranch getTreeBranch() {
//        return treeBranch;
//    }

    public Figure getTreeNodeExpressionFigure() {
        return this.treeNodeExpressionFigure;
    }

    public Label getColumnExpressionFigure() {
        return columnExpressionFigure;
    }

    public void updateNameFigure() {
//        if (!XmlMapUtil.DOCUMENT.equals(treeNode.getType())) {
//            nameLabel.setText(treeNode.getName());
//        } else if (XmlMapUtil.DOCUMENT.equals(treeNode.getType()) && treeNode.eContainer() instanceof InputXmlTree) {
//            ((TreeBranchFigure) treeBranch.getElement()).updataNameFigure();
//        }
    }

    /**
     * used to change figure if talend type switched form Document to others
     */
    public void refreshChildren() {
        this.getChildren().clear();
        createContent();
    }


}
