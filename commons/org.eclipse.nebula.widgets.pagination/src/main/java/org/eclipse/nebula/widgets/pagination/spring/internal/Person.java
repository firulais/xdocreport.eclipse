package org.eclipse.nebula.widgets.pagination.spring.internal;

public class Person {

	private String name;
	private Address address;

	public Person(String name, Address address) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Address getAddress() {
		return address;
	}

}
