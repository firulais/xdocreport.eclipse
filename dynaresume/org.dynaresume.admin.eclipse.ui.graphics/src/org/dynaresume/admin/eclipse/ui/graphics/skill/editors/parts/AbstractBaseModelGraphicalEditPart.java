package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.ModelElement;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public abstract class AbstractBaseModelGraphicalEditPart extends
		AbstractGraphicalEditPart /*implements INodeAdapter*/ {

	/**
	 * Upon activation, attach to the model element as a property change
	 * listener.
	 */
	public void activate() {
		if (!isActive()) {
			hookIntoModel(getModel());
			super.activate();
		}
	}

	/**
	 * Upon deactivation, detach from the model element as a property change
	 * listener.
	 */
	public void deactivate() {
		if (isActive()) {
			unhookFromModel(getModel());
			super.deactivate();
		}
	}

	protected void hookIntoModel(ModelElement model) {
		if (model != null) {
			//model.addAdapter(this);
		}
	}

	protected void unhookFromModel(ModelElement model) {
		if (model != null) {
			//model.removeAdapter(this);
		}
	}

	public boolean isAdapterForType(Object type) {
		return (getModel().getClass() == type);
	}

	
	@Override
	public ModelElement getModel() {	
		return (ModelElement)super.getModel();
	}
}
