package fr.opensagres.xdocreport.eclipse.internal.extensions.modules;

import org.eclipse.swt.graphics.Image;

public abstract class ReportBaseModule {

	private final String id;
	private String name;
	private String description;
	private Image image;

	public ReportBaseModule(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
