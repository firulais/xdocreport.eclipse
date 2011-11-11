package org.dynaresume.domain.core;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
	
	public static final String EMAIL_PROPERTY = "email";
	public static final String ADDRESS_PROPERTY = "address";
	
	/**
     * 
     */
    private static final long serialVersionUID = 298482050929739147L;
    //@Column(length=100,nullable=false)
    @Size(max=100)
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String email;

    //@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    private Address address;

    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
    	//Object oldValue = this.email;
            this.email = email;
            //firePropertyChange("email", oldValue, email);
    }

    public Address getAddress() {
            return address;
    }

    public void setAddress(Address address) {
    	//Object oldValue = this.address;
            this.address = address;
            //firePropertyChange("address", oldValue, address);
    }


}