package fr.opensagres.eclipse.gef.tree.editor.model;

public class TreeDiagram extends TreeNode {

	@Override
	protected String getModelLabel() {
		return null;
	}

	@Override
	protected void setModelLabel(String label) {

	}

	@Override
	protected Object getModelParent() {
		return null;
	}

	@Override
	public String getTypeName() {
		return "Root";
	}

}
