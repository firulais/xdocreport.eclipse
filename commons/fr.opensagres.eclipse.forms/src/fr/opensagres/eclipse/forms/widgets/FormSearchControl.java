package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class FormSearchControl extends SearchControl {

	private final FormToolkit toolkit;

	public FormSearchControl(Composite parent, int style, int textStyle,
			FormToolkit toolkit) {
		super(parent, style, textStyle, false);
		toolkit.adapt(this);
		// toolkit.paintBordersFor(this);
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
