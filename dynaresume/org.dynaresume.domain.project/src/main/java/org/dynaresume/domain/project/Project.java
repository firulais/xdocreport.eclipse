package org.dynaresume.domain.project;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Project implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 9217187221005720810L;

	public static final String NAME_PROPERTY = "name";
	// public static final String DESCRIPTION_PROPERTY = "description";
	public static final String URL_PROPERTY = "URL";
	public static final String CLIENT_PROPERTY = "client";

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "project_fk")
	private Set<ProjectDescription> descriptions;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Client client;

	@Column
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProjectDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Set<ProjectDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}