package fr.opensagres.xdocreport.eclipse.demo.resume.domain.core;

public class Person {
	
	public static final String EMAIL_PROPERTY = "email";
	
	/**
     * 
     */
    private static final long serialVersionUID = 298482050929739147L;
    //@Column(length=100,nullable=false)
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
