package fr.opensagres.xdocreport.eclipse.demo.resume.domain.core;

public class LegalEntity {
	/**
     * 
     */
	private static final long serialVersionUID = -8975049737136593445L;
	// @Id
	// @GeneratedValue
	private Long id;

	// @Column
	private String name;

	// @Column
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		// Object oldValue = this.id;
		this.id = id;
		// firePropertyChange("id", oldValue, id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// Object oldValue = this.name;
		this.name = name;
		// firePropertyChange("name", oldValue, name);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		// Object oldValue = this.code;
		this.code = code;
		// firePropertyChange("code", oldValue, code);
	}

}
