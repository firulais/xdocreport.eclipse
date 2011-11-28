package org.dynaresume.domain.hr;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SkillLanguage {

	public static final String LANGUAGE_PROPERTY = "language";
	@Id
	@GeneratedValue
	long id;

	@ManyToOne
	private Language language;

	public Long getId() {
		return id;
	}

	public void setId(long id) {

		this.id = id;

	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
