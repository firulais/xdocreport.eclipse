package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.policies;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.commands.DeleteConnectionCommand;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.commands.SplitConnectionCommand;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.Connection;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.SkillsDiagram;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts.ConnectionPart;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

public class MyConnectionEditPolicy extends ConnectionEditPolicy {

	/**
	 * @see org.eclipse.gef.editpolicies.ConnectionEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public Command getCommand(Request request) {
		if (REQ_CREATE.equals(request.getType()))
			return getSplitConnectionCommand(request);
		return super.getCommand(request);
	}

	private PolylineConnection getConnectionFigure() {
		return ((PolylineConnection) ((ConnectionPart) getHost()).getFigure());
	}

	/**
	 * @see ConnectionEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command getDeleteCommand(GroupRequest request) {
		DeleteConnectionCommand cmd = new DeleteConnectionCommand();
		Connection t = (Connection) getHost().getModel();
		cmd.setConnection(t);
		cmd.setSource(t.getSource());
		cmd.setTarget(t.getTarget());
		return cmd;
	}

	protected Command getSplitConnectionCommand(Request request) {
		SplitConnectionCommand cmd = new SplitConnectionCommand();
		cmd.setConnection(((Connection) getHost().getModel()));
		cmd.setParent(((SkillsDiagram) ((ConnectionPart) getHost()).getSource()
				.getParent().getModel()));
		cmd.setNewConnectableNode(((ConnectableNode) ((CreateRequest) request)
				.getNewObject()));
		return cmd;
	}

	/**
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	public EditPart getTargetEditPart(Request request) {
		if (REQ_CREATE.equals(request.getType()))
			return getHost();
		return null;
	}

	/**
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
	 */
	public void eraseTargetFeedback(Request request) {
		if (REQ_CREATE.equals(request.getType()))
			getConnectionFigure().setLineWidth(1);
	}

	/**
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#showTargetFeedback(org.eclipse.gef.Request)
	 */
	public void showTargetFeedback(Request request) {
		if (REQ_CREATE.equals(request.getType()))
			getConnectionFigure().setLineWidth(2);
	}

}
