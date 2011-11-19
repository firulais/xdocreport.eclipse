package org.dynaresume.domain.hr;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
import javax.persistence.Transient;

import org.dynaresume.domain.core.NaturalPerson;

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

	@Column(name = "owner_id", unique = true)
	private Long ownerId;

	@Transient
	private NaturalPerson owner;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "resume_fk")
	private Set<Experience> experiences;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "resume_fk")
	private Set<Education> educations;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "resume_fk")
	private Set<Skill> skills;
	@Transient
	private Set<Hobby> hobbies;

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
		// Object oldValue = this.title;
		this.title = title;
		// firePropertyChange("title", oldValue, title);
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		// Object oldValue = this.picture;
		this.picture = picture;
		// this.photo.setImageByteArray(picture);
		// try {
		// this.photo.getImageInfo();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// firePropertyChange("picture", oldValue, picture);
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

	/**
	 * @deprecated use byte[] as much as possible
	 * @param picture
	 * @throws IOException
	 */
	// public void setPictureAsStream(InputStream picture) throws IOException {
	// this.setPicture(IOUtils.toByteArray(picture));
	// }

	public NaturalPerson getOwner() {
		return owner;
	}

	public void setOwner(NaturalPerson owner) {
		// Object oldValue = this.owner;
		this.owner = owner;
		// firePropertyChange("owner", oldValue, owner);
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		// Object oldValue = this.ownerId;
		this.ownerId = ownerId;
		// firePropertyChange("ownerId", oldValue, ownerId);
	}

	public Set<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<Experience> experiences) {
		// Object oldValue = this.experiences;
		this.experiences = experiences;
		// firePropertyChange("experiences", oldValue, experiences);
	}

	public Set<Education> getEducations() {
		return educations;
	}

	public void setEducations(Set<Education> educations) {
		// Object oldValue = this.diplomas;
		this.educations = educations;
		// firePropertyChange("diplomas", oldValue, diplomas);
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		// Object oldValue = this.competences;
		this.skills = skills;
		// firePropertyChange("competences", oldValue, competences);
	}

	// public ByteArrayImageProvider getPhoto() {
	// return photo;
	// }

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public Set<Hobby> getHobbies() {
		return hobbies;
	}

}