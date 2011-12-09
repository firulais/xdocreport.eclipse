package org.eclipse.nebula.widgets.pagination.example.model;

public class Person {

	private String name;
	private Address address;

	public Person(String name, String addressName) {
		this.name = name;
		this.address = new Address(addressName);
	}

	public String getName() {
		return name;
	}
	
	public Address getAddress() {
		return address;
	}

}
