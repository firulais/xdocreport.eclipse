package fr.opensagres.xdocreport.eclipse.demo.resume.domain;

import fr.opensagres.xdocreport.document.images.ClassPathImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;

public class User {

	private static IImageProvider EMPTY_PHOTO = new ClassPathImageProvider(User.class, "EmptyPhoto.png");
	
	private long id;
	private String firstName;
	private String lastName;
	private IImageProvider photo;
	
	public User() {
		this.photo = EMPTY_PHOTO;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public IImageProvider getPhoto() {		
		return photo;
	}
	
	public void setPhoto(IImageProvider photo) {
		this.photo = photo;
	}
}
