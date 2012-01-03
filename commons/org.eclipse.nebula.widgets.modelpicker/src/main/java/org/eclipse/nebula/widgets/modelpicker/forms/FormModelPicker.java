package org.eclipse.nebula.widgets.modelpicker.forms;

import org.eclipse.nebula.widgets.modelpicker.ModelPicker;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class FormModelPicker<T> extends ModelPicker<T> {

	private final FormToolkit toolkit;

	public FormModelPicker(Composite parent, int style, int textStyle,
			FormToolkit toolkit) {
		super(parent, style, textStyle, false);
		toolkit.adapt(this);
		this.toolkit = toolkit;
		super.createUI(this);
	}

	@Override
	protected Text createText(Composite parent, int textStyle) {
		return getToolkit().createText(parent, "", textStyle);
	}

	public FormToolkit getToolkit() {
		return toolkit;
	}

}
