package org.dynaresume.domain.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Competence {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String label;

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

}
