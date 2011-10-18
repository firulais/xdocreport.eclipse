package fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr;

import java.util.Set;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;

//@Entity
//@Table(name = "T_RESUME", schema = "hr")
public class Resume {

	/**
	         * 
	         */
	private static final long serialVersionUID = 7407392831377640438L;

	// @Id
	// @GeneratedValue
	private Long id;

	// @Column
	private String title;

	// @Column
	private byte[] picture;

	// @Column(name = "owner_id", unique = true)
	private Long ownerId;

	// @Transient
	private NaturalPerson owner;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn(name = "resume_fk")
	private Set<Experience> experiences;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn(name = "resume_fk")
	private Set<Diploma> diplomas;
	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn(name = "resume_fk")
	private Set<Competence> competences;

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
		// firePropertyChange("picture", oldValue, picture);
	}

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

	public Set<Diploma> getDiplomas() {
		return diplomas;
	}

	public void setDiplomas(Set<Diploma> diplomas) {
		// Object oldValue = this.diplomas;
		this.diplomas = diplomas;
		// firePropertyChange("diplomas", oldValue, diplomas);
	}

	public Set<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(Set<Competence> competences) {
		// Object oldValue = this.competences;
		this.competences = competences;
		// firePropertyChange("competences", oldValue, competences);
	}

}
