package org.eclipse.nebula.widgets.modelpicker;

import org.eclipse.swt.graphics.Image;

public class SearcherDescriptor implements ISearcher {

	private final ISearcher initializer;
	private final String keyStroke;
	private final String description;
	private final Image icon;

	public SearcherDescriptor(ISearcher initializer, String keyStroke,
			String description, Image icon) {
		this.initializer = initializer;
		this.keyStroke = keyStroke;
		this.description = description;
		this.icon = icon;
	}

	public void init(ModelHolder modelPicker, String keyStroke) {
		initializer.init(modelPicker, keyStroke);
	}

	public ISearcher getInitializer() {
		return initializer;
	}

	public String getKeyStroke() {
		return keyStroke;
	}

	public String getDescription() {
		return description;
	}

	public Image getIcon() {
		return icon;
	}

	public void dispose() {
		initializer.dispose();

	}

	public void handle() {
		initializer.handle();

	}

}
