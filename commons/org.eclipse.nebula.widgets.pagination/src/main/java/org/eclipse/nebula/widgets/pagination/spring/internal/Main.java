package org.eclipse.nebula.widgets.pagination.spring.internal;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("A", new Address()));
		persons.add(new Person("B", new Address()));
		
		 final PropertyDescriptor descriptor = BeanPropertyHelper.getPropertyDescriptor(Person.class, "address.name");
		 System.err.println(descriptor);
		 
		 Collections.sort(persons, new Comparator() {
			 public int compare(Object o1, Object o2) {
				 
				 Object o = BeanPropertyHelper.readProperty(o1, descriptor);
				 System.err.println(o);
				 return 1;
			 };
		 });
	}
}
