package org.dynaresume.domain.hr;

import java.io.ByteArrayInputStream;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.InputStream;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.dynaresume.domain.core.NaturalPerson;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Resume {

	public static final String PICTURE_PROPERTY = "picture";
	public static final String TITLE_PROPERTY = "title";

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String title;

	@Column
	private byte[] picture;

	// @Column(name = "owner_id", unique = true)
	// private Long ownerId;

	@OneToOne(cascade = CascadeType.PERSIST)
	private NaturalPerson owner;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "resume_fk")
	private Set<Experience> experiences;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "resume_fk")
	private Set<Education> educations;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "resume_fk")
	private Set<SkillResume> skills;
	@Transient
	private Set<Hobby> hobbies;
	@Transient
	private Set<SkillLanguage> languages;
	@Transient
	private Set<Reference> references;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	/**
	 * @deprecated use byte[] as much as possible
	 * @return
	 */
	public InputStream getPictureAsStream() {
		if (picture == null) {
			return null;
		}
		return new ByteArrayInputStream(picture);
	}

	public NaturalPerson getOwner() {
		return owner;
	}

	public void setOwner(NaturalPerson owner) {
		this.owner = owner;
	}

	public Set<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}

	public Set<Education> getEducations() {
		return educations;
	}

	public void setEducations(Set<Education> educations) {
		this.educations = educations;
	}

	public Set<SkillResume> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillResume> skills) {
		this.skills = skills;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public Set<Hobby> getHobbies() {
		return hobbies;
	}

	public void setLanguages(Set<SkillLanguage> languages) {
		this.languages = languages;
	}

	public Set<SkillLanguage> getLanguages() {
		return languages;
	}

	public void setReferences(Set<Reference> references) {
		this.references = references;
	}

	public Set<Reference> getReferences() {
		return references;
	}
}