package org.dynaresume.domain.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectDescription implements Serializable {

	private static final long serialVersionUID = -3127339017807923549L;

	public static final String DESCRIPTION_PROPERTY = "description";

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private ProjectDescriptionType type;

	@Column
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProjectDescriptionType getType() {
		return type;
	}

	public void setType(ProjectDescriptionType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
