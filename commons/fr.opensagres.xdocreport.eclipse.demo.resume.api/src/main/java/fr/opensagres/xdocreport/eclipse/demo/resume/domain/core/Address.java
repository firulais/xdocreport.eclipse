package fr.opensagres.xdocreport.eclipse.demo.resume.domain.core;

public class Address {
	 /**
     * 
     */
    private static final long serialVersionUID = 9217187221005720810L;
    //@Id
     //@GeneratedValue 
    private Long id;
    //@Column
    private String zipCode;
    //@Column
    private String city;
    //@Column
    private String fax;
    //@Column
    private String telephone;
    
    //@ManyToOne
    private Country country;

    public Long getId() {
            return id;
    }

    public void setId(Long id) {
    	//Object oldValue = this.id;
            this.id = id;
            //firePropertyChange("id", oldValue, id);
    }

    public String getZipCode() {
            return zipCode;
    }

    public void setZipCode(String zipCode) {
    	//Object oldValue = this.zipCode;
            this.zipCode = zipCode;
            //firePropertyChange("zipCode", oldValue, zipCode);
    }

    public String getCity() {
            return city;
    }

    public void setCity(String city) {
    	//Object oldValue = this.city;
            this.city = city;
            //firePropertyChange("city", oldValue, city);
    }

    public String getFax() {
            return fax;
    }

    public void setFax(String fax) {
    	//Object oldValue = this.fax;
            this.fax = fax;
            //firePropertyChange("fax", oldValue, fax);
    }

    public String getTelephone() {
            return telephone;
    }

    public void setTelephone(String telephone) {
    	//Object oldValue = this.telephone;
            this.telephone = telephone;
            //firePropertyChange("telephone", oldValue, telephone);
    }

    public Country getCountry() {
            return country;
    }

    public void setCountry(Country country) {
    	//Object oldValue = this.country;
            this.country = country;
            //firePropertyChange("country", oldValue, country);
    }

}
