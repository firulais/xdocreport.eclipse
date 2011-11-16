package org.dynaresume.domain.hr;

public class Skill {

	/**
     * 
     */
	private static final long serialVersionUID = 7386196051014581733L;

	// @Id
	// @GeneratedValue
	private Long id;

	// @Column
	private String label;

	private Skill parent;
	
	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public void setId(Long id) {

		this.id = id;

	}

	public void setLabel(String label) {
		// Object oldValue = this.label;
		this.label = label;
		// firePropertyChange("label", oldValue, label);
	}

	public Skill getParent() {
		return parent;
	}
	
	public void setParent(Skill parent) {
		this.parent = parent;
	}
}
